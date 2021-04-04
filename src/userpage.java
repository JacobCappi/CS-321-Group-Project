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
    Game testGame = new Game();
    FileManager test = new FileManager();
    public userpage() {
        createTable();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getText = searchBox.getText();
                try {
                    if(test.searchGame(searchBox.getText())){
                        searchButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ChangeEvent event = new ChangeEvent(this);
                                for (ChangeListener listener : searchListener ) {
                                    listener.stateChanged(event);
                                }
                            }
                        });
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
