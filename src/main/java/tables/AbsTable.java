package tables;

import db.IDBConnector;
import db.MySqlDbConnector;

import java.sql.ResultSet;
import java.util.List;

public abstract class AbsTable {

    private String tableName;

    private IDBConnector dbConnector;

    public AbsTable(String tableName) {
        this.tableName = tableName;
        this.dbConnector = new MySqlDbConnector();

    }

    public void create(String... columns) {
        this.dbConnector.execute(String.format("CREATE TABLE %s (%s)", tableName, String.join(", ", columns)));
    }

//    public void insert() {
//        this.dbConnector.execute(String.format("INSERT INTO %s"));
//
//    }

    public void delete() {
        this.dbConnector.execute(String.format("DROP TABLE %s", tableName));
    }


    public ResultSet read(String... columns) {
        String requestColumns = "*";

        if (columns.length != 0) {
            requestColumns = String.join(". ", columns);
        }
        return this.dbConnector.executeResult(String.format("SELECT %s FROM TABLE %s", requestColumns, tableName));
    }

}
