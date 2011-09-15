/**
 * This class contains all the data necessary for an AI player to make a move
 * 
 * All point references refer the piece's position on the board
 * @author sid123
 *
 */
public class Move {
	private int oldX;  //oldX and oldY keep track of where the the piece is coming from
	private int oldY;
	private int newX;  //newX and newY keep track of where the piece is going
	private int newY;
	private Piece p; //p is a copy of the piece that is to be moved
	
	/**
	 * The Move constructor takes the old position and new position of the piece in the 
	 * form of oldX, oldY, newX, and newY as parameters in addition
	 * to the piece that is to be moved.  The constructor makes a copy of the piece
	 * and sets it to p. 
	 * @param oldX
	 * @param oldY
	 * @param newX
	 * @param newY
	 * @param piece
	 */
	public Move(int oldX, int oldY, int newX, int newY, Piece piece){
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
		
		//makes a copy of the piece and assigns it to p
		/*
		 * Note that a copy is used to prevent piece positions from being moved around
		 * when the AI is making hypothetical moves
		 */
		if(piece.getColor() == false){ //case that piece is white
			if(piece.getType() == "Queen"){
				p = new Queen(piece.getX()/62,piece.getY()/62,false);
			}else if(piece.getType() == "Rook"){
				p = new Rook(piece.getX()/62,piece.getY()/62,false);
			}else if(piece.getType() == "Knight"){
				p = new Knight(piece.getX()/62,piece.getY()/62,false);
			}else if(piece.getType() == "Pawn"){
				p = new Pawn(piece.getX()/62,piece.getY()/62,false);
			}else if(piece.getType() == "Bishop"){
				p = new Bishop(piece.getX()/62, piece.getY()/62, false);
			}else if(piece.getType() == "King"){
				p = new King(piece.getX()/62,piece.getY()/62,false);
			}
		}else if(piece.getColor() == true){ //case that piece is black
			if(piece.getType() == "Queen"){
				p = new Queen(piece.getX()/62,piece.getY()/62,true);
			}else if(piece.getType()== "Bishop"){
				p = new Bishop(piece.getX()/62,piece.getY()/62,true);
			}else if(piece.getType() == "Rook"){
				p = new Rook(piece.getX()/62,piece.getY()/62,true);
			}else if(piece.getType() == "Knight"){
				p = new Knight(piece.getX()/62,piece.getY()/62,true);
			}else if(piece.getType() == "Pawn"){
				p = new Pawn(piece.getX()/62,piece.getY()/62,true);
			}else if(piece.getType() == "King"){
				p = new King(piece.getX()/62,piece.getY()/62,true);
			}
		}
	}
	
	/**
	 * getOldX returns the old x position of the piece
	 * @return the old x position of the piece
	 */
	public int getOldX(){
		return oldX;
	}
	
	/**
	 * getOldY() returns the old y position of the piece
	 * @return the old y-position of the piece
	 */
	public int getOldY(){
		return oldY;
	}
	
	/**
	 * getNewX() returns the x-coordinate of the position the piece is moving to
	 * @return the x-coordinate of the position the piece is moving to
	 */
	public int getNewX(){
		return newX;
	}
	
	/**
	 * getNewY() returns the y-coordinate of the position that the piece is moving to
	 * @return the y-coordinate of the position the piece is moving to.
	 */
	public int getNewY(){
		return newY;
	}
	
	/**
	 * getPiece() returns p, which is a copy of the piece that is to be moved
	 * @return copy of piece that is to be moved
	 */
	public Piece getPiece(){
		return p;
	}
	
	/**
	 * toString() returns as a string the move that is made
	 * 
	 * @return the move that was made as a String
	 */
	public String toString(){
		String text = p.getType() + " moved from (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")";
		return text;
	}
}
