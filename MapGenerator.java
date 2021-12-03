import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class MapGenerator {

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
		brickHeight = 250 / row;
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
						g.setColor(Color.green);
					else if (i == iRed && j == jRed)
						g.setColor(Color.red);
					//change the color after one die 
					else if (map[i][j] >= 1 && map[i][j] != map[iGreeen][jGreen] && map[i][j] !=map[iRed][jRed]){
					g.setColor(new Color(195, 239, 249));
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				     }
                    else 	//first color		
					g.setColor(new Color(74, 109, 124));
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

					// this is just to show separate brick, game can still run without it
					g.setStroke(new BasicStroke(3));
					// g.setColor(new Color(75, 75, 75));
					g.setColor(Color.BLACK);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	public void setBrickValue(int value, int row, int col) {
		if (map[row][col] == 2){
			map[row][col] = 1;
		}else
		map[row][col] = value;
	}
}