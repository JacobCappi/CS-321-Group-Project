import java.util.ArrayList;

public class User {
    ArrayList<Integer> m_uGameLists = new ArrayList<>(); // Game list placeholder
    String m_uName;
    String m_uPassword;

    public User(){
        m_uName = "Guest";
        m_uPassword = " ";
    }

    public void setUPassword(String password){m_uPassword = password;}
    public void setUName(String name){m_uName = name;}
    public void setUInfo(String name, String password){
        m_uName = name;
        m_uPassword = password;
    }

    public String getUname(String name){return m_uName;}
    public String getUPassword(String password){return m_uPassword;}


    public void createUGameList(Integer gameList){ // again 'Integer' is placeholder for 'GameList' when we build that
        m_uGameLists.add(gameList);
    }

    public void removeUGameList(String gameListName){
        //some search method once gameList object is created
        m_uGameLists.get(0).compareTo(1);
        m_uGameLists.remove(0);

    }

    public boolean addGame(Integer gameList, Integer game){
        // assuming .add(), from game object from gamelist object must be created to add();
        return false;
    }


}

