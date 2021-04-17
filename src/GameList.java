import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

public class GameList implements Cloneable, Iterable{
    String m_listName;
    ArrayList<Game> m_gameList = new ArrayList<>();

    int m_length;

    public GameList(){
        m_length = 0;
        setListName("Default");
    }

    public void setListName(String listName){m_listName = listName;}
    public String getListName(){return m_listName;}

    public void addGame(Game game){
        m_gameList.add(game);
        m_length++;
    }

    public boolean deleteGame(Game game){
        for(int i =0; i<m_length; i++){
            if(m_gameList.get(i).getTitle().equals(game.getTitle())){
                m_gameList.remove(i);
                m_length--;
                return true;
            }
        }
        return false;
    }

    public void clear(){
        m_gameList.clear();
        m_length = 0;
    }

    public boolean ifExists(Game game){
        for(int i =0; i<m_length; i++){
            if(m_gameList.get(i).getID().equals(game.getID())){
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return m_length;
    }

    @Override
    public String toString() {
        return "GameList{" +
                "m_listName='" + m_listName + '\'' +
                ", m_gameList=" + m_gameList +
                ", m_length=" + m_length +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Iterator<Game> iterator() {
        return this.m_gameList.iterator();
    }

}
