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
    private JTable gameTable;
    private JComboBox typeCombo;
    private JComboBox genreCombo;
    private JTextField searchBox;
    private JButton searchButton;
    private JButton Logout;
    ArrayList<ChangeListener> searchListener = new ArrayList<>();
    ArrayList< ChangeListener> logoutListener = new ArrayList<>();
    Game m_testGame = new Game();
    GameList m_searchResult = new GameList();
    FileManager m_fileManager = new FileManager();

    public userpage() throws IOException, ParseException {
        createTable();
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

    private void createTable() {
        Object[][] data = {
                {"Yakuza: Like a Dragon", "2020", "Role-playing", "7.8", "Playstation 4, Xbox One, Playstation 5, Xbox Series X, PC"},
                {"Prey", "2017", "First-person shooter", "8.9", "Playstation 4, Xbox One, PC"},
                {"Red Dead Redemption II", "2018", "Action-adventure", "9.0", "Playstation 4, Xbox One, Google Stadia, PC"},
                {"Animal Crossing: New Horizons", "2020", "Life simulation", "10", "Nintendo Switch"},
                {"Death Stranding", "2019", "Action", "9.0", "Playstation 4, PC"},

        };
        gameTable.setModel( new DefaultTableModel(
                data,
                new String[]{"Title", "Year", "Genre", "Rating", "Platforms"}
        ));
        TableColumnModel columns = gameTable.getColumnModel();
        columns.getColumn(0).setMinWidth(100);

        //center columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < data.length; i++){
            columns.getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    //here you will need to get an Array list of genres from json

    public void createGenreCombo(){
        genreCombo.setModel(new DefaultComboBoxModel(new String[]{"yeah"})); //need to add action listener to work
    }

    public void createTypeCombo(){
        genreCombo.setModel(new DefaultComboBoxModel(new String[]{"yeah"}));
    }
    public void addSearchListener(ChangeListener listener) {
        searchListener.add(listener);
    }
    public void addlogoutListener(ChangeListener listener) {logoutListener.add(listener);}
}
