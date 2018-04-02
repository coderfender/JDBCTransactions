import org.json.simple.JSONObject;

import java.io.File;
import java.sql.*;

public class DBUtil {
    static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "rajinikanth";


    Connection conn = null;
    Statement stmt = null;
    String sql;

    DBUtil() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
    }

    public JSONObject getSum(String userId) throws SQLException {
        sql = "SELECT user_id,sum(amount) as amount from transaction where user_id = "+userId;
        ResultSet rs = stmt.executeQuery(sql);
        JSONObject resultObject = new JSONObject();

        while(rs.next()){
            resultObject.put("user_id",rs.getInt("user_id"));
            resultObject.put("sum",rs.getInt("amount"));

        }
        rs.close();
        stmt.close();
        return resultObject;
    }

    public JSONObject getListOfTransactions(String userId) throws SQLException{
        sql = "SELECT * from transaction where user_id = "+userId;
        ResultSet rs = stmt.executeQuery(sql);
        JSONObject resultObject = new JSONObject();

        while(rs.next()){
            resultObject.put("transaction_id",rs.getString(1));
            resultObject.put("amount",rs.getFloat(2));
            resultObject.put("description",rs.getString(3));
            resultObject.put("user_id",rs.getString(4));
            resultObject.put("Date",rs.getDate(5));
        }
        rs.close();
        stmt.close();
        return resultObject;

    }

    public JSONObject getAllTransaction(String user_id,String transactionID) throws SQLException {
        transactionID = "\"" +transactionID + "\"";
        sql = "SELECT * FROM TRANSACTION WHERE transaction_id = "+ transactionID+ "and + user_id = "+ user_id;

        ResultSet rs = stmt.executeQuery(sql);
        JSONObject resultObject = new JSONObject();

        if (!rs.isBeforeFirst() ) resultObject.put("Transactions not found", null);

        while(rs.next()){
            resultObject.put("transaction_id",rs.getString(1));
            resultObject.put("amount",rs.getFloat(2));
            resultObject.put("description",rs.getString(3));
            resultObject.put("user_id",rs.getString(4));
            resultObject.put("Date",rs.getDate(5));
        }
        rs.close();
        stmt.close();
        return resultObject;

    }

    public JSONObject addTransaction(String JSONPath){
        return null;
    }
}
