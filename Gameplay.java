import java.awt.event.*; // for KeyListener 
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer; // for timer 
// extends JPanel for ????
// implements KeyListener for dedicting arraw keys 
// implements ActionListener for moving the ball ????

public class Gameplay extends JPanel implements KeyListener, ActionListener 
{
	private boolean play = false; // the game doesn't played by itself 
	private int score = 0; // start score 
	
	private int totalBricks = 48; // 4*12 = 48 line 35 
	//class 
	private Timer timer; // for the ball and how fast it should move ?????
	private int delay=8; // speed 

	//STARTING position for the slider 
	private int playerX = 310; 

	//STARTING position for the ball
	private int ballposX = 120; //120
	private int ballposY = 350; //350

	//STARTING diriction  for the ball
	private int ballXdir = -1;
	private int ballYdir = -2;

	//class variable ?  
	private MapGenerator map; 
	
	public Gameplay() //CONSTRUCTOR
	{		
		map = new MapGenerator(4, 12); //obj with start brickets number row & col 
		addKeyListener(this); // keyLestener obj
		setFocusable(true); // to get all atintion gor it ?
		setFocusTraversalKeysEnabled(false); //enable to us tab or enter key at beginingg 
        timer=new Timer(delay,this); // new time obj with( delay val & keyLestener obj )
		timer.start(); //  ????
	}
	
	public void paint(Graphics g) //draw the game 
	{    		
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592); // 1&1 start x&y 692&592 ending x&y  
		
		// drawing map by pass the obj g
		map.draw((Graphics2D) g);
		
		// borders of the window 
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// the scores 		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString(""+score, 590,30);
		
		// the slider
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8); //playerX changable, 550 const at the buttom, 100 width, 8 hight 
		
		// the ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		//ballposX, ballposY bcuz it's moving, 20, 20 size of it , fillOval for square shap
		// when i change the sise the affect remain the same as 20*20 ball ???
	
		// when you won the game
		if(totalBricks <= 0)
		{
			 play = false;
             ballXdir = 0; //srop the ball 
     		 ballYdir = 0; 
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("You Won", 260,300);
             
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,350);  
		}
		
		// when you lose the game
		if(ballposY > 570) //why 570 ?
        {
			 play = false; 
             ballXdir = 0;
     		 ballYdir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("Game Over, Scores: "+score, 190,300);
             
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,350);        
        }
		
		// g.dispose(); // ???????
	}	

	//from keyListener package 
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) // or 39 
		{        
			if(playerX >= 600) 
			{
				playerX = 600; // to not get out of the boundes
			}
			else
			{
				moveRight();
			}
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) // or 37 
		{          
			if(playerX < 10)
			{
				playerX = 10; // to not get out of the boundes
			}
			else
			{
				moveLeft();
			}
        }		
         
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play) // set defulte val 
			{
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;// its less than b4 
				map = new MapGenerator(3, 7);// its less than b4 
				
				repaint();
			}
			//can i add tap event ? 
        }		
	}
 //from keyListener package we didn't used it, but we must implement it  
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void moveRight()
	{
		play = true;
		playerX+=20;	
	}
	
	public void moveLeft()
	{
		play = true;
		playerX-=20;	 	
	}
	//form actionListener package  
	public void actionPerformed(ActionEvent e)  // auto call 
	{
		timer.start();  //?????
		if(play)
		{			
			// didn't explan these lines 
			// to dedect the slider boundary 
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = -2;
			}
			
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			}
			
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
			{
				ballYdir = -ballYdir;
			}
			
			//all ??????
			// check map collision with the ball 		
			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)
					{
						//scores++;
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{					
							map.setBrickValue(0, i, j);
							score+=5;	
							totalBricks--;
							
							// when ball hit right or left of brick
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else
							{
								ballYdir = -ballYdir;				
							}
							
							break A; // break from outer loop  
						}
					}
				}
			}

			//for moving the ball 
			ballposX += ballXdir;
			ballposY += ballYdir;

			//chack if the ball inside the boundary 
			//left bound
			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			//top bound
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			//right bound
			if(ballposX > 670)
			{
				ballXdir = -ballXdir;
			}		
			
			repaint(); // to repaint the the game after change ball and slider position 	
		}
	}
}