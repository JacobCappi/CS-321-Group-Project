import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class FileManager {

    private String m_loginFiles = "login.json";
    private String m_userFiles = "users.json";

    public boolean isRegisteredUser(User insertUser) throws IOException, ParseException, FileNotFoundException {
        JSONParser m_parser = new JSONParser();
        Reader m_reader = new FileReader(m_loginFiles);
        JSONObject m_objJSON = (JSONObject)m_parser.parse(m_reader);

        File m_testFile = new File("login.json");
        if(m_testFile.length() == 0) {
            return false;
        }
        JSONArray m_jsonArray = (JSONArray) m_objJSON.get("Users");

        Iterator<String> m_jsonArrayIterator = m_jsonArray.iterator();

        while(m_jsonArrayIterator.hasNext()){
            String m_tmpCheck = m_jsonArrayIterator.next();
            if(m_jsonArrayIterator.next().equals(insertUser.toString())){
                return true;
            }
        }
        return false;

    }

    public boolean addUser(User user) throws IOException, ParseException {
        JSONObject m_topLevelJson = new JSONObject();
        JSONArray m_jsonArray;

        File m_testFile = new File("login.json");
        if(m_testFile.length() == 0) {
            m_jsonArray = new JSONArray();
        }
        else{
            JSONParser m_parser = new JSONParser();
            Reader m_reader = new FileReader(m_loginFiles);
            JSONObject m_objJSON = (JSONObject)m_parser.parse(m_reader);
            m_jsonArray = (JSONArray) m_objJSON.get("Users");
        }

        JSONObject m_jsonObject = new JSONObject();
        m_jsonObject.put("ID", user.toString());

        m_jsonArray.add(m_jsonObject.toJSONString());

        m_topLevelJson.put("Users", m_jsonArray);

        try {
            FileWriter m_fileToWrite = new FileWriter(m_loginFiles);
            m_fileToWrite.write(m_topLevelJson.toJSONString());
            m_fileToWrite.flush();
            m_fileToWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}