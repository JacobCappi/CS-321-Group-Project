import java.util.Comparator;


public class GameComparator implements Comparator<Game>{

    @Override
    public int compare(Game o1, Game o2) {
      return o1.m_Title.compareTo(o2.m_Title);
    }

}
