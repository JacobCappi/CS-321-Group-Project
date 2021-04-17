import org.json.simple.parser.ParseException;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserpageDisplay {

    private JPanel rootPanel;
    private JTable m_gameTable;
    private JComboBox genreCombo;
    private JTextField searchBox;
    private JButton searchButton;
    private JButton Logout;
    private JButton deleteButton;
    private JLabel titleLabel;
    ArrayList<ChangeListener> searchListener = new ArrayList<>();
    ArrayList< ChangeListener> logoutListener = new ArrayList<>();
    ArrayList<ChangeListener> deleteListener  = new ArrayList<>();
    User m_currentUser = new User();
    Game m_testGame = new Game();
    GameList m_searchResult = new GameList();
    FileManager m_fileManager = new FileManager();


    public UserpageDisplay(User user) throws IOException, ParseException {

        createTable(user);
        createGenreCombo(user);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    m_testGame.setTitle(searchBox.getText());
                    if(m_fileManager.isGameInList(m_testGame)){
                        m_searchResult = m_fileManager.gamesSearchResult(m_testGame);

                        ChangeEvent event = new ChangeEvent(this);
                        for (ChangeListener listener : searchListener ) {
                            listener.stateChanged(event);
                        }

                    }
                    else{ JOptionPane.showMessageDialog(null, "GAME NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);}
                } catch (IOException | ParseException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    m_fileManager.saveUserData(user);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                user.getGameLists().get(0).clear();
                genreCombo.setSelectedIndex(0);
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : logoutListener ) {
                    listener.stateChanged(event);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game m_remove = new Game();
                int m_row = m_gameTable.getSelectedRow();
                String m_title = (String) m_gameTable.getValueAt(m_row, 0);
                m_remove.setTitle(m_title);

                user.getGameLists().get(0).deleteGame(m_remove);
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : deleteListener ) {
                    listener.stateChanged(event);
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void createTable(User user) {
        // will make it look better later
        String[][] m_data = new String[user.getGameLists().get(0).getLength()][4];

        int m_counter = 0;

        for (Game g : (Iterable<Game>)user.getGameLists().get(0)) {
            if(m_counter == user.getGameLists().get(0).getLength()){
                break;
            }
            //temporarily replacing commas with spaces to test sorting genres
            String genreL = g.getGenre().replace(",", " ");
            m_data[m_counter][0] = g.getTitle();
            m_data[m_counter][1] = genreL; //TEMPORARY
            m_data[m_counter][2] = g.getPublisher();
            m_data[m_counter++][3] = g.getRating();
        }

        m_gameTable.setModel( new DefaultTableModel(
                m_data,
                new String[]{"Title", "Genre", "Publisher" , "Rating" }
        ));
      TableColumnModel columns = m_gameTable.getColumnModel();
      columns.getColumn(0).setMinWidth(0);
       m_gameTable.setAutoCreateRowSorter(true); // THIS WILL SORT ALPHABETICALLY AUTOMATICALLY

    }


    public void createGenreCombo(User user){



        genreCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    String genreSelect = (String)genreCombo.getSelectedItem(); //grab genre selection
                    if(genreSelect.equals("Default")){
                        createTable(user);
                    }
                    else{
                        Game m_testGame = new Game();
                        m_testGame.setGenre(genreSelect);
                        User m_testUser = new User();
                        for (Game g : (Iterable<Game>)user.getGameLists().get(0)) {
                            if(g.compareGenre(m_testGame)){
                                m_testUser.getGameLists().get(0).addGame(g);
                            }
                        }
                        createTable(m_testUser);

                    }
                }
            }
        });


    }



    public void addSearchListener(ChangeListener listener) {
        searchListener.add(listener);
    }
    public void addlogoutListener(ChangeListener listener) {logoutListener.add(listener);}
    public void deleteGameListener(ChangeListener listener){ deleteListener.add(listener);}
    public void setUserTitle(String Name){
        titleLabel.setText(Name);
    }
}
