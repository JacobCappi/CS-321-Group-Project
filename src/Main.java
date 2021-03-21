import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Main {


    public static  void main ( String []args ){
        JFrame test = new JFrame();
        test.setSize(500,500);
        test.setLocationRelativeTo(null);
        final LoginScreenDisplay userLogin = new LoginScreenDisplay();
        final cardTestDisplay userDisplay = new cardTestDisplay();
        final JPanel cardset= new JPanel(new CardLayout());
        cardset.add(userLogin.getLoginPanel(), " Login View");
        cardset.add(userDisplay.getJklsdhgisdhgsfdkhjgsfg(), "RANDOM");
        test.add(cardset);


        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);





    }
}
