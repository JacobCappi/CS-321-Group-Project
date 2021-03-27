import java.util.ArrayList;

public class User {
    ArrayList<GameList> m_uGameLists = new ArrayList<>(); // Game list placeholder
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

    public String getName(String name){return m_Name;}
    public String getPassword(String password){return m_Password;}


    public void createGameList(GameList gameList){ // again 'Integer' is placeholder for 'GameList' when we build that
        m_uGameLists.add(gameList);
    }

    public void removeGameList(String gameListName){


    }

    public boolean addGame(Integer gameList, Integer game){
        // assuming .add(), from game object from gamelist object must be created to add();
        return false;
    }


}

