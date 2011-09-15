/**
 * CaptainRandom is an AI player that checks all moves available to him and picks a random move to go through with.  
 * 
 * Note that AI players are always black.  
 */

import java.util.*;
import java.awt.Point;

public class CaptainRandom implements AI {
	private final String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H"}; //used to print the row as a letter instead of a number
	
	/**
	 * The makeMove(Board b) iterates through the board, generates all the AI's possible moves and picks a random move.
	 * After picking the move, the AI performs the move on the board, returning a String that records what kind of
	 * move was made.  
	 */
	@Override
	public String makeMove(Board b) {
		ArrayList<Move> moves= new ArrayList<Move>();
		Random generator = new Random();
		/*
		 * For loops iterate through board and finds all possible legal moves for the AI player
		 */
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				if(b.hasPiece(i, j)){
					if(b.getSquare(i, j).getColor()){
						Piece piece = b.getSquare(i,j);
						for(int k = 0; k<8; k++){
							for(int l = 0; l<8; l++){
								Point p = new Point(k*62, l*62); //multiplied by 62 to match pixel position as required by checkLegalMove method
								if(piece.checkLegalMove(p, b)){ //check if move is legal
									moves.add(new Move(i,j,k,l,piece)); //Point methods casted to int
								}
							}
						}
					}
				}
			}
		}
		//get random index
		int index = generator.nextInt(moves.size());
		Move moveToMake = moves.get(index);
		Piece pieceToMove = moveToMake.getPiece();
		
		/*
		 * Check if castling took place, first check white castle and then
		 * check black castle
		 */
		if(pieceToMove.getType().equals("King")){
			if(pieceToMove.getColor() == false){//case that it is white king
				if(b.hasPiece(7, 7)){
					if(b.getSquare(7,7).getType().equals("Rook")){
						if(moveToMake.getOldX() == 4 && moveToMake.getOldY() == 7 && moveToMake.getNewX() == 6 && moveToMake.getNewY() ==7){
							//make necessary changes when castling takes place and return the correct text
							b.clearSquare(moveToMake.getOldX(), moveToMake.getOldY());
							b.setSquare(moveToMake.getNewX(), moveToMake.getNewY(), pieceToMove);
							pieceToMove.setLocation(moveToMake.getNewX(), moveToMake.getNewY());
							Piece rook = b.getSquare(7, 7);
							b.setSquare(5,7,rook);
							rook.setLocation(5, 7);
							b.clearSquare(7, 7);
							String text = "White castles. \n";
							return text;
						}
					}
				}
			}else if(pieceToMove.getColor() == true){ //case that it is black king
				if(b.hasPiece(7, 0)){
					if(b.getSquare(7, 0).getType().equals("Rook")){
						if(moveToMake.getOldX() == 4 && moveToMake.getOldY() == 0 && moveToMake.getNewX() == 6 && moveToMake.getNewY() ==0){
							//perform the castling
							b.clearSquare(moveToMake.getOldX(), moveToMake.getOldY());
							b.setSquare(moveToMake.getNewX(), moveToMake.getNewY(), pieceToMove);
							pieceToMove.setLocation(moveToMake.getNewX(), moveToMake.getNewY());
							Piece rook = b.getSquare(7, 0);
							b.setSquare(5, 0, rook);
							rook.setLocation(5, 0);
							b.clearSquare(7, 0);
							String text = "Black castles. \n";
							return text;
						}
					}
				}
			}
		}else{ //case that castling did not take place, change pieces and change location of the piece
			b.clearSquare(moveToMake.getOldX(), moveToMake.getOldY());
			b.setSquare(moveToMake.getNewX(), moveToMake.getNewY(), pieceToMove);
			pieceToMove.setLocation(moveToMake.getNewX(), moveToMake.getNewY());
			
			/*
			 * Figures out what text to send back
			 */
			String text = pieceToMove.getType() + " was moved to: " + columns[moveToMake.getNewX()] +  (moveToMake.getNewY()+1) + "\n";
			return text;
		}
		return ""; //default return
	}

}
