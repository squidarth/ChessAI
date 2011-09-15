import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import java.awt.*;
import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.*;



public class Pawn extends Piece {
	private BufferedImage img;
	
	/**
	 * The Pawn(int x,int y, boolean color) constructor calls the Piece constructor with parameters x,y, which is 
	 * the location of the piece.  It also adds an image of a pawn to the pawn object.  Finally the color parameter
	 * is used to set the color of the piece, where true is black and false is white.
	 * @param x
	 * @param y
	 */
	public Pawn(int x, int y, boolean color){
		super(x,y,color);
		img = null;
		if(super.getColor() == false){
			try {
			    img = ImageIO.read(new File("whitepawn.png"));
			} catch (IOException e) {
				System.out.println("file not found");
			}
		}else{
			try{
				img = ImageIO.read(new File("blackpawn.png"));
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
		
		if(super.getColor() == false){ //case that pawn is white
			if(oldY == 6){  //case that pawn is in original position
				//allows pawn to move two spaces ahead as long as there is no space in between
				if(Math.abs(newX - oldX) == 1 && newY == oldY -1 && b.hasPiece(newX, newY)){
					return true;
				}else if(newX == oldX && newY == oldY -1 && !b.hasPiece(newX, newY)){
					return true;
				}else if(newX == oldX && newY == oldY - 2 && !b.hasPiece(newX, newY)){
					return true;
				}
				return false;
			}else{//case pawn is nt in original position
				//check if pawn is capturing another piece
				if(Math.abs(newX - oldX) == 1 && newY == oldY -1 && b.hasPiece(newX, newY)){ 
					return true;
				//check that pawn is moving forward one square
				}else if(newX == oldX && newY == oldY -1 && !b.hasPiece(newX, newY)){
					return true;
				}
				return false;
			}
		}else{//case that pawn is black, repeat same rules as for white
			if(oldY == 1){
				if(Math.abs(newX - oldX) == 1 && newY == oldY +1 && b.hasPiece(newX, newY)){
					System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");

					return true;
				}else if(newX == oldX && newY == oldY +1 && !b.hasPiece(newX, newY)){
					System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");

					return true;
				}else if(newX == oldX && newY == oldY + 2 && !b.hasPiece(newX, newY)){
					System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");

					return true;
				}
				return false;
			}else{//case pawn is nt in original position
				if(Math.abs(newX - oldX) == 1 && newY == oldY +1 && b.hasPiece(newX, newY)){
					System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");

					return true;
				}else if(newX == oldX && newY == oldY +1 && !b.hasPiece(newX, newY)){
					System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");

					return true;
				}
				return false;
			}
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
	 * the getType() method returns the type of the piece, in this case "Pawn"
	 * 
	 * @return the type of piece
	 */
	@Override
	public String getType() {
		return "Pawn";
	}

}
