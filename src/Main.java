import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {


    public static  void main ( String []args ) throws FileNotFoundException {
        JFrame testFrame = new JFrame(); //creates a new Jframe

        testFrame.setSize(500,500); //sets the Jframe size
        testFrame.setLocationRelativeTo(null); // sets the location to null so that the Jframe does not open in the top left corner of the screen
        final LoginScreenDisplay userLogin = new LoginScreenDisplay(); //creates a new loginDisplay for the card Layout
        final cardTestDisplay userDisplay = new cardTestDisplay(); // creates a new loginDisplay for the card Layout
        final JPanel cardset= new JPanel(new CardLayout());       //creates a card set which will accept Jpanels into its cardset
        cardset.add(userLogin.getLoginPanel(), " Login View"); //adds the login display Panel
        cardset.add(userDisplay.getTestingPanel(), "RANDOM"); //adds the Test display Panel
        testFrame.add(cardset);  //adds  cardset to the JFrame


        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
        /**
         * Change listener that listens for a change to happen in the userLogin(LoginScreenDisplay) panel, which will be when the Log in button is clicked
         */
        userLogin.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent  e){
                CardLayout cl = (CardLayout) (cardset.getLayout());
                cl.show(cardset,  "RANDOM"); //shows the the testing card
            }

        });


    }
}
