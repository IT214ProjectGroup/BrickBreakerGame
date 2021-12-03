import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseMotionListener {
	private boolean play = false;
	private int score = 0;

	private int totalBricks = 48;

	private Timer timer;
	private int delay = 8;

	private int paddleX = 310;

	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	int lose = 0;

	//map rows and colomns 
	int mapR = 8;
	int mapC = 10; 

    //for changing the pause btn state 
	private int pauseT = 0;

	private MapGenerator map;
	
	public Gameplay() {
		map = new MapGenerator(mapR, mapC);

		// Moving the paddle using the Mouse
	    	addMouseMotionListener(this);
	    	setFocusable(false);

		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		// background
		g.setColor(Color.getColor("0, 0, 0, 71"));
		g.fillRect(1, 1, 692, 580);
		ImageIcon background = new ImageIcon("art.gif");
		Image img = background.getImage();
		g.drawImage(img, 0, 0, 740, 605, null);

		// drawing map
		map.draw((Graphics2D) g);

		// borders
		g.setColor(new Color(158, 216, 236));
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);

		// the scores
		g.setColor(Color.black);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("Score:" + score, 10, 30);

		// the paddle
		g.setColor(Color.white);
		g.fillRect(paddleX, 550, 100, 10);
		((Graphics2D) g).setStroke(new BasicStroke(4));
		((Graphics2D) g).setColor(new Color(164, 219, 235));
		((Graphics2D) g).drawRect(paddleX, 550, 100, 8);

		// the ball
		g.setColor(Color.WHITE);
		g.fillOval(ballposX, ballposY, 20, 20);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		((Graphics2D) g).setColor(Color.ORANGE);
		((Graphics2D) g).drawOval(ballposX, ballposY, 20, 20);

		// when you won the game
		if (totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won, Scores: " + score, 190, 400);

			// g.setColor(Color.RED);
			// g.setFont(new Font("serif", Font.BOLD, 20));
			// g.drawString("Press (Enter) to Restart", 230, 350);
		}

		// when you lose the game
		if (ballposY > 570 || lose == 1) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Scores: " + score, 190, 400);

			// g.setColor(Color.RED);
			// g.setFont(new Font("serif", Font.BOLD, 20));
			// g.drawString("Press (Enter) to Restart", 230, 350);
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				Restart(); // new // put it in a function for reusability
			}
		}
	}
   @Override
	public void mouseDragged(MouseEvent e) { // Drag the paddle
		paddleX = e.getX();
		if (paddleX < 10)
			paddleX = 10;
		else if (paddleX >= 600)
			paddleX = 600;
	}

	public void mouseMoved(MouseEvent e) { // dedect the mouse location
		paddleX = e.getX();
		if (paddleX < 10)
			paddleX = 10;
		else if (paddleX >= 600)
			paddleX = 600;
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
		totalBricks = mapC * mapR;
		map = new MapGenerator(mapR, mapC);
		lose = 0;
		repaint();
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
								score += 400;
								map.setBrickValue(0, 0, 0);
								totalBricks = 0;
							} else if (map.iRed == i && map.jRed == j) {
								if (i == map.map.length - 1 && j == map.map[0].length - 1) {
									score += totalBricks * 5;
									map = new MapGenerator(1, 1);
									map.setBrickValue(0, 0, 0);
									totalBricks = 0;
								} else { lose = 1; }
							} else {
								score += 5;
								map.setBrickValue(0, i, j);
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