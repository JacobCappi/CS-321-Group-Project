import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class searchResultsDisplay extends JPanel{
    private JPanel test;
    private JTable m_gameTable;
    private JButton returntoUserPageButton;
    private JButton searchButton;
    private JButton m_addGameButton;
    private JTextField searchGameText;
    private FileManager m_fileManager;

    private int m_row = 0;
    private int m_column = 0;
    ArrayList <Game> m_searchResult = new ArrayList<>(); // lazy coding, already annoyed

    ArrayList  <ChangeListener> returntoUser = new ArrayList<>();
    ArrayList <ChangeListener> m_addGame = new ArrayList<>();

    public searchResultsDisplay(User user) throws IOException, ParseException {
        returntoUserPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : returntoUser) {
                    listener.stateChanged(event);
                }
            }
        });
        m_addGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    m_fileManager = new FileManager();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                m_row = m_gameTable.getSelectedRow();
                String m_title = (String) m_gameTable.getValueAt(m_row, 0);
                for(Game g : (Iterable<Game>) m_searchResult ){
                    if(g.getTitle().equals(m_title)){
                        user.getGameLists().get(0).addGame(g);
                        try {
                            m_fileManager.saveUserData(user);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
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

             Game m_tmpGame = new Game();
             m_tmpGame = g;
             m_searchResult.add(g);
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

