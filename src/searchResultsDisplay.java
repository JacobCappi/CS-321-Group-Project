import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class searchResultsDisplay extends JPanel{
    private JPanel test;
    private JTable m_gameTable;
    private JButton returntoUserPage;
    private JButton searchButton;
    private JButton addGameButton;
    private JTextField searchGameText;
    FileManager manager = new FileManager();
    Game Gabe = new Game();
    ArrayList  <ChangeListener> returntoUser = new ArrayList<>();

    public searchResultsDisplay() throws IOException, ParseException {
        returntoUserPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : returntoUser) {
                    listener.stateChanged(event);
                }
            }
        });
    }

    public void setGameDisplay(GameList gameList){
         String[][] m_data = new String[gameList.getLength()][3];
         int m_counter = 0;

         for (Game g : (Iterable<Game>)gameList) {
             m_data[m_counter][0] = g.getTitle();
             m_data[m_counter][1] = g.getGenre();
             m_data[m_counter++][2] = g.getPublisher();
         }

         m_gameTable.setModel( new DefaultTableModel(
                 m_data,
                 new String[]{"Title", "Genre", "Publisher"}
         ));
         TableColumnModel columns = m_gameTable.getColumnModel();
         columns.getColumn(0).setMinWidth(0);

    }



    public JPanel getTest(){return test;}
    public void addReturntoUserPage(ChangeListener listener) {
        returntoUser.add(listener);
    }
}

