import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {

    private String m_loginFiles = "login.json";
    private String m_userFiles = "users.json";

    public boolean isRegisteredUser(User insertUser) throws IOException, ParseException, FileNotFoundException {
        JSONParser m_parser = new JSONParser();
        Reader m_reader = new FileReader(m_loginFiles);
        JSONObject m_objJSON = (JSONObject)m_parser.parse(m_reader);
        return false;

    }

    public boolean addUser(User user) throws IOException, ParseException {
        JSONObject m_jsonObject = new JSONObject();
        m_jsonObject.put("UserName", user.getName());
        m_jsonObject.put("Password", user.getPassword());
        m_jsonObject.put("ID", user.toString());

        try {
            FileWriter m_fileToWrite = new FileWriter(m_loginFiles);
            m_fileToWrite.write(m_jsonObject.toJSONString());
            m_fileToWrite.flush();
            m_fileToWrite.close();
        } catch (IOException e) {
            createLoginFile();
            e.printStackTrace();
        }
        return true;
    }

    public boolean createLoginFile(){
        File m_loginFile = new File(m_loginFiles);
        try{
            if (!m_loginFile.createNewFile()) {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}