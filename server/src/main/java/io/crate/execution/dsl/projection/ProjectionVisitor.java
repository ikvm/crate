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

package io.crate.execution.dsl.projection;


import javax.annotation.Nullable;

public class ProjectionVisitor<C, R> {

    public R process(Projection projection, @Nullable C context) {
        return projection.accept(this, context);
    }

    protected R visitProjection(Projection projection, C context) {
        return null;
    }

    public R visitLimitAndOffsetProjection(LimitAndOffsetProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitGroupProjection(GroupProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitAggregationProjection(AggregationProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitMergeCountProjection(MergeCountProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitWriterProjection(WriterProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitSourceIndexWriterProjection(SourceIndexWriterProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitColumnIndexWriterProjection(ColumnIndexWriterProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitFilterProjection(FilterProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitUpdateProjection(UpdateProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitDeleteProjection(DeleteProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitFetchProjection(FetchProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitSysUpdateProjection(SysUpdateProjection projection, C context) {
        return visitProjection(projection, context);
    }

    public R visitOrderedLimitAndOffset(OrderedLimitAndOffsetProjection orderedLimitAndOffsetProjection, C context) {
        return visitProjection(orderedLimitAndOffsetProjection, context);
    }

    public R visitEvalProjection(EvalProjection evalProjection, C context) {
        return visitProjection(evalProjection, context);
    }

    public R visitProjectSet(ProjectSetProjection projectSetProjection, C context) {
        return visitProjection(projectSetProjection, context);
    }

    public R visitWindowAgg(WindowAggProjection windowAgg, C context) {
        return visitProjection(windowAgg, context);
    }

    public R visitLimitDistinct(LimitDistinctProjection limitDistinctProjection, C context) {
        return visitProjection(limitDistinctProjection, context);
    }

    public R visitCorrelatedJoin(CorrelatedJoinProjection correlatedJoin, C context) {
        return visitProjection(correlatedJoin, context);
    }
}
