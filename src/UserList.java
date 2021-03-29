import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class UserList {
   private final JSONParser parser = new JSONParser();

    public boolean loginUser (User insertUser) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(String.valueOf(Paths.get("test.json")))); // Object that will parse the JSON file for its information
        JSONObject jsonObject = (JSONObject) obj;

        return insertUser.getName().equals((String) jsonObject.get("username")) && insertUser.getPassword().equals((String) jsonObject.get("password"));
    }
}
