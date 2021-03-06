package org.javers.repository.sql.finders;

import org.javers.repository.sql.schema.TableNameProvider;
import org.polyjdbc.core.query.SelectQuery;

import static org.javers.repository.sql.schema.FixedSchemaFactory.GLOBAL_ID_TYPE_NAME;
import static org.javers.repository.sql.schema.FixedSchemaFactory.GLOBAL_ID_FRAGMENT;

/**
 * @author bartosz.walacik
 */
public class VoOwnerEntityFilter extends SnapshotFilter {
    private final String ownerTypeName;
    private final String fragment;

    VoOwnerEntityFilter(TableNameProvider tableNameProvider, String ownerTypeName, String fragment) {
        super(tableNameProvider);
        this.ownerTypeName = ownerTypeName;
        this.fragment = fragment;
    }

    @Override
    void addWhere(SelectQuery query) {
        query.where("o." + GLOBAL_ID_TYPE_NAME + " = :ownerTypeName").withArgument("ownerTypeName", ownerTypeName)
             .append(" AND g." + GLOBAL_ID_FRAGMENT + " = :fragment").withArgument("fragment", fragment);
    }

}
