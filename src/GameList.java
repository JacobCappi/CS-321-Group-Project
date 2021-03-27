import java.util.ArrayList;

public class GameList {
    String m_listName;
    ArrayList<Game> m_gameList = new ArrayList<>();
    int m_length;

    public GameList(){
        m_length = 0;
    }

    public void setListName(String listName){m_listName = listName;}
    public String getListName(){return m_listName;}

    public void addGame(Game game){
        m_gameList.add(game);
        m_length++;
    }

    public boolean deleteGame(Game game){
        for(int i =0; i<m_length; i++){
            if(m_gameList.get(i).getID().equals(game.getID())){
                m_gameList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean ifExists(Game game){
        for(int i =0; i<m_length; i++){
            if(m_gameList.get(i).getID().equals(game.getID())){
                return true;
            }
        }
        return false;
    }

}
