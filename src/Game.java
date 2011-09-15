/**
 * The Game class keeps track of certain properties of the game, for example
 * the mode that the game is in (Player vs. Player, Captain Random, Minimax),
 * who's turn it is, and whether victory has been achieved.  Getter methods exist
 * to allow other classes to get access to these conditions.
 * @author sid123
 *
 */
import javax.swing.*;

public class Game {
	private boolean turn; //false means white turn, true means black turn
	private int mode; //0 => player vs. player, 1 => Captain Random, 2 => minimax
	private boolean victory; //false means game has not been won, true means game is over
	private AI player; //keeps track of the AI player
	private JLabel label; //label that keeps track of who's turn it is
	/*
	 * For mode 0
	 */
	private String playerOneName;
	private String playerTwoName;
	/**
	 * The Game(int mode) constructor takes the mode as a parameter and creates a new
	 * game object, instantiating the correct AI depending on the mode picked.  Mode 0 denotes
	 * Player vs. Player, mode 1 is Captain Random, and mode 2 is MinMax.
	 * @param mode
	 */
	public Game(int mode, JLabel label){
		turn = false;
		this.mode = mode;
		victory = false;
		this.label = label;
		if(mode == 1){
			player = new CaptainRandom();
		}
		if(mode == 2){
			player = new MinMax();
		}
	}
	public Game(int mode, String playerOneName, String playerTwoName, JLabel label){
		turn = false;
		this.mode = mode;
		victory = false;
		
		this.playerOneName = playerOneName;
		this.playerTwoName = playerTwoName;
		this.label = label;
	}
	
	/**
	 * The getAI() method returns the AI object playing in the current game.  This method
	 * should only be called for modes that involve an AI player
	 * @return player, the AI playing the game
	 */
	public AI getAI(){
		return player;
	}
	
	/**
	 * The getVictory() method checks the state of the victory variable and returns it.
	 * a value of true means that victory has been achived by a player and false means that
	 * no player has won the game
	 * @return true if game has been won, false if it has not
	 */
	public boolean getVictory(){
		return victory;
	}
	
	/**
	 * The getMode() method returns the mode that the game is being played in.
	 * @return the mode of the game, 0 if the game is Player vs. Player, 1 if the game is 
	 * Captain Random, and 2 if the game is MinMax
	 */
	public int getMode(){
		return mode;
	}
	
	
	/**
	 * The getTurn() method returns the player who's turn it is. 
	 * @return the color of the player who's turn it is, false if it is white, true if it is black
	 */
	public boolean getTurn(){
		return turn;
	}
	
	/**
	 * The changeTurn() method changes whoever's turn it is.  If it is black's turn, changeTurn() will change it to white's
	 * and vice versa.  False means white, true means black.  the changeTurn method
	 * also rewrites a JLabel that says who's turn it is.  
	 */
	public void changeTurn(){
		turn = !turn;
		if(mode == 0){
			if(!turn){
				label.setText(playerOneName + "'s Turn");
			}else{
				label.setText(playerTwoName + "'s Turn");
			}
		}else if(mode == 1){
			if(!turn){
				label.setText("Your turn!");
			}else{
				label.setText("The captain's turn!");
			}
		}else if(mode == 2){
			if(!turn){
				label.setText("Your turn!");
			}else{
				label.setText("Minimax's turn!");
			}
		}
	}
	
	
	/**
	 * The checkVictory(Board b) method takes a board as a parameter and checks if the game has been won.  
	 * The method iterates through the board it is passed, and if a king is found, victory is set to false, 
	 * otherwise, the victory variable defaults to true.
	 * @param b
	 */
	public void checkVictory(Board b){
		victory = true; //victory defaults to true if no king is found
		if(turn == true){ //case that it was black turn
			/*
			 * Iterate through the board
			 */
			for(int i = 0; i<8; i++){
				for(int j=0; j<8; j++){
					if(b.hasPiece(i, j)){
						if(b.getSquare(i,j).getType().equalsIgnoreCase("King")){ //checks if a king is on the square
							if(b.getSquare(i, j).getColor() == false){
								victory =  false;
							}
						}
					}
				}
			}
		}else{ //case that it was white turn
			for(int i =0; i<8; i++){
				for(int j=0; j<8; j++){
					if(b.hasPiece(i, j)){
						if(b.getSquare(i,j).getType().equalsIgnoreCase("King")){
							if(b.getSquare(i,j).getColor()==true){
								victory = false;
							}
						}
					}
				}
			}
		}
	}
}
