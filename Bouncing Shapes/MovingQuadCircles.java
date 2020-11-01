/*
 *	===============================================================================
 *	MovingQuadCircles.java : A Subclass of the MovingCircle Subclass.
 *	A shape has a point (top-left corner).
 *	A shape defines various properties, including selected, colour, width and height.
 *	UPI: 445267172
 *	KENNY YEUNG
 *	===============================================================================
 */
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;
public class MovingQuadCircles extends MovingCircle{
    public MovingQuadCircles(){
        super();
        setHeight(50);
        setWidth(50);
    }
    
    public MovingQuadCircles(int height){
        super(height);
    }
    
	public MovingQuadCircles(int x, int y, int size, int marginwidth, int marginheight, Color bordercolor, Color fillcolor, int path) {
		super(x, y, size, marginwidth, marginheight, bordercolor, fillcolor, path);
	}
	
	
	public MovingQuadCircles(int x, int y, int width, int height, int marginwidth, int marginheight, Color bordercolor, Color fillcolor, int path) {
		super(x, y, width, height, marginwidth, marginheight, bordercolor, fillcolor, path);
	}
	
	public void draw(Graphics g){
		Ellipse2D ellipse1 = new Ellipse2D.Float(x + (width / 4), y, (width / 2), (height / 2));
		Ellipse2D ellipse2 = new Ellipse2D.Float(x + (width / 2), y + (height / 4), width / 2, height / 2);
		Ellipse2D ellipse3 = new Ellipse2D.Float(x + (width / 4), y + (height / 2), width / 2, height / 2);
		Ellipse2D ellipse4 = new Ellipse2D.Float(x, y + (height / 4), width / 2, height / 2);
		Area area1 = new Area(ellipse1);
		area1.intersect(new Area(ellipse2));//finds the intersections with the two circles
		Area area2 = new Area(ellipse2);
		area2.intersect(new Area(ellipse3));
		Area area3 = new Area(ellipse3);
		area3.intersect(new Area(ellipse4));
		Area area4 = new Area(ellipse4);
		area4.intersect(new Area(ellipse1));
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(fillColor);
		g2d.fill(area1);
		g2d.fill(area2);
		g2d.fill(area3);
		g2d.fill(area4);
		
        g2d.setPaint(borderColor);
        g2d.drawOval(x + (width / 4), y, (width / 2), (height / 2));
		g2d.drawOval(x + (width / 2), y + (height / 4), width / 2, height / 2);
		g2d.drawOval(x + (width / 4), y + (height / 2), width / 2, height / 2);
		g2d.drawOval(x, y + (height / 4), width / 2, height / 2);
		drawHandles(g2d);
		
		
		
    }
}