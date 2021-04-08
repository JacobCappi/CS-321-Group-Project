import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.io.IOException;

public class testDisplay extends JPanel{
    private JPanel test;
    private JLabel test1;
    private JLabel test2;
    private JLabel test3;
    private JTable m_gameTable = new JTable();
    FileManager manager = new FileManager();
    Game Gabe = new Game();

    public testDisplay() throws IOException, ParseException {
    }

    public void setGameDisplay(GameList gameList){
         String[][] m_data = new String[gameList.getLength()][4];
         int m_counter = 0;

         for (Game g : (Iterable<Game>)gameList) {
             m_data[m_counter][0] = g.getTitle();
             m_data[m_counter][1]= g.getPublisher();
             m_data[m_counter][2] = g.getGenre();
             m_data[m_counter++][3] = g.getStatus();
         }

         m_gameTable.setModel( new DefaultTableModel(
                 m_data,
                 new String[]{"Title", "Publisher", "Genre", "Status"}
         ));
         TableColumnModel columns = m_gameTable.getColumnModel();
         columns.getColumn(0).setMinWidth(0);

    }



    public JPanel getTest(){return test;}
}

