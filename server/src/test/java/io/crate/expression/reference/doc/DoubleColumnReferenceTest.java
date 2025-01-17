/*
 * Licensed to Crate.io GmbH ("Crate") under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.  Crate licenses
 * this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial agreement.
 */

package io.crate.expression.reference.doc;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.SortedNumericDocValuesField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.NumericUtils;
import org.junit.Test;

import io.crate.execution.engine.fetch.ReaderContext;
import io.crate.expression.reference.doc.lucene.DoubleColumnReference;

public class DoubleColumnReferenceTest extends DocLevelExpressionsTest {

    private String column = "d";

    public DoubleColumnReferenceTest() {
        super("create table t (d double)");
    }

    @Override
    protected void insertValues(IndexWriter writer) throws Exception {
        for (double d = 0.5; d < 10.0d; d++) {
            Document doc = new Document();
            doc.add(new SortedNumericDocValuesField(column, NumericUtils.doubleToSortableLong(d)));
            writer.addDocument(doc);
        }
    }

    @Test
    public void testFieldCacheExpression() throws Exception {
        DoubleColumnReference doubleColumn = new DoubleColumnReference(column);
        doubleColumn.startCollect(ctx);
        doubleColumn.setNextReader(new ReaderContext(readerContext));
        IndexSearcher searcher = new IndexSearcher(readerContext.reader());
        TopDocs topDocs = searcher.search(new MatchAllDocsQuery(), 10);
        double d = 0.5;
        for (ScoreDoc doc : topDocs.scoreDocs) {
            doubleColumn.setNextDocId(doc.doc);
            assertThat(doubleColumn.value(), is(d));
            d++;
        }
    }
}
