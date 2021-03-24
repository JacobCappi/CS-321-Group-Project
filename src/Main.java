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
        final TestForm2021 testForm = new TestForm2021();
        final JPanel cardSet= new JPanel(new CardLayout());       //creates a card set which will accept Jpanels into its cardset;

        cardSet.add(userLogin.getLoginPanel(), " Login View"); //adds the login display Panel
        cardSet.add(userDisplay.getTestingPanel(), "RANDOM"); //adds the Test display Panel
        cardSet.add(testForm.getYes(),"DOES THIS WORK");
        testFrame.add(cardSet);  //adds  cardset to the JFrame


        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
        /**
         * Change listener that listens for a change to happen in the userLogin(LoginScreenDisplay) panel, which will be when the Log in button is clicked
         */
        userLogin.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent  e){
                CardLayout cl = (CardLayout) (cardSet.getLayout());
                cl.show(cardSet,  "RANDOM"); //shows the the testing card
            }

        });

        userLogin.addChangeListener2(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent  e2){
                CardLayout cl = (CardLayout) (cardSet.getLayout());
                cl.show(cardSet,  "DOES THIS WORK"); //shows the the testing card
            }

        });


    }
}
