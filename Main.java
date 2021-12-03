import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame;
		JButton Restart, Pause;// new

		// objects
		frame = new JFrame();
		Gameplay gamePlay = new Gameplay();
		// Restart button
		Restart = new JButton("Start | Restart");
		Restart.addActionListener(e -> gamePlay.Restart());
		Restart.setFocusable(false);// new

		// Pause button
		Pause = new JButton("Pause");
		Pause.addActionListener(e -> gamePlay.pause());
		Pause.setFocusable(false);// new
		// Frame
		frame.setBounds(10, 10, 700, 650);
		frame.setTitle("Breakout Ball");
		frame.setResizable(false);
		frame.setBackground(new Color(75, 75, 75));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePlay.setBounds(0, 0, 700, 670);// new

		frame.add(gamePlay);
		frame.add(Restart, BorderLayout.SOUTH);
		frame.add(Pause, BorderLayout.NORTH);
		frame.setVisible(true);
	}

}