import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginScreenDisplay  extends JPanel{
    private JPanel LoginPanel;
    private JLabel WelcomeLabel;
    private JTextField usernameTextField;
    private JLabel usernameLabel;
    private JTextField textField1;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JLabel loginLabel;
    private JLabel registerLabel;
    private JButton registerButton;
    final ArrayList<ChangeListener> listeners = new ArrayList<>();

    public  LoginScreenDisplay(){


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener: listeners)
                    listener.stateChanged(event);

            }
        });
    }

    public JPanel getLoginPanel (){
        return LoginPanel;
    }


    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }
}
