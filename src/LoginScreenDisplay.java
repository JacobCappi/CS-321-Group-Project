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


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object obj = parser.parse(new FileReader("C:/Users/gdors/Documents/test.json"));

                    JSONObject jsonObject = (JSONObject) obj;
                    if (usernameTextField.getText().equals((String) jsonObject.get("username"))) {
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

    public JPanel getLoginPanel (){
        return loginPanel;
    }


    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }
}
