package com.grafysi.dynameta.context;

import com.grafysi.dynameta.api.service.PgDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class PgDataSourceImpl implements PgDataSource {

    private final DataSource internal;

    public PgDataSourceImpl(@Autowired DataSource dataSource) {
        this.internal = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return internal.getConnection();
    }
}
