import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator 
{
	public int map[][];
	public int brickWidth; 
	public int brickHeight;
	
	public MapGenerator (int row, int col) 
	{
		map = new int[row][col];
		// get brick value 		
		for(int i = 0; i<map.length; i++) //row
		{
			for(int j =0; j<map[0].length; j++) //col
			{
				map[i][j] = 1; //1 dedect that this patcular break has not interact with the ball
			}//no panle ? =0 ???			
		}
		
		//map size 
		brickWidth = 540/col;
		brickHeight = 150/row; 
	}	
	//draw the bricks 
	public void draw(Graphics2D g)
	{
		for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				if(map[i][j] > 0)
				{
					g.setColor(Color.white);//color of bricks 
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight); //fill the rectangls, i can make it Oval  
					//j * brickWidth + 80, i * brickHeight + 50 for the start point 


					// this is just to show separate brick, game can still run without it
					g.setStroke(new BasicStroke(3)); //for the outlines of graphics
					g.setColor(Color.blue); // color of borders 
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);	 //draw the borders 
				}
			}
		}
	}
	 
	// to set brick val
	public void setBrickValue(int value, int row, int col)
	{
		map[row][col] = value;
	}
}