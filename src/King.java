/**
 * The king class provides functionality for king pieces. 
 * It extends from the Piece class, but also contains 
 * additional field BufferedImage img, which stores the image
 * of a bishop, which is instantiated in the King constructor.  
 */
import java.awt.*;
import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.*;

public class King extends Piece {
	private BufferedImage img; //stores image object
	
	/**
	 * The King(int x,int y, boolean color) constructor calls the Piece constructor with parameters x,y, which is 
	 * the location of the piece.  It also adds an image of a king to the king object.  Finally the color parameter
	 * is used to set the color of the piece, where true is black and false is white.
	 * @param x
	 * @param y
	 */
	public King(int x, int y, boolean color){
		super(x,y,color);
		img = null;
		if(super.getColor() == false){
			try {
			    img = ImageIO.read(new File("whiteking.png"));
			} catch (IOException e) {
				System.out.println("file not found");
			}
		}else{
			try{
				img = ImageIO.read(new File("blackking.png"));
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
		 * Checks if the difference between the new x and old x and new y and old y is less then 1 (it only moved one square
		 * and that the piece is still on the board
		 */
		if(Math.abs(newX - oldX) <= 1 && Math.abs(newY - oldY) <=1 && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
			System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");

			return true;
		}
		/*
		 * Allow for castling
		 */							
		if(super.getColor() == false){ //case that piece is white
			if(b.hasPiece(7, 7)){
				if(b.getSquare(7,7).getType().equals("Rook")){ //checks if there is rook in correct position
					if(oldX == 4 && oldY == 7 && newX == 6 && newY ==7){
						if(!b.hasPiece(5, 7) && !b.hasPiece(6,7)){ //checks if proper castling rules followed
							return true;
						}
					}
				}
			}
			//applies same reasoning to a black king, hardcoded positions for rook and king used
		}else if(super.getColor() == true){ //case that piece is black
			if(b.hasPiece(7, 0)){
				if(b.getSquare(7, 0).getType().equals("Rook")){
					if(oldX == 4 && oldY == 0 && newX == 6 && newY ==0){
						if(!b.hasPiece(5, 0) && !b.hasPiece(6,0)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * The getType() method returns the type of the piece, in this case "King"
	 */
	public String getType(){
		return "King";
	}

}
