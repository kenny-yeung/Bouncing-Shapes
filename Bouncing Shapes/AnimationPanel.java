/*
 *	==========================================================================================
 *	AnimationPanel.java : Moves shapes around on the screen according to different paths.
 *	It is the main drawing area where shapes are added and manipulated.
 *	It also contains a popup menu to clear all shapes.
 *	YOUR UPI: 445267172
 *	KENNY YEUNG
 *	==========================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.Comparator;

public class AnimationPanel extends JComponent implements Runnable {
	private Thread animationThread = null;	// the thread for animation
	private ArrayList<MovingShape> shapes;				// the ArrayList which stores a list of shapes
	private int currentWidth=100, currentHeight=50, currentShapeType, currentPath; // the current shape type, current path type
	private Color currentBorderColor = Color.pink;	// the current border colour of a shape
	private Color currentFillColor = Color.blue;	// the current fill colour of a shape
	private int delay = 100;		 // the current animation speed
	JPopupMenu popup;				// popup menu

	 /** Constructor of the AnimationPanel
		*/
	public AnimationPanel() {
		shapes = new ArrayList<MovingShape>(); //create the ArrayList to store shapes
		Insets insets = getInsets();
		int marginWidth = getWidth() - insets.left - insets.right;
		int marginHeight = getHeight() - insets.top - insets.bottom;
		popup = new JPopupMenu(); //create the popup menu
		makePopupMenu();
		// add the mouse event to handle popup menu
		addMouseListener( new MouseAdapter() {
			public void mousePressed(MouseEvent e) { maybeShowPopup(e); }
			public void mouseReleased(MouseEvent e) { maybeShowPopup(e); }
			private void maybeShowPopup(MouseEvent e) {
				if (e.isPopupTrigger()) { popup.show(e.getComponent(), e.getX(), e.getY()); }
			}
			public void mouseClicked( MouseEvent e ) {
				if (animationThread != null) {	// if the animation has started, then
					boolean found = false;
					for (MovingShape currentShape: shapes)
						if ( currentShape.contains( e.getPoint()) ) { // if the mousepoint is within a shape, then set the shape to be selected/deselected
							currentShape.setSelected( ! currentShape.isSelected() );
							found = true;
						}
					if (!found) createNewShape(e.getX(), e.getY());
				}
			}
		});
	}

	/** create a new shape
	 * @param x	 the x-coordinate of the mouse position
	 * @param y	the y-coordinate of the mouse position
	 */
	protected void createNewShape(int x, int y) {
		// get the margin of the frame
		Insets insets = getInsets();
		int marginWidth = getWidth() - insets.left - insets.right;
		int marginHeight = getHeight() - insets.top - insets.bottom;
		// create a new shape dependent on all current properties and the mouse position
		switch (currentShapeType) { //complete this
		case 0:{
			shapes.add(new MovingRectangle(x, y, currentWidth, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
			break;
		}
		case 1:{
			if(currentWidth <= currentHeight){
				shapes.add(new MovingSquare(x, y, currentWidth, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
				break;
			}else{
				shapes.add(new MovingSquare(x, y, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
				break;
			}
		}
		case 2:{
			shapes.add(new MovingEllipse(x, y, currentWidth, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
			break;
		}
		case 3:{
			if(currentWidth <= currentHeight){
				shapes.add(new MovingCircle(x, y, currentWidth, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
				break;
			}else{
				shapes.add(new MovingCircle(x, y, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
				break;
			}
		}
		case 4:{
			if(currentWidth <= currentHeight){
				shapes.add(new MovingQuadCircles(x, y, currentWidth, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
				break;
			}else{
				shapes.add(new MovingQuadCircles(x, y, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
				break;
			}
		}
		case 5:{
			if(currentWidth <= currentHeight){
				shapes.add(new MovingPixelArt(x, y, currentWidth, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
				break;
			}else{
				shapes.add(new MovingPixelArt(x, y, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
				break;
			}
		}
		}
	}

	/** get sorted info of all shapes in the program
	 */
	public String getSortedInfo() {
		String sorted = "";
	    Collections.sort(shapes);
	    for(MovingShape s: shapes){
			sorted += s;
			sorted += "\n";
		}
		return sorted;
	}

	/** set the current shape type
	 * @param s	the new shape type
	 */
	public void setCurrentShapeType(int s) {
		currentShapeType = s;
	}

	/** set the current path type and the path type for all currently selected shapes
	 * @param t	the new path type
	 */
	public void setCurrentPathType(int index) {
		currentPath = index;
		for (MovingShape currentShape: shapes)
			if ( currentShape.isSelected())
				currentShape.setPath(index);
	}

	/** get the current width
	 * @return currentWidth - the width value
	 */
	public int getCurrentWidth() {
		return currentWidth;
	}

	/** get the current height
	 * @return currentHeight - the height value
	 */
	public int getCurrentHeight() {
		return currentHeight;
	}

	/** set the current width and the width for all currently selected shapes
	 * @param w		the new width value
	 */
	public void setCurrentWidth(int w) {
		currentWidth = w;
		for (MovingShape currentShape: shapes)
			if ( currentShape.isSelected())
				currentShape.setWidth(currentWidth);
	}

	/** set the current height and the height for all currently selected shapes
	 * @param h		the new height value
	 */
	public void setCurrentHeight(int h) {
		currentHeight = h;
		for (MovingShape currentShape: shapes)
			if ( currentShape.isSelected())
				currentShape.setHeight(currentHeight);
}

	/** get the current border colour
	 * @return currentBorderColor - the border colour value
	 */
	public Color getCurrentBorderColor() {
		return currentBorderColor;
	}

	/** get the current fill colour
	 * @return currentFillColor - the fill colour value
	 */
	public Color getCurrentFillColor() {
		return currentFillColor;
	}

	 /** set the current border colour and the border colour for all currently selected shapes
	 * @param bc		the new border colour value
	 */
	public void setCurrentBorderColor(Color bc) {
		currentBorderColor = bc;
		for (MovingShape currentShape: shapes)
			if ( currentShape.isSelected())
				currentShape.setBorderColor(currentBorderColor);
	}

	/** set the current fill colour and the border colour for all currently selected shapes
	 * @param bc	the new fill colour value
	 */
	public void setCurrentFillColor(Color fc) {
		currentFillColor = fc;
		for (MovingShape currentShape: shapes)
			if ( currentShape.isSelected())
				currentShape.setFillColor(currentFillColor);
	}

	/** reset the margin size of all shapes from our ArrayList
	*/
	public void resetMarginSize() {
		Insets insets = getInsets();
		int marginWidth = getWidth() - insets.left - insets.right;
		int marginHeight = getHeight() - insets.top - insets.bottom ;
		for (MovingShape currentShape: shapes)
			currentShape.setMarginSize(marginWidth,marginHeight );
	}

		/** remove all shapes from the ArrayList
	 */
	public void clearAllShapes() {
		shapes.clear();
	}

	/**	update the painting area
	 * @param g	the graphics control
	 */
	public void update(Graphics g){
		paint(g);
	}

	/**	move and paint all shapes within the animation area
	 * @param g	the Graphics control
	 */
	public void paintComponent(Graphics g) {
		for (MovingShape currentShape: shapes) {
			currentShape.move();
			currentShape.draw(g);
		}
	}
	// you don't need to make any changes after this line ______________
	/** create the popup menu for our animation program
	 */
	protected void makePopupMenu() {
		JMenuItem menuItem;
	 // clear all
		menuItem = new JMenuItem("Clear All");
		menuItem.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllShapes();
			}
		});
		popup.add(menuItem);
	 }
	/** change the speed of the animation
	 * @param newValue	 the speed of the animation in ms
	 */
	public void adjustSpeed(int newValue) {
		if (animationThread != null) {
			stop();
			delay = newValue;
			start();
		}
	}
	/**	When the "start" button is pressed, start the thread
	 */
	public void start() {
		animationThread = new Thread(this);
		animationThread.start();
	}
	/**	When the "stop" button is pressed, stop the thread
	 */
	public void stop() {
		if (animationThread != null) {
			animationThread = null;
		}
	}
	/** run the animation
	 */
	public void run() {
		Thread myThread = Thread.currentThread();
		while(animationThread==myThread) {
			repaint();
			pause(delay);
		}
	}
	/** Sleep for the specified amount of time
	 */
	private void pause(int milliseconds) {
		try {
			Thread.sleep((long)milliseconds);
		} catch(InterruptedException ie) {}
	}
}
