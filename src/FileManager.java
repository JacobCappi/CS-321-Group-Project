import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

/**
 * Class:  FileManager
 * Description: This class handles all of the files that the project uses, and invokes methods that creates, modifies, and iterates through those files
 */
public class FileManager {

    private String m_loginFiles = "login.json";
    private String m_userFiles = "users.json";
    private String m_gameFile = "gameFile.json";
    Game tempGame = new Game();//Creates a new Game object so that data can be sent to another class

    /**
     * Method: isRegisteredUser:
     * Description: Searches a file of users to verify that the user info entered is within that file
     * @param insertUser: Parameter that takes in a user object, that contains a username and password
     * @return returns TRUE if  the user is found within the file, returns FALSE otherwise.
     * @throws IOException
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public boolean isRegisteredUser(User insertUser) throws IOException, ParseException, FileNotFoundException {
        JSONParser m_parser = new JSONParser(); //creates a new JSON parser that allows the function to parse through the JSON file
        Reader m_reader = new FileReader(m_loginFiles);// creates a new Reader Object that allows the parser to read the information
        JSONObject m_objJSON = (JSONObject)m_parser.parse(m_reader); // JSON object that allows the parser to take in the information from the parser

        File m_testFile = new File("login.json");
        if(m_testFile.length() == 0) { // if the file is empty, returns false.
            return false;
        }
        JSONArray m_jsonArray = (JSONArray) m_objJSON.get("Users"); //JSON Array that holds the array of users within the file.

        //Creates a String iterator that takes  the JSON iterator to go through the JSON array

        for (String s : (Iterable<String>) m_jsonArray) {  // For loop that loops through each item in a JSON Array

            if (s.equals(insertUser.toJSON())) { // If the current iterator equals the information in the JSON file, returns true.
                return true;
            }
        }
        return false;

    }

    /**
     * Method: addUser
     * Description: Adds a user object to a JSON file
     * @param user :  User object that is passed in so that they can be added to a JSON file
     * @throws IOException
     * @throws ParseException
     */
    public void addUser(User user) throws IOException, ParseException {
        JSONObject m_topLevelJson = new JSONObject(); //creates a new JSON object
        JSONArray m_jsonArray; //intializes a new JSONArray

        File m_testFile = new File("login.json");
        if(m_testFile.length() == 0) { //if the file is empty
            m_jsonArray = new JSONArray(); //Creates the new JSON array
        }
        else{
            JSONParser m_parser = new JSONParser(); //creates a new JSONparser that allows the JSON file to be parsed through for information
            Reader m_reader = new FileReader(m_loginFiles); //creates a new Reader Object that reads in the JSON file
            JSONObject m_objJSON = (JSONObject)m_parser.parse(m_reader); // parses the JSON file and puts the information in a JSONObject
            m_jsonArray = (JSONArray) m_objJSON.get("Users"); // takes the JSON array and puts the User information within
        }

        JSONObject m_jsonObject = new JSONObject(); //creates another JSON object that  allows the information to be put in another JSON file
        m_jsonObject.put("ID", user.toString()); //puts the ID of the user in the JSON object

        m_jsonArray.add(m_jsonObject.toJSONString()); // puts the JSON object, with the  username and password in the JSON object

        m_topLevelJson.put("Users", m_jsonArray); // puts the JSON array within the JSON Object

        try {
            FileWriter m_fileToWrite = new FileWriter(m_loginFiles); // creates a new FileWriter Object that uses the loginfiles as the filepath
            m_fileToWrite.write(m_topLevelJson.toJSONString());
            m_fileToWrite.flush();
            m_fileToWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean searchGame(String gameName) throws IOException, ParseException {
        JSONParser m_gameParser = new JSONParser();
        Reader gameReader = new FileReader(m_gameFile);
        JSONObject m_gameobjJSON = (JSONObject) m_gameParser.parse(gameReader);
        File testFile = new File("dataFile.json");
        if(testFile.length()== 0){return false;}
        JSONArray gameArray = (JSONArray)  m_gameobjJSON.get("Games");


       for (Object o: gameArray){
           JSONObject currentGame = (JSONObject) o;
           if(gameName.equals((String) currentGame.get("Title"))){
                tempGame.setGenre((String) currentGame.get("Genre"));
               tempGame.setPublisher((String) currentGame.get("Publisher"));

               tempGame.setTitle((String) currentGame.get("Title"));


               return true;
           }
       }
       return false;
    }

    public Game getTempGame(){return tempGame;}


}