import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {


    public static  void main ( String []args ) throws FileNotFoundException {

        JFrame testFrame = new JFrame(); //creates a new Jframe

        User m_newUser = new User();

        testFrame.setSize(500,500); //sets the Jframe size
        testFrame.setLocationRelativeTo(null); // sets the location to null so that the Jframe does not open in the top left corner of the screen
        final LoginScreenDisplay userLogin = new LoginScreenDisplay(); //creates a new loginDisplay for the card Layout
        final RegisterUserDisplay userRegister = new RegisterUserDisplay(m_newUser); // creates a new Register user display for the card Layout;
        final userpage userListPage = new userpage();
        final JPanel cardSet= new JPanel(new CardLayout());       //creates a card set which will accept Jpanels into its cardset;

        cardSet.add(userLogin.getLoginPanel(), "LoginView"); //adds the login display Panel
        cardSet.add(userRegister.getRegisterDisplay(), "RegisterView"); //adds the Test display Panel
        cardSet.add(userListPage.getRootPanel(),"UserView");
        testFrame.add(cardSet);  //adds  cardset to the JFrame


        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
        /*
          Change listener that listens for a change to happen in the userLogin(LoginScreenDisplay) panel, which will be when the Log in button is clicked
         */
        CardLayout cl = (CardLayout) (cardSet.getLayout());

        userLogin.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent  e){
                cl.show(cardSet,  "UserView"); //shows the the testing card
            }

        });

        userLogin.addChangeListener2(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent  e2){
                cl.show(cardSet,  "RegisterView"); //shows the the testing card
            }

        });

        userRegister.addListenerReturntoLogin(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                cl.show(cardSet, "LoginView");
            }
        });
        // Needs error Checking :: will work on soon
        userRegister.addListenerLogin(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                cl.show(cardSet, "UserView");
            }
        });


    }
}
