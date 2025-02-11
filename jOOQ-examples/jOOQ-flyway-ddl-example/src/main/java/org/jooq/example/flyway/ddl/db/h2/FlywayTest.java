/*
 * This file is generated by jOOQ.
 */
package org.jooq.example.flyway.ddl.db.h2;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.example.flyway.ddl.db.h2.tables.Author;
import org.jooq.example.flyway.ddl.db.h2.tables.Book;
import org.jooq.impl.DSL;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class FlywayTest extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>FLYWAY_TEST</code>
     */
    public static final FlywayTest FLYWAY_TEST = new FlywayTest();

    /**
     * The table <code>FLYWAY_TEST.AUTHOR</code>.
     */
    public final Author AUTHOR = Author.AUTHOR;

    /**
     * The table <code>FLYWAY_TEST.BOOK</code>.
     */
    public final Book BOOK = Book.BOOK;

    /**
     * No further instances allowed
     */
    private FlywayTest() {
        super(DSL.name("FLYWAY_TEST"), null, DSL.comment(""));
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.asList(
            Sequences.S_AUTHOR_ID
        );
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Author.AUTHOR,
            Book.BOOK
        );
    }
}
