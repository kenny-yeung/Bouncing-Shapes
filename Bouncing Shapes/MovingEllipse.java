/*
 *	===============================================================================
 *	MovingEllipse.java : A Subclass of the MovingShape Subclass.
 *	A shape has a point (top-left corner).
 *	A shape defines various properties, including selected, colour, width and height.
 *	UPI: 445267172
 *	KENNY YEUNG
 *	===============================================================================
 */
import java.awt.*;
public class MovingEllipse extends MovingShape{
    public MovingEllipse(){
        super();
    }
    
    public MovingEllipse(int height){
        super(height);
    }
    
    public MovingEllipse(int x, int y, int width, int height, int marginwidth,int marginheight, Color bordercolor, Color fillcolor, int path) {
		super(x, y, width, height, marginwidth, marginheight, bordercolor, fillcolor, path);
	}
    
    public void draw(Graphics g){
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(fillColor);
        g2d.fillOval(x, y, width, height);
        g2d.setPaint(borderColor);
        g2d.drawOval(x, y, width, height);
        drawHandles(g2d);
    }
    
    public double getArea(){
        return (width/2) * (height/2) * Math.PI;
    }
    
    public boolean contains(Point p){
		double dx,dy;
		Point EndPt = new Point(x + width, y + height);
		dx = (2 * p.x - x - EndPt.x) / (double) width;
		dy = (2 * p.y - y - EndPt.y) / (double) height;
		return dx * dx + dy * dy < 1.0;
}
}