import org.json.simple.JSONObject;
import java.sql.*;

public class Transactor {
    DBUtil dbObject;

    public Transactor(){
        try {
            dbObject = new DBUtil();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public JSONObject addTransaction(String[] args){
            return null;
    }

    public JSONObject aggTransaction(String[] args) throws SQLException {

        if (args[1].equals("sum")) {
            System.out.println("Sum function detected!!");
            return dbObject.getSum(args[0]);
        }

        return new JSONObject();

    }


    public JSONObject getResults(String[] args) throws SQLException {

        if (args.length==3){
            return addTransaction(args);
        }
        else if (args.length==2){
            return aggTransaction(args);
        }
        return null;

    }

}
