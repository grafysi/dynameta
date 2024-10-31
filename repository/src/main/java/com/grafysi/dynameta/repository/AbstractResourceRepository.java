package com.grafysi.dynameta.repository;

import com.grafysi.dynameta.api.annotation.Inject;
import com.grafysi.dynameta.api.repository.RepositoryException;
import com.grafysi.dynameta.api.repository.ResourceRepository;
import com.grafysi.dynameta.api.service.PgDataSource;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

import static org.jooq.impl.DSL.*;

public abstract class AbstractResourceRepository implements ResourceRepository {

    private static final Logger logger = LoggerFactory.getLogger(AbstractResourceRepository.class);

    private static final String RESOURCE_TABLE = "core.resources";

    @Inject
    private PgDataSource dataSource;

    protected PgDataSource getDataSource() {
        return dataSource;
    }

    protected int baseCreateResource(String name, Long definitionId, byte[] value, Long leaseId) {
        // build query
        var query = create().insertInto(table(RESOURCE_TABLE))
                .columns(field("name"), field("definition_id"), field("value"), field("lease_id"))
                .values(val(name), val(definitionId), val(value), val(leaseId));
        // execute with jdbc
        return runUpdateQuery(query, logger::debug);
    }

    protected int baseUpdateResource(String name, byte[] value) {
        // build query
       var query = create().update(table(RESOURCE_TABLE))
               .set(field("value"), val(value))
               .where(field("name").eq(val(name)));
       // execute with jdbc
        return runUpdateQuery(query, logger::debug);
    }

    protected int baseUpdateResource(String name, Long leaseId) {
        // build query
        var query = create().update(table(RESOURCE_TABLE))
                .set(field("lease_id"), val(leaseId))
                .where(field("name").eq(val(name)));
        // execute with jdbc
        return runUpdateQuery(query, logger::debug);
    }

    protected int baseDeleteResource(String name) {
        // build query
        var query = create().deleteFrom(table("resource"))
                .where(field("name").eq(val(name)));
        // execute with jdbc
        return runUpdateQuery(query, logger::debug);
    }

    private int runUpdateQuery(Query query, Consumer<String> logger) throws RepositoryException {
        if (logger != null) {
            logger.accept("Query: " + query.getSQL());
            logger.accept("Params: " + query.getBindValues());
        }
        try (var conn = dataSource.getConnection();
             var pst = createPrepareStatement(conn, query)) {
            return pst.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    private ResultSet runSingleResourceQuery(Query query, Consumer<String> logger) throws RepositoryException {
        if (logger != null) {
            logger.accept("Query: " + query.getSQL());
            logger.accept("Params: " + query.getBindValues());
        }
        try (var conn = dataSource.getConnection();
             var pst = createPrepareStatement(conn, query)) {
            return pst.executeQuery();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    private DSLContext create() {
        return DSL.using(SQLDialect.POSTGRES);
    }

    private PreparedStatement createPrepareStatement(Connection conn, Query query) throws SQLException {
        var pst = conn.prepareStatement(query.getSQL());
        var paramIndex = 1;
        for (var value : query.getBindValues()) {
            pst.setObject(paramIndex++, value);
        }
        return pst;
    }
}




















