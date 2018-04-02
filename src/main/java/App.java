import org.json.simple.JSONObject;

import java.sql.SQLException;

public class App {
    public static  void main(String[] args) throws SQLException {
        Transactor transact = new Transactor();
        String[] test = {"85","7BJMS3"};
        JSONObject result = transact.getResults(test);
        System.out.println(result.toJSONString());
    }
}
