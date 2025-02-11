/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: https://www.jooq.org/legal/licensing
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.impl;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.Internal.*;
import static org.jooq.impl.Keywords.*;
import static org.jooq.impl.Names.*;
import static org.jooq.impl.SQLDataType.*;
import static org.jooq.impl.Tools.*;
import static org.jooq.impl.Tools.BooleanDataKey.*;
import static org.jooq.impl.Tools.ExtendedDataKey.*;
import static org.jooq.impl.Tools.SimpleDataKey.*;
import static org.jooq.SQLDialect.*;

import org.jooq.*;
import org.jooq.Function1;
import org.jooq.Record;
import org.jooq.conf.ParamType;
import org.jooq.tools.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



/**
 * The <code>MAX BY</code> statement.
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
final class MaxBy<T>
extends
    AbstractAggregateFunction<T>
implements
    QOM.MaxBy<T>
{

    MaxBy(
        Field<T> value,
        Field<?> by
    ) {
        super(
            false,
            N_MAX_BY,
            Tools.nullSafeDataType(value),
            nullSafeNotNull(value, (DataType) OTHER),
            nullSafeNotNull(by, OTHER)
        );
    }

    // -------------------------------------------------------------------------
    // XXX: QueryPart API
    // -------------------------------------------------------------------------



    @Override
    public final void accept(Context<?> ctx) {
        switch (ctx.family()) {







































            case H2:
            case HSQLDB:
            case POSTGRES:
            case YUGABYTEDB: {
                List<SortField<?>> o = new ArrayList<>();
                o.add(arguments.get(1).desc());

                if (!isEmpty(withinGroupOrderBy))
                    o.addAll(withinGroupOrderBy);

                ctx.visit(arrayGet(fo(DSL.arrayAgg(arguments.get(0)).orderBy(o)), inline(1)));
                break;
            }

            default:
                acceptFunctionName(ctx);
                ctx.sql('(');
                acceptArguments0(ctx);
                acceptOrderBy(ctx);
                ctx.sql(')');

                acceptFilterClause(ctx);
                acceptOverClause(ctx);
                break;
        }
    }

    @Override
    final void acceptFunctionName(Context<?> ctx) {
        switch (ctx.family()) {
            case CLICKHOUSE:
                ctx.visit(N_argMax);
                break;







            default:
                super.acceptFunctionName(ctx);
                break;
        }
    }



    // -------------------------------------------------------------------------
    // XXX: Query Object Model
    // -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    @Override
    public final Field<T> $value() {
        return (Field<T>) getArgument(0);
    }

    @Override
    public final Field<?> $by() {
        return getArgument(1);
    }

    @Override
    public final QOM.MaxBy<T> $value(Field<T> newValue) {
        return $constructor().apply(newValue, $by());
    }

    @Override
    public final QOM.MaxBy<T> $by(Field<?> newValue) {
        return $constructor().apply($value(), newValue);
    }

    public final Function2<? super Field<T>, ? super Field<?>, ? extends QOM.MaxBy<T>> $constructor() {
        return (a1, a2) -> new MaxBy<>(a1, a2);
    }

























    // -------------------------------------------------------------------------
    // XXX: The Object API
    // -------------------------------------------------------------------------

    @Override
    public boolean equals(Object that) {
        if (that instanceof QOM.MaxBy<?> o) {
            return
                StringUtils.equals($value(), o.$value()) &&
                StringUtils.equals($by(), o.$by())
            ;
        }
        else
            return super.equals(that);
    }
}
