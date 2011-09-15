/**
 * The bishop class provides functionality for bishop pieces. 
 * It extends from the Piece class, but also contains 
 * additional field BufferedImage img, which stores the image
 * of a bishop, which is instantiated in the Bishop constructor.  
 */
import java.awt.*;
import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.*;

public class Bishop extends Piece {
	private BufferedImage img;
	
	/**
	 * The Bishop(int x,int y, boolean color) constructor calls the Piece constructor with parameters x,y, which is 
	 * the location of the piece.  It also adds an image of a bishop to the bishop object.  Finally the color parameter
	 * is used to set the color of the piece, where true is black and false is white.
	 * @param x
	 * @param y
	 * @param color
	 */
	public Bishop(int x, int y, boolean color){
		super(x,y, color);
		img = null;
		if(super.getColor() == false){
			try {
			    img = ImageIO.read(new File("whitebishop.png"));
			} catch (IOException e) {
				System.out.println("file not found");
			}
		}else{
			try{
				img = ImageIO.read(new File("blackbishop.png"));
			}catch(IOException e){
				System.out.println("filenotfound");
			}
		}
	}
	/**
	 * the draw(Graphics g) method uses the Graphics method drawImage to draw the image 
	 * stored in img in the location of the piece.
	 */
	public void draw(Graphics g){
		g.drawImage(img, super.getX(), super.getY(), null);
	}
	
	/**
	 * method checks what legal moves are possible from a given point in the chess board
	 */
	public boolean checkLegalMove(Point p, Board b){
		int oldX = (int)super.getSquareOn().getX();
		int oldY = (int)super.getSquareOn().getY();
		int newX = (int)p.getX()/62;
		int newY = (int)p.getY()/62;
		
		/*
		 * checks if the square being moved to has a piece of the same color
		 */
		if(b.hasPiece(newX, newY)){
			if(b.getSquare(newX, newY).getColor() == super.getColor()){
				return false;
			}
		}
		/*
		 *checks if piece moved in a rise/run = 1 pattern by making sure that change in y = change in x
		 *and also checks that the piece is still on the board. Also runs through board and checks that there
		 *are no pieces in the way of the piece getting through
		 *
		 *Iterates through all possible directions the Bishop can move to ensure there are no other pieces in 
		 *its path
		 */
		
		
		if(Math.abs(newX - oldX) == Math.abs(newY - oldY) && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){ //case that it moved in the correct pattern
			if(newX - oldX > 0 && newY - oldY > 0){ //checks if moved to the right and down, iterates through board,
				for(int i= 1; i< newX-oldX; i++){   // finds if there are any pieces in the way
					if(b.hasPiece(oldX + i,oldY + i)){
						return false;
					}
				}
				return true;
			}
			if(newX - oldX < 0 && newY - oldY < 0){ //checks if it moved left and up
				for(int i = 1; i< oldX - newX; i++){
					if(b.hasPiece(newX + i, newY + i)){
						return false;
					}
				}
				return true;
			}
			if(((newX - oldX) > 0) && ((newY - oldY) < 0)){ //checked if it moved right and up
				for(int i = 1; i< newX - oldX; i++){
					if(b.hasPiece(oldX + i, oldY - i)){
						return false;
					}
				}
				System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");

				return true;
			}
			if(newX - oldX < 0 && newY - oldY > 0){ //checks if it moved left and down
				for(int i = 1; i< Math.abs(newX - oldX); i++){
					if(b.hasPiece(oldX -i, oldY + i)){
						return false;
					}
				}
				return true;
			}
			return true;
		}
		return false; //return false if it went in wrong pattern
	}
	
	/**
	 * The getType() method returns the type of the piece, in this case "Bishop"
	 */
	public String getType(){
		return "Bishop";
	}
}
