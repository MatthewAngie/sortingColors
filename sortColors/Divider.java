package sortColors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Divider extends Rectangle2D.Double {

	public Divider(int xloc, int yloc)
	{
		super(xloc, yloc, 2, 800);
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.draw(this);
		g.fill(this);
	}
	
}
