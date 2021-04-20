package com.cs_group.panels;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import com.cs_group.managers.FileManager;
import com.cs_group.objects.User;
import org.json.simple.parser.ParseException;

/**
 * Class: LoginScreenDisplay
 * Description: Creates the login ScreenDisplay so that the user can enter information and log in.
 */
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
    ArrayList<ChangeListener> Loginlistener = new ArrayList<>();
    ArrayList<ChangeListener> RegisterListener = new ArrayList<>();

    FileManager m_fileManager = new FileManager();

    /**
     * Constructor: Login Screen Display
     * @param user : user Parameter that the panel can use the login information given by the user into a new user Class  to attempt to log them in
     * @throws IOException
     * @throws ParseException
     */
    public LoginScreenDisplay(User user) throws IOException, ParseException {;

        //ActionListener the gets the information from the panel and sets the user information on Login button click
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sets the user Information
                user.setName(usernameTextField.getText());
                user.setPassword(passwordTextField.getText());

                //if the the login function is true.
                try{
                    if(m_fileManager.isRegisteredUser(user)){
                        ChangeEvent event = new ChangeEvent(this);
                        m_fileManager.loadUser(user); //loads the user's Lists
                        for (ChangeListener listener : Loginlistener)
                            listener.stateChanged(event);
                    }
                    else{
                        //prints an error message
                        JOptionPane.showMessageDialog(null, "Login Failed\nUsername or Password may be incorrect", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ParseException ioException) {
                    ioException.printStackTrace();
                }


            }
        });

        //actionListener that will take the user to the RegisterUser Screen
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : RegisterListener)
                    listener.stateChanged(event);
            }
        });
    }

    /**
     * Method: getLoginPanel
     * Description: function that returns the Jpanel of the Login view
     * @return the Jpanel of the LoginView
     */
    public JPanel getLoginPanel (){
        return loginPanel;
    }

    /**
     * Method: addLoginUserListener
     * Description: Adds the listener from loginButton to the LoginListener ArrayList
     * @param listener takes in a changeListener so that it can be added to the arraylist
     */
    public void addLoginUserListener(ChangeListener listener) {
        Loginlistener.add(listener);
    }

    /**
     * Method: addToRegisterUserListener
     * Description: Adds the listener from registerButton to the RegisterListener ArrayList
     * @param listener takes in a changeListener so that it can be added to the arraylist
     */
    public void addRegisterUserListener(ChangeListener listener) {
        RegisterListener.add(listener);
    }

}

