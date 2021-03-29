import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class UserList {
   private final JSONParser parser = new JSONParser();

    public boolean loginUser (User insertUser) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(String.valueOf(Paths.get("test.json")))); // Object that will parse the JSON file for its information
        JSONArray jsonArray = (JSONArray) obj;

        for (Object o : jsonArray) {
            JSONObject currentOBJ = (JSONObject) o;
            if (insertUser.getName().equals((String) currentOBJ.get("username")) && insertUser.getPassword().equals((String) currentOBJ.get("password"))) {
                return true;
            }
        }
        return false;
    }


}
