import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseMotionListener {

	Clip audiowon;
	Clip Audiolose;
	
    AudioInputStream D;        
    AudioInputStream F;
	private boolean play = false;
	private int score = 0;



	private Timer timer;
	private int delay = 8;

	private int paddleX = 310;

	private int ballposX = 120;
	private int ballposY = 350;
	private float ballXdir = -1;
	private float ballYdir = -2;

	int lose = 0;

	//map rows and colomns 
	int mapR = 5;
	int mapC = 4; 
	private int totalBricks =mapC * mapR;
    //for changing the pause btn state 
	private int pauseT = 0;

	private MapGenerator map;
	
	public Gameplay() {
		map = new MapGenerator(mapR, mapC);
       //Moving the paddle using the keys
		addKeyListener(this);
		setFocusable(true);

	// Moving the paddle using the Mouse
		addMouseMotionListener(this);
		setFocusable(true);

		setFocusTraversalKeysEnabled(true);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 580);
		ImageIcon background = new ImageIcon("image/wonder.gif");
		Image img = background.getImage();
		g.drawImage(img, 0, 0, 700, 600, null);

		// drawing map
		map.draw((Graphics2D) g);

		// borders
		g.setColor(new Color(177,168,185));
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(683, 0, 3, 592);

		// the scores
		g.setColor(new Color(177,168,185));
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("Score:" + score, 10, 30);

		// the paddle
		g.setColor(Color.white);
		g.fillRect(paddleX, 550, 100, 10);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.setColor(new Color(32, 7, 73));
		g.drawRect(paddleX, 550, 100, 8);

		// the ball
		g.setColor(Color.WHITE);
		g.fillOval(ballposX, ballposY, 20, 20);
		((Graphics2D) g).setStroke(new BasicStroke(4));
		g.setColor(new Color(244,180,73));
		g.drawOval(ballposX, ballposY, 20, 20);

		// when you won the game
		if (totalBricks <= 2) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			if(score >1000){
				g.setColor(new Color(79,251,223));
				g.setFont(new Font("serif", Font.BOLD, 40));
				g.drawString("You are LUCKY !!", 200, 350);
				g.setFont(new Font("serif", Font.BOLD, 20));
				g.drawString("press E for easy & H for hard", 230, 400);
			}else{
				g.setColor(new Color(79,251,223));
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("You Won", 280, 350);
				g.drawString("Scores: " + score, 290, 400);
				g.setFont(new Font("serif", Font.BOLD, 20));
				g.drawString("press E for easy & H for hard", 230, 450);
			}
			try {
				AudioWon();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
				Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		// when you lose the game
		if (ballposY > 570 || lose == 1) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(new Color(223,0,0));
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over", 280, 350);
			g.drawString("Scores: " + score, 290, 400);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("press E for easy & H for hard", 230, 450);
			try {
				AudioLose();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
				Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		g.dispose();
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (paddleX >= 600) {
				paddleX = 600;
			} else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (paddleX < 10) {
				paddleX = 10;
			} else {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_H) {
		   hard();
		}
		if (e.getKeyCode() == KeyEvent.VK_E) {
           Restart();
		} 
	}
	// Drag the paddle
	@Override
	public void mouseDragged(MouseEvent e) { 
		paddleX = e.getX();
		if (paddleX < 10)
			paddleX = 10;
		else if (paddleX >= 600)
			paddleX = 600;
	}
    // dedect the mouse location
     @Override
	public void mouseMoved(MouseEvent e) { 
		paddleX = e.getX();
		if (paddleX < 10)
			paddleX = 10;
		else if (paddleX >= 600)
			paddleX = 580;
	}
	// pause btn fun
	public void pause() {
		if (pauseT % 2 == 0) {
			timer.stop();
		} else
			timer.start();

		pauseT++;
	}
	public void Restart() {
		play = true;
		ballposX = 120;
		ballposY = 350;
		ballXdir = -1;
		ballYdir = -2;
		paddleX = 310;
		score = 0;
		mapR = 5;
		mapC = 4;
		totalBricks = mapC * mapR;
		map = new MapGenerator(mapR, mapC);
		lose = 0;
		repaint();
	}
	public void hard() {
		play = true;
		ballposX = 120;
		ballposY = 350;
		ballXdir = -3;
		ballYdir = -9;
		paddleX = 310;
		score = 0;
		mapR = 10 ;
		mapC = 8;
		totalBricks = mapC * mapR;
		map = new MapGenerator(mapR, mapC);
		lose = 0;
		repaint();
	}
    public void AudioWon() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		File whenwon;
		whenwon = new File("Audio/won2.wav");
		D = AudioSystem.getAudioInputStream(whenwon);
		audiowon=AudioSystem.getClip();
		audiowon.open(D);
		audiowon.start();
		audiowon.setMicrosecondPosition(0);
	}
	
    public void AudioLose() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
        File whenlose = new File("Audio/lose.wav");
        F = AudioSystem.getAudioInputStream(whenlose);
        Audiolose=AudioSystem.getClip();
        Audiolose.open(F);
        Audiolose.start();
        Audiolose.setMicrosecondPosition(0);
	}
	// useless but needed
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	public void moveRight() {
		play = true;
		paddleX += 20;
	}

	public void moveLeft() {
		play = true;
		paddleX -= 20;
	}

	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(paddleX, 550, 30, 8))) {
				ballYdir = -ballYdir;
				ballXdir = -2;
			} else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(paddleX + 70, 550, 30, 8))) {
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			} else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(paddleX + 30, 550, 40, 8))) {
				ballYdir = -ballYdir;
			}

			// check map collision with the ball
			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							if (map.iGreeen == i && map.jGreen == j) {
								score += 1000;
                            	try {
									map.setBrickValue(0, i, j);
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
									Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
								}
								totalBricks = 0;
							} else if (map.iRed == i && map.jRed == j) {
								if (i == map.map.length - 1 && j == map.map[0].length - 1) {
									
									try {
										map.setBrickValue(0, i, j);
									} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
										Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
									}
									totalBricks = 0;
								} else { lose = 1; }
							} else {
								score += 5;
								try {
									map.setBrickValue(0, i, j);
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
									Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
								}
								totalBricks--;
							}

							// when ball hit right or left of brick
							if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else {
								ballYdir = -ballYdir;
							}

							break A;
						}
					}
				}
			}

			ballposX += ballXdir;
			ballposY += ballYdir;

			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 670) {
				ballXdir = -ballXdir;
			}
			repaint();
		}
	}
}

