package tables;

import objects.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeTable extends AbsTable {

    public TypeTable(String tableName) {
        super("type");
    }

//    public List<Type> read() throws SQLException {
//        List<Type> types = new ArrayList<>();
//        ResultSet resultSet = super.read();
//
//        while (resultSet.next()) {
//            Type type = new Type(
//                    resultSet.getInt("id"));
//        }
//    }

}
