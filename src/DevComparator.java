import java.util.Comparator;
public class DevComparator implements Comparator<Game> {


    @Override
    public int compare(Game o1, Game o2) {
        return o1.m_Publisher.compareTo(o2.m_Publisher);
    }
}
