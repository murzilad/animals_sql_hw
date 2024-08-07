package tables;

import db.IDBConnector;
import db.MySqlDbConnector;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbsTable {

    protected String tableName;
    protected IDBConnector dbConnector;
    protected Map<String, String> columns;

    public AbsTable(String tableName) {
        this.tableName = tableName;
        this.dbConnector = new MySqlDbConnector();
    }

    public void create() {
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, convertMapColumnsToString());
        dbConnector = new MySqlDbConnector();
        dbConnector.execute(sqlRequest);
        dbConnector.close();
    }

    private String convertMapColumnsToString() {
        final String result = columns.entrySet().stream()
                .map((Map.Entry entry) -> String.format("%s %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
        return result;
    }
}
