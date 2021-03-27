import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class userpage {
    private JPanel rootPanel;
    private JTable gameTable;
    private JComboBox typeCombo;
    private JComboBox genreCombo;
    private JButton button1;

    public userpage() {
        createTable();
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
}
