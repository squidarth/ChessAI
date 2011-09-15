/**
 * The knight class provides functionality for knight pieces. 
 * It extends from the Piece class, but also contains 
 * additional field BufferedImage img, which stores the image
 * of a knight, which is instantiated in the Knight constructor.  
 */

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import java.awt.*;
import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.*;

public class Knight extends Piece {
	private BufferedImage img;
	
	/**
	 * The Knight(int x,int y, boolean color) constructor calls the Piece constructor with parameters x,y, which is 
	 * the location of the piece.  It also adds an image of a knight to the knight object.  Finally the color parameter
	 * is used to set the color of the piece, where true is black and false is white.
	 * @param x
	 * @param y
	 */
	public Knight(int x, int y, boolean color){
		super(x,y, color);
		img = null;
		if(super.getColor() == false){
			try {
			    img = ImageIO.read(new File("whiteknight.png"));
			} catch (IOException e) {
				System.out.println("file not found");
			}
		}else{
			try{
				img = ImageIO.read(new File("darkknight.png"));
			}catch(IOException e){
				System.out.println("filenotfound");
			}
		}
		
	}
	
	/**
	 * method checks what legal moves are possible from a given point in the chess board.
	 * 
	 * It iterates through all possible directions the rook could move to see if there are pieces in the way
	 * 
	 * @return true if move is legal, false if it is not legal
	 */
	@Override
	public boolean checkLegalMove(Point p, Board b) {
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
		//check the legal move
		/*
		 * the legal moves allowed for knights are ones where the change in x is 2 and the change in y is 1 or the other 
		 * way around, where the change in y is 2 and the change in x is 1.  Knights can jump over other pieces,
		 * so there is not need to check if other pieces are in the way.  
		 */
		if(Math.abs(newX - oldX) == 2 && Math.abs(newY - oldY) == 1 && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
			System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");

			return true;
		}else if(Math.abs(newX -oldX) == 1 && Math.abs(newY - oldY) == 2 && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
			System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");

			return true;
		}else{
			return false;
		}
	}

	/**
	 * the draw(Graphics g) method uses the Graphics method drawImage to draw the image 
	 * stored in img in the location of the piece.
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, super.getX(), super.getY(), null);

	}

	/**
	 * the getType() method returns the type of the piece, in this case "Knight"
	 * 
	 * @return the type of piece
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Knight";
	}

}
