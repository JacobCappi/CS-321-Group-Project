import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterUserDisplay extends JPanel{

    final JPanel registerDisplay = new JPanel();
    final JLabel l_userNameLabel = new JLabel("Create UserName: ");
    final JTextField l_userNameBlank = new JTextField(30);
    final JLabel l_passwordLabel = new JLabel("Create Password: ");
    final JTextField l_passwordBlank = new JTextField(30);
    final JButton l_submitButton = new JButton("Create User");

    String m_inputStringUserName;
    String m_inputStringPassword;
    UserList tempUserList= new UserList();

    final ArrayList<ChangeListener> listeners = new ArrayList<>(); // unsure what to do with quite yet
    FileReader inputFile;

    public RegisterUserDisplay(String inputUser, String inputPassword){
        if(inputUser == null || inputUser == ""){
            inputUser = "Create Username: ";
        }
        l_userNameLabel.setText(inputUser);

        if(inputPassword == null || inputPassword == ""){
            inputPassword = "Create Password: ";
        }
        l_passwordLabel.setText(inputPassword);

        l_userNameBlank.setPreferredSize(new Dimension(150, 20));
        l_passwordBlank.setPreferredSize(new Dimension(150, 20));
        l_userNameBlank.setMaximumSize(new Dimension(200, 20));
        l_passwordBlank.setMaximumSize(new Dimension(200, 20));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        registerDisplay.add(l_userNameLabel);
        registerDisplay.add(l_userNameBlank);
        registerDisplay.add(l_passwordLabel);
        registerDisplay.add(l_passwordBlank);
        registerDisplay.add(l_submitButton);


        registerDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_userNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_userNameBlank.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_passwordBlank.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        m_inputStringUserName = "";
        m_inputStringPassword = "";

        l_submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                m_inputStringUserName = l_userNameBlank.getText();
                m_inputStringPassword = l_passwordBlank.getText();
                try {
                    tempUserList.addUser(m_inputStringUserName,m_inputStringPassword);
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                ChangeEvent event = new ChangeEvent(this);
                    for (ChangeListener listener : listeners)
                    listener.stateChanged(event);


            }
        });


    }
        public JPanel getRegisterDisplay(){
            return registerDisplay;
        }

    public void addChangeListenerRegister(ChangeListener listener) {
        listeners.add(listener);
    }
}
