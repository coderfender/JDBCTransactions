import org.json.simple.JSONObject;

import java.sql.SQLException;

public class App {
    public static  void main(String[] args) throws SQLException {
        Transactor transact = new Transactor();
//        String[] test = {"85","sum"};
        JSONObject result = transact.getResults(args);
        System.out.println(result.toJSONString());
    }
}
