import javax.swing.*;

public class testDisplay extends JPanel{
    private JPanel test;
    private JLabel test1;
    private JLabel test2;
    private JLabel test3;
    FileManager manager = new FileManager();
    Game Gabe = new Game();

 public void setGameDisplay(String title, String  genre, String publisher){
    test1.setText(title);
    test2.setText(genre);
    test3.setText(publisher);
 }



  public JPanel getTest(){return test;}
}

