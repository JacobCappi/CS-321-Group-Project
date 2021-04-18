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

/**
 * Class: searchResultsDisplay
 * Description: The panel for displaying the results of the search from the UserpageDisplay.
 *              The class also handles the same search from within the class itself, and allows the user to add the game to their list
 */
public class searchResultsDisplay extends JPanel{
    private JPanel searchResultPanel;
    private JTable m_gameTable;
    private JButton returntoUserPageButton;
    private JButton searchButton;
    private JButton m_addGameButton;
    private JTextField searchGameText;
    private FileManager m_fileManager;
    private FileManager m_fileManager1 = new FileManager();
    private JLabel searchResultLabel;
    private JTextField scoreTextField;
    Game m_testGame = new Game();
    private int m_row = 0;
    private int m_column = 0;
    GameList m_searchResult = new GameList();
    GameList m_NewSearchResult = new GameList();
    ArrayList <ChangeListener> returntoUser = new ArrayList<>();
    ArrayList <ChangeListener> m_addGame = new ArrayList<>();
    ArrayList <ChangeListener> m_addAnotherGame = new ArrayList<>();

    /**
     * Constructor: searchResults Display
     * Description: Constructor that handles all of the buttons and the action handlers within the Panel
     * @param user : Passes in a user that lets the panel add the game to their list
     * @throws IOException
     * @throws ParseException
     */
    public searchResultsDisplay(User user) throws IOException, ParseException {

        //actionListener that lets the user returns the user to their list
        returntoUserPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : returntoUser) {
                    listener.stateChanged(event);
                }
            }
        });
        //actionListener that adds the selected game to their list
        m_addGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    m_fileManager = new FileManager();
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                m_row = m_gameTable.getSelectedRow(); //gets row of the game that the user wants to add
                String m_title = (String) m_gameTable.getValueAt(m_row, 0);
                String m_rating = scoreTextField.getText(); // gets the rating from the textbox



                for(Game g : (Iterable<Game>) m_searchResult){
                    if(g.getTitle().equals(m_title)){
                        if(!(m_rating.equals("") || m_rating == null)){ // if the rating is not empty
                            g.setRating(m_rating);
                        }
                        else{
                            g.setRating("N/A");
                        }
                        user.getGameLists().get(0).addGame(g); //adds the game to the user's list
                        try {
                            m_fileManager.saveUserData(user); //saves the data from the newly added game into the user's own list
                            JOptionPane.showMessageDialog(null, "Game Added!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            ChangeEvent event = new ChangeEvent(this);
                            for (ChangeListener listener : m_addGame) {
                                listener.stateChanged(event);
                            }
                            break;
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        //ActionListener on searchButton that searches the Game file again to find another game
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //gets the title from the textbox
                m_testGame.setTitle(searchGameText.getText());
                try {
                    if(m_fileManager1.isGameInList(m_testGame)){ // if the game is in the file
                       m_NewSearchResult = m_fileManager1.gamesSearchResult(m_testGame); //makes a new GameList with the search Result
                        ChangeEvent event = new ChangeEvent(this);
                        for (ChangeListener listener : m_addAnotherGame) {
                            listener.stateChanged(event);
                        }
                    }else { JOptionPane.showMessageDialog(null, "GAME NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);}
                } catch (IOException | ParseException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }


    /**
     * Method: setGameDisplay
     * Description: Sets the searchResultsDisplay with all of the games that match what the user searched.
     * @param gameList: Passes in a gameList that holds all of the games that matches the criteria from the search
     */
    public void setGameDisplay(GameList gameList){
         String[][] m_data = new String[gameList.getLength()][3];
         int m_counter = 0;
        //iterates through the game list and sets the information at each column to the current games information
         for (Game g : (Iterable<Game>)gameList) {
             m_data[m_counter][0] = g.getTitle();
             m_data[m_counter][1] = g.getGenre();
             m_data[m_counter++][2] = g.getPublisher();

             Game m_tmpGame = new Game();
             m_tmpGame = g;
             m_searchResult.addGame(g); // adds the game to the searchResults List
         }
        //sets the table with basic information
         m_gameTable.setModel( new DefaultTableModel(
                 m_data,
                 new String[]{"Title", "Genre", "Publisher"}
         ));
         TableColumnModel columns = m_gameTable.getColumnModel();
         columns.getColumn(0).setMinWidth(0);
        m_gameTable.setAutoCreateRowSorter(true);
    }


    /**
     * Method: getSearchResultPanel
     * Description: Gets the searchResultPanel for ScreenManager
     * @return returns the searchResultPanel
     */
    public JPanel getSearchResultPanel(){return searchResultPanel;}


    /**
     * Method: addReturntoUserPage
     * Description: adds the listener from returntoUserPageButton to return the user back to their page
     * @param listener Passes in a changeListener so that it can be added to the returntoUser arrayList
     */
    public void addReturntoUserPage(ChangeListener listener) {
        returntoUser.add(listener);
    }

    /**
     * Method: addAnotherGame
     * Description: adds the listener from the searchButton to search the file again with the new critera
     * @param listener Pass in a changeListner so that it can be added to the m_addNotherGame arrayList
     */
    public void addAnotherGame(ChangeListener listener){
        m_addAnotherGame.add(listener);
    }

    /**
     * Method: setSearchResultLabel
     * Descritpion: sets the Search Result Label with the string from the user
     * @param resultLabel passes in a a String that is the criteria from the search box  from the user
     */
    public void setSearchResultLabel(String resultLabel){ searchResultLabel.setText(resultLabel);}



}

