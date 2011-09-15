/**
 * The board class keeps track of what pieces are on what square.  The board class 
 * has a number of methods for checking if a square has a piece, for getting the piece
 * on a particular square, and for setting pieces on squares.  The constructor constructs
 * a board object with a number of arbitrary pieces. A second constructor allows a board
 * to be constructed from an existing board object.
 * 
 *  private board is a  2D ArrayList used to keep track of the
 *  pieces on each board.  Every square with no piece is represented with a null object,
 *  while squares with pieces contain the piece
 * 
 * 
 * @author sid123
 *
 */

import java.util.*;

public class Board {
	private Piece[][] board;
	
	/**
	 * The public Board() constructor of the board class creates a board
	 * with full of null objects, and adds a few arbitrary pieces to the 
	 * board.  
	 */
	public Board(){
		
		board = new Piece[8][8]; //initializes board
		//this for loop interates through board and fills it with null objects
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				board[i][j] = null;
			}
		}
		//this adds pieces to the board
		setSquare(4,0,new King(4,0,true));
		setSquare(0,0,new Rook(0,0,true));
		setSquare(1,0,new Knight(1,0,true));
		setSquare(2,0,new Bishop(2,0,true));
		setSquare(3,0,new Queen(3,0,true));
		setSquare(5,0,new Bishop(5,0,true));
		setSquare(6,0,new Knight(6,0,true));
		setSquare(7,0, new Rook(7,0,true));
		for(int i = 0; i<8; i++){
			setSquare(i,1,new Pawn(i,1,true));
		}
		//white pieces
		setSquare(4,7,new King(4,7,false));
		setSquare(0,7,new Rook(0,7,false));
		setSquare(1,7,new Knight(1,7,false));
		setSquare(2,7,new Bishop(2,7,false));
		setSquare(3,7,new Queen(3,7,false));
		setSquare(5,7, new Bishop(5,7,false));
		setSquare(6,7, new Knight(6,7,false));
		setSquare(7,7,new Rook(7,7,false));
		for(int i=0;i<8;i++){
			setSquare(i,6, new Pawn(i,6,false));
		}
		
	}
	/**
	 * The second constructor public Board(Board b) constructs a new board object based on an existing board.
	 * 
	 * This is used to copy the board to be copied when the Minimax AI is computing future moves.  
	 * 
	 * @param b
	 */
	public Board(Board b){
		board = new Piece[8][8]; //initializes board
		//this for loop interates through board and fills it with null objects
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				board[i][j] = null;
			}
		}
		/*
		 * This loop iterates through the board passed as a parameter,
		 * and for each piece found in the board, a piece of the same color
		 * and type is added to the new board object.
		 */
		for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
				if(b.hasPiece(i,j)){
					if(b.getSquare(i, j).getColor() == false){ //case that piece is white
						if(b.getSquare(i, j).getType() == "King"){
							board[i][j] = new King(i,j,false);
						}else if(b.getSquare(i,j).getType() == "Queen"){
							board[i][j] = new Queen(i,j,false);
						}else if(b.getSquare(i,j).getType() == "Rook"){
							board[i][j] = new Rook(i,j,false);
						}else if(b.getSquare(i,j).getType() == "Bishop"){
							board[i][j] = new Bishop(i,j,false);
						}else if(b.getSquare(i,j).getType() == "Knight"){
							board[i][j] = new Knight(i,j,false);
						}else if(b.getSquare(i, j).getType() == "Pawn"){
							board[i][j] = new Pawn(i,j,false);
						}
					}else{ //case that piece is black
						if(b.getSquare(i, j).getType() == "King"){
							board[i][j] = new King(i,j,true);
						}else if(b.getSquare(i,j).getType() == "Queen"){
							board[i][j] = new Queen(i,j,true);
						}else if(b.getSquare(i,j).getType() == "Rook"){
							board[i][j] = new Rook(i,j,true);
						}else if(b.getSquare(i,j).getType() == "Bishop"){
							board[i][j] = new Bishop(i,j,true);
						}else if(b.getSquare(i,j).getType() == "Knight"){
							board[i][j] = new Knight(i,j,true);
						}else if(b.getSquare(i, j).getType() == "Pawn"){
							board[i][j] = new Pawn(i,j,true);
						}
					}
				}
			}
		}
	}

	/**
	 * the getSquare(int row, int col) returns whatever piece is on a given square given a row and column.
	 * 
	 * @param row
	 * @param col
	 * @return piece on the given square
	 */
	public Piece getSquare(int row, int col){
		return board[row][col];
	}
	
	/**
	 * the setSquare(int row, int col, Piece piece) method adds a given piece to the board, also given a row
	 * and column to put the piece.
	 * 
	 * @param row
	 * @param col
	 * @param piece
	 */
	public void setSquare(int row, int col, Piece piece){
		board[row][col] = piece;
	}
	/**
	 * The clearSquare(int row, int col) method sets a given square to null
	 * @param row
	 * @param col
	 */
	public void clearSquare(int row, int col){
		board[row][col] = null;
	}
	
	/**
	 * The hasPiece(int row, int col) method checks if a square has a piece given a row or column
	 * 
	 * @param row
	 * @param col
	 * @return boolean, true if a square has a piece, false if the square has no piece
	 */
	public boolean hasPiece(int row, int col){
		if(getSquare(row,col) != null){
			return true;
		}else{
			return false;
		}
	}
	
}
