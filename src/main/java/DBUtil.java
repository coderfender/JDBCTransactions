import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public JSONObject addTransaction(String JSONPath) throws IOException, ParseException, SQLException {
        JSONParser jsonParser = new JSONParser();
        Object json_blob = jsonParser.parse(new FileReader(JSONPath));
        JSONObject ob  = (JSONObject) json_blob;
        String amount = (String) ob.get("amount");
        System.out.println(amount);
        String description = (String) ob.get("description");
        Date date = (Date) ob.get("date");
        String userId = (String) ob.get("user_id");
        sql = "INSERT INTO test (transaction_id,amount,desc,user_id,rand_date)" + String.format("VALUES (LEFT(UIUD(),8),%f,%s,%s,%s)",
                amount,description,date,userId);
        PreparedStatement prep = conn.prepareStatement(sql);
        prep.executeUpdate();
        ResultSet resultSet = stmt.executeQuery(sql);
        return null;
    }
}
