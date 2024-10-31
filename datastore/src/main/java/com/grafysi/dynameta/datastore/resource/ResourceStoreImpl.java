package com.grafysi.dynameta.datastore.resource;


import com.grafysi.dynameta.datastore.DatastoreException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ResourceStoreImpl implements ResourceStore {

    private static final int FALLBACK_LEASE_ID = -1;

    private static final String FALLBACK_TYPE = "NO_TYPE";


    private static final String INSERT_RESOURCE_QUERY = """
            INSERT INTO core.resources (name, type, definition_id, data, lease_id)
            VALUES (?, ?, ?, ?, ?)
            """;

    private final DataSource ds;

    public ResourceStoreImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void persist(Resource resource) {
        try (var conn = ds.getConnection();
             var query = conn.prepareStatement(INSERT_RESOURCE_QUERY)) {

            query.setString(1, resource.getName());
            query.setString(2,
                    resource.getType() == null ? FALLBACK_TYPE : resource.getType());
            query.setInt(3, resource.getDefinitionId());
            query.setBytes(4, resource.getData());
            query.setInt(5,
                    resource.getLeaseId() == null ? FALLBACK_LEASE_ID : resource.getLeaseId());

            query.executeUpdate();

        } catch (SQLException e) {
            throw new DatastoreException(e);
        }
    }
}



















