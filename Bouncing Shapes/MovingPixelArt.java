/*
 *	===============================================================================
 *	MovingSPixelArt.java : A Subclass of the MovingSquare Subclass.
 *	A shape has a point (top-left corner).
 *	A shape defines various properties, including selected, colour, width and height.
 *	UPI: 445267172
 *	KENNY YEUNG
 *	===============================================================================
 */
import java.awt.*;
public class MovingPixelArt extends MovingSquare{
    public MovingPixelArt(){
        super();
        setHeight(50);
        setWidth(50);
    }
    
	public MovingPixelArt(int x, int y, int size, int marginwidth, int marginheight, Color bordercolor, Color fillcolor, int path) {
		super(x, y, size, marginwidth, marginheight, bordercolor, fillcolor, path);
	}
	
	public MovingPixelArt(int x, int y, int width, int height, int marginwidth, int marginheight, Color bordercolor, Color fillcolor, int path) {
		super(x, y, width, height, marginwidth, marginheight, bordercolor, fillcolor, path);
		if(height <= width){
			setHeight(height);
			setWidth(height);
		}else{
			setHeight(width);
			setWidth(width);
		}
	}
	
	public void draw(Graphics g){
		final Graphics2D g2d = (Graphics2D) g;
		String[] values = {"1111111111", "1111551111", "1111551111", "1115555111", "1115555111", "1155555511", "1555555551", "1111881111", "1111881111", "1111111111"};
		Color[] colours = {Color.black, Color.white, Color.red, Color.orange, Color.yellow, Color.green, Color.cyan, Color.blue, Color.gray, Color.DARK_GRAY};
		int oldX = x, countX = 0, countY = 0;

		for(String codes : values){
			int sizeY = (height / 10) * countY;
			for(int i = 0; i < codes.length(); i++){
				int sizeX = (width / 10) * countX;
				int index = Character.getNumericValue(codes.charAt(i));
				g2d.setPaint(colours[index]);
				g2d.fillRect(x + sizeX, y + sizeY, width / 10, height / 10);
				g2d.setPaint(colours[index]);
				g2d.drawRect(x + sizeX, y + sizeY, width / 10, height / 10);
				countX += 1;
			}
			countX = 0;
			x = oldX;
			countY += 1;
			
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