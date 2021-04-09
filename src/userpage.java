import org.json.simple.parser.ParseException;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class userpage {
    private JPanel rootPanel;
    private JTable m_gameTable;
    private JComboBox genreCombo;
    private JTextField searchBox;
    private JButton searchButton;
    private JButton Logout;
    ArrayList<ChangeListener> searchListener = new ArrayList<>();
    ArrayList< ChangeListener> logoutListener = new ArrayList<>();

    User m_currentUser = new User();
    Game m_testGame = new Game();
    GameList m_searchResult = new GameList();
    FileManager m_fileManager = new FileManager();

    public userpage(User user) throws IOException, ParseException {
        createTable(user);
        createGenreCombo();
        createTypeCombo();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    m_testGame.setTitle(searchBox.getText());
                    if(m_fileManager.isGameInList(m_testGame)){
                        m_searchResult = m_fileManager.gamesSearchResult(m_testGame);

                        for (Game g : (Iterable<Game>) m_searchResult) {  // For loop that loops through each item in a JSON Array
                            System.out.println(g.toString());
                        }

                        ChangeEvent event = new ChangeEvent(this);
                        for (ChangeListener listener : searchListener ) {
                            listener.stateChanged(event);
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "GAME NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ParseException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : logoutListener ) {
                    listener.stateChanged(event);
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createTable(User user) {
        // will make it look better later
        String[][] m_data = new String[user.getGameLists().get(0).getLength()][3];
        int m_counter = 0;

        for (Game g : (Iterable<Game>)user.getGameLists().get(0)) {
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


    //here you will need to get an Array list of genres from json

    public void createGenreCombo(){
        genreCombo.setModel(new DefaultComboBoxModel(new String[]{"Genre"})); //need to add action listener to work
    }

    public void createTypeCombo(){
        genreCombo.setModel(new DefaultComboBoxModel(new String[]{"Genre"}));
    }
    public void addSearchListener(ChangeListener listener) {
        searchListener.add(listener);
    }
    public void addlogoutListener(ChangeListener listener) {logoutListener.add(listener);}
}
