package sortColors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Doodad extends Ellipse2D.Double {
	
	public Color color;
	public int numColor;
	public int g;
	public int b;

	
	public Doodad(double x, double y, Color colour) // creates Doodad, sets color, and takes color G,B to form ID
	{
		super(x,y,20,20);
		color=colour;
		g= color.getGreen();
		b= color.getBlue();
		numColor=(b+255*g);
		
	}

	
	public void draw(Graphics2D g)
	{
		g.setColor(color);
		g.draw(this);
		g.fill(this);
	}
}
