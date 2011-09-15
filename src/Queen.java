/**
 * The queen class provides functionality for queen pieces. 
 * It extends from the Piece class, but also contains 
 * additional field BufferedImage img, which stores the image
 * of a queen, which is instantiated in the Queen constructor.  
 */
import java.awt.*;
import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.*;


public class Queen extends Piece {
	private BufferedImage img;
	
	/**
	 * The Queen(int x,int y, boolean color) constructor calls the Piece constructor with parameters x,y, which is 
	 * the location of the piece.  It also adds an image of a queen to the queen object.  Finally the color parameter
	 * is used to set the color of the piece, where true is black and false is white.
	 * @param x
	 * @param y
	 * @param color
	 */
	public Queen(int x, int y, boolean color){
		super(x,y, color);
		img = null;
		if(super.getColor() == false){
			try {
			    img = ImageIO.read(new File("whitequeen.png"));
			} catch (IOException e) {
				System.out.println("file not found");
			}
		}else{
			try{
				img = ImageIO.read(new File("blackqueen.png"));
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
		 * combines rules for rook and bishop, allows for either scenario to occur (move in change in x/change in y = 1 pattern)
		 * or horizontal/vertical pattern.
		 * 
		 * 
		 * also checks if piece is still on the board, and makes checks to see if there are any other pieces in the way 
		 */
		if((oldX == newX) && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
			/*
			 * For all possible patterns of movement, the Queen iterates through the board to check
			 * if there are any pieces in the way in that direction
			 */
			if(newY > oldY){
				for(int i = 1; i< newY-oldY; i++){ 
					if(b.hasPiece(oldX, oldY + i)){ 
						return false;
					}
				}
				return true;
			}
			if(newY < oldY){
				for(int i= 1; i<oldY - newY; i++){
					if(b.hasPiece(oldX, newY + i)){
						return false;
					}
				}
				return true;
			}
			if(newY == oldY){
				return true;
			}
			return true;
		}
		if((oldY == newY) && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
			if(newX > oldX){
				for(int i = 1; i<newX-oldX; i++){
					if(b.hasPiece(oldX + i, oldY)){
						return false;
					}
				}
				return true;
			}
			
			if(newX < oldX){
				for(int i = 1; i<oldX-newX; i++){
					if(b.hasPiece(newX + i, oldY)){
						return false;
					}
				}
				return true;
			}
			if(newX == oldX){
				return true;
			}
		}
		if(Math.abs(newX - oldX) == Math.abs(newY - oldY) && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
			if(newX - oldX > 0 && newY - oldY > 0){
				for(int i= 1; i< newX-oldX; i++){
					if(b.hasPiece(oldX + i,oldY + i)){
						return false;
					}
				}
				return true;
			}
			if(newX - oldX < 0 && newY - oldY < 0){
				for(int i = 1; i< oldX - newX; i++){
					if(b.hasPiece(newX + i, newY + i)){
						return false;
					}
				}
				return true;
			}
			if(((newX - oldX) > 0) && ((newY - oldY) < 0)){
				for(int i = 1; i< newX - oldX; i++){
					if(b.hasPiece(oldX + i, oldY - i)){
						return false;
					}
				}
				return true;
			}
			if(newX - oldX < 0 && newY - oldY > 0){
				for(int i = 1; i< Math.abs(newX - oldX); i++){
					if(b.hasPiece(oldX -i, oldY + i)){
						return false;
					}
				}
				return true;
			}
			return true;
		}
		return false;
	}
	/**
	 * The getType() method returns the type of the piece, in this case "Queen"
	 */
	public String getType(){
		return "Queen";
	}

}
