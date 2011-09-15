/**
 * The abstract class Piece holds a bunch of methods that help keep track of pieces on the board.
 * It has a number of methods for getting the location of pieces, what square pieces are on, for
 * checking if a point designated by pixels on the GUI corresponds to the piece, and checking the 
 * type of the piece (an abstract method).  The Piece also has a boolean color, for which black is true
 * and white is false.  
 * 
 * Note: the x,y location of each piece is designated in pixels, where (x,y) is the top left-hand corner 
 * of the piece.  
 */

import java.awt.*;
import java.awt.geom.*;

public abstract class Piece {
	
	private int x; //locations denoted are top-left hand corner of squares.
	private int y;
	private Point squareOn; //keeps track of what square the piece is on, in terms of squares on the board
	private boolean color; //true:black, false:white
	
	private boolean selected; //keeps track of if  a piece has been selected or not
	
	/**
	 * The constructor Piece(int x, int y, boolean color) takes a square on the board as parameter, sets the squareOn field
	 * to that value, and the x and y location to that value multiplied by 62, as each chess square is 62 pixels
	 * wide and tall.  
	 * 
	 * Selected is initially set to false.
	 * 
	 * @param x
	 * @param y
	 */
	public Piece(int x, int y, boolean color ){ //note that false is white and true is black for the color
		squareOn = new Point(x,y);
		this.x = x*62;
		this.y = y*62;
		this.selected = false;
		this.color = color;
	}
	
	/**
	 * The getSquareOn method returns the square that the piece is on as a point object
	 * @return Point, the square that the piece is on
	 */
	public Point getSquareOn(){
		return squareOn;
	}
	
	/**
	 * the setSelected(boolean selected) method sets the selected field to the parameter selected.  
	 * @param selected
	 */
	public void setSelected(boolean selected){
		this.selected =  selected;
	}
	
	/**
	 * The method isSelected returns whether or not the piece is selected.
	 * @return boolean, true if piece is selected, false if piece is not selected
	 */
	public boolean isSelected(){
		return selected;
	}
	
	/**
	 * the getLocation() method returns the location of the piece as a Point, with the pixel location x,y
	 * @return Point, the location of the piece
	 */
	public Point getLocation(){
		return new Point(x,y);
	}
	
	/**
	 * the getX() method returns the x value of the location of the piece
	 * @return int, the x value of the location of hte piece
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * the getY() method returns the y-value of the location of the piece.
	 * @return int, the y value of the location of the piece
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * The contains(Point2D p) method takes a Point2D as a parameter and checks if the point lies on the piece.
	 * 
	 * The method checks this by checking if if the point that the user clicked was between x and x+62 and between y and 
	 * y+62, as each square has width and height of 62 pixels
	 * @param p
	 * @return boolean, true if point is contained, false if it is not
	 */
	public boolean contains(Point2D p){
		return x<= p.getX() && (x + 62) >= p.getX() && y<= p.getY() && (y+62) >= p.getY();
	}
	
	/**
	 * The translate method is used when a piece is being dragged and it moves the piece
	 * by parameters dx and dy.
	 * 
	 * @param dx
	 * @param dy
	 */
	public void translate(int dx, int dy){
		x += dx;
		y += dy;
	}
	
	/**
	 * The setLocation(int row, int col) takes a row and column from the board as parameters,
	 * sets x and y to those multiplied by 62, and sets the squareOn point to that point.  
	 * 
	 * Used to set a piece on a given square
	 * @param row
	 * @param col
	 */
	public boolean setLocation(int row, int col){
		x = row*62;
		y  = col*62;
		squareOn.setLocation(row, col);
		return true; //for debug
	}
	
	public boolean getColor(){
		return color;
	}
	/**
	 * The getType() method returns the type of piece(ie. rook, king, bishop)
	 * @return String, the type of piece
	 */
	public abstract String getType();
	/**
	 * The draw method governs how to draw each piece
	 * @param g
	 */
	public abstract void draw(Graphics g);
	/**
	 * The checkLegalMove(Point p) method takes a point in pixels and
	 * determines whether the move is legal or not for the piece
	 * 
	 * @return boolean, true if move is legal, false if it is not
	 */
	public abstract boolean checkLegalMove(Point p, Board b);
}
