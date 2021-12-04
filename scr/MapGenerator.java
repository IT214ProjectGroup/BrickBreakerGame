package scr;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.*;

public class MapGenerator {
    Clip break1;
	Clip break2;
	
	AudioInputStream A;
    AudioInputStream B;

	public int map[][];
	public int brickWidth;
	public int brickHeight;
	Random ran = new Random();
	public int iRed, jRed, iGreeen, jGreen;

	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 2;
			}
		}

		brickWidth = 540 / col;
		brickHeight = 200 / row;

		iRed = ran.nextInt(map.length);
		jRed = ran.nextInt(map[0].length);
		iGreeen = ran.nextInt(map.length);
		jGreen = ran.nextInt(map[0].length);
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					if (i == iGreeen && j == jGreen)
						g.setColor(new Color(79,251,223));
					else if (i == iRed && j == jRed)
						g.setColor(new Color(205,63,44));
					//change the color after second hit 
					else if (map[i][j] >= 1 && map[i][j] != map[iGreeen][jGreen] && map[i][j] !=map[iRed][jRed]){
					g.setColor(new Color(177,168,185));
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				     }
					 //first color of bricks	
                    else 		
					g.setColor(Color.WHITE);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

					// this is just to show separate brick, game can still run without it
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	public void setBrickValue(int value, int row, int col) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (map[row][col] == 2){
			map[row][col] = 1;
             Audio2();            
		}else
		map[row][col] = value;
             Audio();
	}  
     public void Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{ 
        File file = new File("Audio/audioForFirstHit.wav");
        A = AudioSystem.getAudioInputStream(file);
        break1=AudioSystem.getClip();
        break1.open(A);
        break1.start();
        break1.setMicrosecondPosition(0);
	}
        
     public void Audio2() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{  
        File file2 = new File("Audio/audioForSecondHit.wav");
        B= AudioSystem.getAudioInputStream(file2);
        break2=AudioSystem.getClip();
        break2.open(B);
        break2.start();
        break2.setMicrosecondPosition(0);
	}
}
