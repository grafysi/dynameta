package com.grafysi.dynameta.api.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface PgDataSource {

    Connection getConnection() throws SQLException;
}
