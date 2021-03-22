import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {


    public static  void main ( String []args ) throws FileNotFoundException {
        JFrame testFrame = new JFrame();

        testFrame.setSize(500,500);
        testFrame.setLocationRelativeTo(null);
        final LoginScreenDisplay userLogin = new LoginScreenDisplay();
        final cardTestDisplay userDisplay = new cardTestDisplay();
        final JPanel cardset= new JPanel(new CardLayout());
        cardset.add(userLogin.getLoginPanel(), " Login View");
        cardset.add(userDisplay.getJklsdhgisdhgsfdkhjgsfg(), "RANDOM");
        testFrame.add(cardset);


        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
        userLogin.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent  e){
                CardLayout cl = (CardLayout) (cardset.getLayout());
                cl.show(cardset,  "RANDOM");
            }

        });


    }
}
