package com.cs_group.panels;


import com.cs_group.managers.FileManager;
import com.cs_group.objects.User;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class: RegisterUserDisplay
 * Description: Display screen for creating new users; Adds new users to the file manager by taking user input.
 * Uses the Strategy pattern.
 */

public class RegisterUserDisplay extends JPanel{

    FileManager m_fileManager = new FileManager();
    User m_tmpUser = new User();

    final JPanel m_registerDisplay = new JPanel();
    final JLabel l_createUser = new JLabel("<html><B>Create User</B>");
    final JLabel l_userNameLabel = new JLabel("Create UserName:");
    final JTextField l_userNameBlank = new JTextField(30);
    final JLabel l_passwordLabel = new JLabel("Create Password:");
    final JTextField l_passwordBlank = new JTextField(30);
    final JButton l_submitButton = new JButton("Create User");
    final JButton l_returnToLogin = new JButton("Return to Login Page");
    final JLabel l_spaceField = new JLabel("<html><br><br>");
    final JLabel l_errorMessage = new JLabel();

    String m_inputStringUserName;
    String m_inputStringPassword;

    final ArrayList<ChangeListener> listeners = new ArrayList<>();
    final ArrayList<ChangeListener> returnToLoginListener = new ArrayList<>();

    /**
     * Constructor: Register com.cs_group.objects.User Display
     * @throws IOException
     * @throws ParseException
     */
    public RegisterUserDisplay() throws IOException, ParseException {
        if(m_tmpUser.getName() == null || m_tmpUser.getName().equals("")){
            l_userNameLabel.setText("Create Username: ");
        }

        if(m_tmpUser.getPassword() == null || m_tmpUser.getPassword().equals("")){
            l_passwordLabel.setText("Create Password:  ");
        }
        l_userNameBlank.setPreferredSize(new Dimension(150, 20));
        l_passwordBlank.setPreferredSize(new Dimension(150, 20));
        l_userNameBlank.setMaximumSize(new Dimension(200, 20));
        l_passwordBlank.setMaximumSize(new Dimension(200, 20));

        l_createUser.setPreferredSize(new Dimension(450, 40));
        l_userNameLabel.setPreferredSize(new Dimension(450, 40));
        l_passwordLabel.setPreferredSize(new Dimension(450, 40));
        l_spaceField.setPreferredSize(new Dimension(400, 50));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        m_registerDisplay.add(l_createUser);
        m_registerDisplay.add(l_spaceField);
        m_registerDisplay.add(l_userNameLabel);
        m_registerDisplay.add(l_userNameBlank);
        m_registerDisplay.add(l_spaceField);
        m_registerDisplay.add(l_passwordLabel);
        m_registerDisplay.add(l_passwordBlank);
        m_registerDisplay.add(l_spaceField);
        m_registerDisplay.add(l_submitButton);
        m_registerDisplay.add(l_returnToLogin);
        m_registerDisplay.add(l_errorMessage);

        m_registerDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_createUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_userNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        l_userNameBlank.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        l_passwordBlank.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        l_returnToLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
        l_errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);


        m_inputStringUserName = "";
        m_inputStringPassword = "";


        //Action Listener that takes the information of the user and adds it to the list of users so that they can log in upon pressing the button.
        l_submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //gets the users information
                m_inputStringUserName = l_userNameBlank.getText();
                m_inputStringPassword = l_passwordBlank.getText();

                m_tmpUser.setInfo(m_inputStringUserName, m_inputStringPassword);
                try {
                    if(m_fileManager.addUser(m_tmpUser) == 1){ // if duplicate user
                        JOptionPane.showMessageDialog(null, "Error: User with that name already exists", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                    ChangeEvent event = new ChangeEvent(this);
                    for(ChangeListener listener : returnToLoginListener){
                        listener.stateChanged(event);
                    }
                } catch (IOException | ParseException e) {
                    JOptionPane.showMessageDialog(null, "Issue", "ERROR", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }


            }
        });

        l_returnToLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangeEvent event = new ChangeEvent(this);
                for(ChangeListener listener : returnToLoginListener){
                    listener.stateChanged(event);
                }
            }
        });


    }

    /**
     * Method: getRegisterDisplay
     * Description: Gets the register user display for the screen manager
     * @return returns the register display panel
     */
    public JPanel getRegisterDisplay(){
        return m_registerDisplay;
    }

    /**
     * Method: addListenerReturntoLogin
     * Description: Returns to login screen
     * @param listener
     */
    public void addListenerReturntoLogin(ChangeListener listener) {
        returnToLoginListener.add(listener);
    }

}
