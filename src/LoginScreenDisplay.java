import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginScreenDisplay  extends JPanel{
    private JPanel loginPanel;
    private JLabel WelcomeLabel;
    private JTextField usernameTextField;
    private JLabel usernameLabel;
    private JTextField passwordTextField;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JLabel loginLabel;
    private JLabel registerLabel;
    private JButton registerButton;
    final ArrayList<ChangeListener> listeners = new ArrayList<>();
    JSONParser parser = new JSONParser();



    public  LoginScreenDisplay() throws FileNotFoundException {;

        /**
         * Action Button Listener that will search a JSON file for a username and try  and match it with the inputted username and passowrd on the login button lick
         *
         */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object obj = parser.parse(new FileReader("C:/Users/gdors/Documents/test.json")); // Object that will parse the JSON file for its information

                    JSONObject jsonObject = (JSONObject) obj; //creates a JSON object so it can retrieve the information from the JSON File

                    /**
                     * If the user's username and pass word are identical to the JSON file's password, it will alert the change listener that it can change the screen to a test string (AS of 3/22/21)
                     */

                    if (usernameTextField.getText().equals((String) jsonObject.get("username")) && passwordTextField.getText().equals((String) jsonObject.get("password"))) {
                        ChangeEvent event = new ChangeEvent(this);
                        for (ChangeListener listener : listeners)
                            listener.stateChanged(event);
                    }


                } catch (ParseException | IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    /**
     * Function: getLoginPanel
     * Description: function that returns the Jpanel of the Login view
     * @return the Jpanel of the LoginView
     */
    public JPanel getLoginPanel (){
        return loginPanel;
    }


    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }
}
