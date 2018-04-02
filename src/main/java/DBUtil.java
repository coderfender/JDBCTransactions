import org.json.simple.JSONObject;

import java.sql.*;

public class DBUtil {
    static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "rajinikanth";

    Connection conn = null;
    Statement stmt = null;

    DBUtil() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    public JSONObject getSum(String userId) throws SQLException {
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
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
}
