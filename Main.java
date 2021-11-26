import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
	
	
	public static void main(String[] args) {
		JFrame frame;
	    JButton Restart ,Pause ;// new 

		//objects
        frame = new JFrame();
		Gameplay gamePlay = new Gameplay();
		// Restart button 
		Restart = new JButton("Restart");
		// Restart.setBounds(600, 580, 80, 50);
		Restart.addActionListener(e -> gamePlay.Restart());
        Restart.setFocusable(false);// new 

		// Pause button  
		Pause = new JButton("Pause");
		// Pause.setBounds(510, 580, 80, 50);
		Pause.addActionListener(e -> gamePlay.pause());
        Pause.setFocusable(false);// new 
		// Frame 
		frame.setBounds(10, 10, 700, 670);
		frame.setTitle("Breakout Ball");		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // frame.setLayout(null); // new 
		gamePlay.setBounds(0, 0, 700, 670);// new 
  
	
		frame.add(gamePlay);
      	// frame.add(Restart);
		  frame.add(Restart,BorderLayout.SOUTH);
		// frame.add(Pause);
		frame.add(Pause,BorderLayout.NORTH);
		frame.setVisible(true);
	}

}