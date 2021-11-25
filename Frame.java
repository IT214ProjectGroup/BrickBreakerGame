
import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame{
    
    JButton Pause ;

    Frame(){  
    Gameplay gamePlay = new Gameplay();

    this.setBounds(10, 10, 700, 600);
    this.setTitle("Breakout Ball");		
    this.setResizable(false);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(gamePlay);
            this.setVisible(true); 
        }
}
