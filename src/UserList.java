import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserList {
   public ArrayList<User> listOfUsers = new ArrayList<>();
    int length;
  public UserList(){ length = 0;}

  public void addUser(User user){
      listOfUsers.add(user);
     length++;
  }

  public boolean findUser(User user){

     for(int i = 0; i < listOfUsers.size(); i++){
         if(user.getName().equals(listOfUsers.get(i).getName()) && user.getPassword().equals(listOfUsers.get(i).getPassword())){
             return true;
         }
         else{
             return false;
         }
     }
     return false;
  }


}
