// package scr;

import java.awt.BorderLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

import javax.swing.JButton;
// import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame;
		JButton Restart;
		JButton Pause;// new
		// String [] levels = {"Easy", "Hard"};
		// JComboBox<String> comboBox = new JComboBox<>(levels);
        // comboBox.addItem("easy");
        // comboBox.addItem("hard");
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePlay.setBounds(0, 0, 700, 600);// new
		
		// comboBox.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent event) {
        //         // JComboBox comboBox = (JComboBox) event.getSource();
        //         Object selected = comboBox.getSelectedItem();
        //         if(selected.equals("Easy"))
        //         	gamePlay.Restart();
        //         else if(selected.equals("Hard"))
        //         	gamePlay.hard();
        //     }
        // });

		frame.add(gamePlay);
		frame.add(Restart, BorderLayout.SOUTH);
		frame.add(Pause, BorderLayout.NORTH);
		frame.setVisible(true);
	}
	

}
