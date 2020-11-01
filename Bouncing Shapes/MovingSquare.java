/*
 *	===============================================================================
 *	MovingSquare.java : A Subclass of the MovingRectangle Superclass.
 *	A shape has a point (top-left corner).
 *	A shape defines various properties, including selected, colour, width and height.
 *	UPI: 445267172
 *	KENNY YEUNG
 *	===============================================================================
 */
import java.awt.*;
public class MovingSquare extends MovingRectangle{
    public MovingSquare(){
        super();
        setHeight(50);
        setWidth(50);
    }
    
    public MovingSquare(int height){
        super(height);
    }
    
	public MovingSquare(int x, int y, int size, int marginwidth, int marginheight, Color bordercolor, Color fillcolor, int path) {
		super(x, y, size, size, marginwidth, marginheight, bordercolor, fillcolor, path);
	}
	
	public MovingSquare(int x, int y, int width, int height, int marginwidth, int marginheight, Color bordercolor, Color fillcolor, int path) {
		super(x, y, width, height, marginwidth, marginheight, bordercolor, fillcolor, path);
		if(height <= width){
			setHeight(height);
			setWidth(height);
		}else{
			setHeight(width);
			setWidth(width);
		}
	}
    
	public void setHeight(int h){
		this.height = h;
		this.width = h;
	}
	
	public void setWidth(int w){
		this.width = w;
		this.height = w;
	}
}