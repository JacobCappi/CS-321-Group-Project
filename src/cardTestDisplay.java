import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class cardTestDisplay extends JPanel {
    private JPanel testingPanel;
    private JLabel testingLabel;
    final ArrayList<ChangeListener> listeners = new ArrayList<>();

    public JPanel getTestingPanel() {return testingPanel;}

}
