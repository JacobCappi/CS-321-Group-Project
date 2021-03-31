import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Manager
{
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
    public boolean addUser(String inputUsername, String inputPassword) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(String.valueOf(Paths.get("test.json")))); // Object that will parse the JSON file for its information
        JSONArray addUserJSONArray = (JSONArray) obj;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", inputUsername);
        jsonObject.put("password",inputPassword);
        addUserJSONArray.add(jsonObject);
        FileWriter jsonFileWriter = new FileWriter(String.valueOf(Paths.get("test.json")));
        jsonFileWriter.write(addUserJSONArray.toJSONString());
        jsonFileWriter.flush();
        jsonFileWriter.close();
        return true;


    }
}
