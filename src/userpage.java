import org.json.simple.parser.ParseException;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    ArrayList<ChangeListener> searchListener = new ArrayList<>();
    Game m_testGame = new Game();
    GameList m_searchResult = new GameList();
    FileManager m_fileManager = new FileManager();

    public userpage() throws IOException, ParseException {
        createTable();
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
        columns.getColumn(0).setMinWidth(0);

    }

    public void addSearchListener(ChangeListener listener) {
        searchListener.add(listener);
    }

}
