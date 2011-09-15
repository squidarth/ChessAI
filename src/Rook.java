/**
 * The rook class provides functionality for rook pieces. 
 * It extends from the Piece class, but also contains 
 * additional field BufferedImage img, which stores the image
 * of a rook, which is instantiated in the Rook constructor.  
 */

import java.awt.*;
import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.*;

public class Rook extends Piece {
	private BufferedImage img; //stores image object
	
	/**
	 * The Rook(int x,int y, boolean color) constructor calls the Piece constructor with parameters x,y, which is 
	 * the location of the piece.  It sets the color of the piece to the color specified in the parameter.  It also adds an image 
	 * of a rook to the rook object.  
	 * @param x
	 * @param y
	 * @param color
	 */
	public Rook(int x, int y, boolean color){
		super(x,y,color);
		img = null;
		if(super.getColor() == false){
			try {
			    img = ImageIO.read(new File("whiterook.png"));
			} catch (IOException e) {
				System.out.println("file not found");
			}
		}else{
			try{
				img = ImageIO.read(new File("blackrook.png"));
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
	 * method checks what legal moves are possible from a given point in the chess board.
	 * 
	 * It iterates through all possible directions the rook could move to see if there are pieces in the way
	 * 
	 * @return true if move is legal, false if it is not legal
	 */
	public boolean checkLegalMove(Point p, Board b){
		int oldX = (int)super.getSquareOn().getX(); //old position on board
		int oldY = (int)super.getSquareOn().getY();
		int newX = (int)p.getX()/62; //new position on board
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
		 * Checks if piece is still in line with either the x or the y and makes sure that
		 * the piece is still on the board.  
		 */
		if((oldX == newX) && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
			if(newY > oldY){ //checks if there are any pieces in the way if the rook is moving down
				for(int i = 1; i< newY-oldY; i++){ //iterates through board to check for pieces
					if(b.hasPiece(oldX, oldY + i)){
						return false;
					}
				}
				return true;
			}
			if(newY < oldY){ //checks if there are pieces in the way if the rook moves up
				for(int i= 1; i<oldY - newY; i++){ //iterates through board, checks for pieces
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
			if(newX > oldX){ //checks if there are pieces in the way if rook moves right
				for(int i = 1; i<newX-oldX; i++){ //iterates through board checking for pieces
					if(b.hasPiece(oldX + i, oldY)){
						return false;
					}
				}
				return true;
			}
			
			if(newX < oldX){ //checks if there are pieces in teh way if the rook moves left
				for(int i = 1; i<oldX-newX; i++){ //iterates through board
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
		return false;
	}
	
	/**
	 * the getType() method returns the type of the piece, in this case "Rook"
	 * 
	 * @return the type of piece
	 */
	public String getType(){
		return "Rook";
	}

}
