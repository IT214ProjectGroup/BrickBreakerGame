import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame obj=new JFrame(); 
		Gameplay gamePlay = new Gameplay();
		
		//functions for fram from Jfram package 
		obj.setBounds(10, 10, 700, 600); // what x ,y means??
		obj.setTitle("Breakout Ball"); // Just title 
		obj.setResizable(false); //  can't change the window size 
		obj.setVisible(true); // display the window 
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // an action apper on X button 

		obj.add(gamePlay); // it must be a panel ? what is th epanel ? 
        obj.setVisible(true);// display the window agine ? why ? 
		
	}

}
