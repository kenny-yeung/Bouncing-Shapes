/*
 *	===============================================================================
 *	MovingRectangle.java : A Subclass of the MovingShape Subclass.
 *	A shape has a point (top-left corner).
 *	A shape defines various properties, including selected, colour, width and height.
 *	UPI: 445267172
 *	KENNY YEUNG
 *	===============================================================================
 */
import java.awt.*;
public class MovingRectangle extends MovingShape{
    public MovingRectangle(){
        super();
    }
    
    public MovingRectangle(int height){
        super(height);
    }
    
    public MovingRectangle(int x, int y, int width, int height, int marginwidth,int marginheight, Color bordercolor, Color fillcolor, int path) {
		super(x, y, width, height, marginwidth, marginheight, bordercolor, fillcolor, path);
	}
    
    public void draw(Graphics g){
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(fillColor);
        g2d.fillRect(x, y, width, height);
        g2d.setPaint(borderColor);
        g2d.drawRect(x, y, width, height);
        drawHandles(g2d);
    }
    
    public double getArea(){
        return this.width * this.height;
    }
    
    public boolean contains(Point p){
		int x = this.getX();
		int y = this.getY();
		int px = (int)p.getX();
		int py = (int)p.getY();
		int l = x + width;
		int h = y + height;
        if((((px > x) && (px < l)) && ((py > y) && (py < h)))){
            return true;
        }
        else {
        	return false;
        }
    }
}