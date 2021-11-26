import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
	
	
	public static void main(String[] args) {
		JFrame frame;
		// new 
	    JButton Restart ,Pause ;

        frame = new JFrame();
		Gameplay gamePlay = new Gameplay();
		// new 
		Restart = new JButton("Restart");
		Restart.addActionListener(e -> gamePlay.Restart());
        Restart.setFocusable(false);// new 

		// new 
		Pause = new JButton("Pause");
		Pause.addActionListener(e -> gamePlay.pause());
        Pause.setFocusable(false);// new 
		
		frame.setBounds(10, 10, 700, 670);
		frame.setTitle("Breakout Ball");		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(null); // new 

		gamePlay.setBounds(0, 0, 700, 670);// new 
		frame.add(gamePlay);
		frame.setVisible(true);

		// new 
		Restart.setBounds(600, 580, 80, 50);
		frame.add(Restart);

		// new 
		Pause.setBounds(510, 580, 80, 50);
		frame.add(Pause);
		frame.setVisible(true);
	}

}