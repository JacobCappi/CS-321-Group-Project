import java.util.ArrayList;

public class User {
    ArrayList<GameList> m_GameLists = new ArrayList<>(); // Game list placeholder
    String m_Name;
    String m_Password;

    public User(){
        m_Name = "Guest";
        m_Password = " ";
    }

    public void setPassword(String password){m_Password = password;}
    public void setName(String name){m_Name = name;}
    public void setInfo(String name, String password){
        m_Name = name;
        m_Password = password;
    }

    public String getName(){return m_Name;} //Changed the gets for right now so that it does not require a String for a parameter for login (3/28/21)
    public String getPassword(){return m_Password;} //Changedthe gets for right now so that it does not require the String for a parameter for login (3/28/21)


    public void createGameList(GameList gameList){
        m_GameLists.add(gameList);
    }

    public void removeGameList(String gameListName){
    }

    public boolean addGame(Integer gameList, Integer game){
        // assuming .add(), from game object from gamelist object must be created to add();
        return false;
    }


}

