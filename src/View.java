/**
 * The View class is a JComponent that can be added to JFrames. It has a 
 * board object, whjich keeps track of what pieces are on which square.
 * It then uses this board object to draw pieces on the board on squares
 * that do have pieces.  The View class, in it's constructor, adds
 * ActionListeners to itself that help in moving pieces around the board.
 * 
 * The View class also has a JTextArea textBox which is constantly updated
 * when pieces are moved on the board.  While the JTextArea is not put on
 * the View JComponent, the ActionListeners inside this view class update teh 
 * text field.  
 * 
 * Note that false designates white color and true designates black color on the 
 * board.  The human player is always white and the AI is always black when the 
 * game is played in an AI mode.
 * 
 * The Point mousePoint is used to keep track of where the cursor is when
 * someone is dragging a piece on the board.  
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JComponent{
	
	private final String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H"}; //used to print the row as a letter instead of a number
	private final Board board; //instance of board class
	private Game game; //instance of Game class, keeps track of turns and victory
	private Point mousePoint; //keeps track of where the mouse is for the drag function
	private JTextArea textBox; //textBox, console that tracks changes to the board
	private String text; //String that keeps track of the words on the console
	
	/**
	 * The View(Board board, JTextArea textBox) constructor takes a board and textBox as parameters and sets
	 * these to the corresponding fields in the View object.  String text is initially set to "".  Then, 
	 * actionlisteners are added to the View.
	 * 
	 * @param board
	 * @param textBox
	 */
	public View(Board board, JTextArea textBox, Game game){
		this.board = board;
		this.game = game;
		this.textBox  = textBox;
		text = "";
		/*
		 * This MouseListener governs what happens in the event that a mouse is pressed.  
		 * The mouseListener cycles through all the squares on the board, and for all the 
		 * squares that have pieces, it checks if the place where the person clicked
		 * is within the piece.  If it is, then that piece is set to selected.  
		 */
		
		/*
		 * The mousepressed listener for Player vs. Player mode
		 */
		if(game.getMode() == 0){
			addMouseListener(new
					MouseAdapter(){
						public void mousePressed(MouseEvent event){
							mousePoint = event.getPoint(); //sets mousepoint to the current point for when the mouse is dragged
							//this for loop cycles through all squares on the board
							if(!View.this.game.getVictory()){
								for(int i = 0; i<8; i++){ 
									for(int j = 0; j<8; j++){
										if(View.this.board.hasPiece(i, j)){ //checks if board square has a piece
											if(View.this.board.getSquare(i, j).contains(event.getPoint())){ //checks if piece contains the place mouse was clicked
												if(View.this.board.getSquare(i,j).getColor() == View.this.game.getTurn()){ //check if it is the right turn
													View.this.board.getSquare(i,j).setSelected(true); //set selected to true
													View.this.text += View.this.board.getSquare(i,j).getType() + " selected! \n"; //add to console
													View.this.textBox.setText(View.this.text);
												}
											}
										}
									}
								}
							}
						}
			});
		}
		
		/*
		 * The mousepressed listener for modes Captain Random and Minimax
		 */
		if(game.getMode() != 0){
			addMouseListener(new
					MouseAdapter(){
						public void mousePressed(MouseEvent event){
							mousePoint = event.getPoint(); //sets mousepoint to the current point for when the mouse is dragged
							//this for loop cycles through all squares on the board
							if(!View.this.game.getVictory()){
								for(int i = 0; i<8; i++){ 
									for(int j = 0; j<8; j++){
										if(View.this.board.hasPiece(i, j)){ //checks if board square has a piece
											if(View.this.board.getSquare(i, j).contains(event.getPoint())){ //checks if piece contains the place mouse was clicked
												if(View.this.game.getTurn() == false && View.this.board.getSquare(i,j).getColor() == false){ //check if it is the right turn
													View.this.board.getSquare(i,j).setSelected(true); //set selected to true
													View.this.text += View.this.board.getSquare(i,j).getType() + " selected! \n"; //add to console
													View.this.textBox.setText(View.this.text);
												}
											}
										}
									}
								}
							}
						}
			});
		}
		/*
		 * this actionListener governs what happens when the mouse is dragged
		 */
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent event){
				Point lastMousePoint = mousePoint; //mousePoint used to keep track of where the piece should be drawn
				mousePoint = event.getPoint();
				for(int i = 0; i<8; i++){ //nested for loops iterate through the whole board
					for(int j=0; j<8; j++){
						if(View.this.board.hasPiece(i, j)){
							if(View.this.board.getSquare(i,j).isSelected()){
								double dx = mousePoint.getX() - lastMousePoint.getX(); //calculate how much to change piece position
								double dy = mousePoint.getY() - lastMousePoint.getY();
								View.this.board.getSquare(i, j).translate((int)dx,(int)dy); //translate piece
							}
						}
					}
				}
				repaint(); //repaints the View everytime mouse is dragged
			}
		});
		/*
		 * This actionListener governs what happens when the mouse is released
		 */
		addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				boolean pieceSelected = false; //keeps track of whether or not a piece was selected
				Piece selectedPiece = null; //keeps track of the selected piece
				Piece eatenPiece = null; //keeps track of piece on square another piece was moved to
				int oldX = 0; //keeps track of prior position if piece
				int oldY = 0;
				
				//for loop cyles through board
				for(int i=0; i<8; i++){
					for(int j=0; j<8; j++){
						if(View.this.board.hasPiece(i, j)){ //checks if board square has a piece
							if(View.this.board.getSquare(i, j).isSelected()){ //then checks if the piece was selected
								pieceSelected = true;
								selectedPiece = View.this.board.getSquare(i,j); //records data about piece
								oldX = i;
								oldY = j;
							}
						}
					}
				}
				if(pieceSelected){
					if(selectedPiece.checkLegalMove(e.getPoint(), View.this.board)){ //check legal moves
						int newX = ((int)e.getPoint().getX())/62; //record new point
						int newY = ((int)e.getPoint().getY())/62;
						/*
						 * Make the move
						 */
						//check for castling here
						if(selectedPiece.getType().equals("King") && newX-oldX == 2){
							if(selectedPiece.getColor() == false){ //case that piece is white
								View.this.board.clearSquare(oldX, oldY);
								View.this.board.setSquare(newX, newY, selectedPiece);
								selectedPiece.setLocation(newX, newY);
								Piece rook = View.this.board.getSquare(7, 7);
								View.this.board.setSquare(5,7,rook);
								rook.setLocation(5, 7);
								View.this.board.clearSquare(7, 7);
								View.this.text += "White castles. \n";
							}else{
								//case that piece is black
								View.this.board.clearSquare(oldX, oldY);
								View.this.board.setSquare(newX, newY, selectedPiece);
								selectedPiece.setLocation(newX, newY);
								Piece rook = View.this.board.getSquare(7, 0);
								View.this.board.setSquare(5,0,rook);
								rook.setLocation(5, 0);
								View.this.board.clearSquare(7, 0);
								View.this.text += "White castles. \n";
							}
						}else{
							if(View.this.board.hasPiece(newX, newY)){
								eatenPiece = View.this.board.getSquare(newX, newY);
							}
							View.this.board.clearSquare(oldX, oldY);
							View.this.board.setSquare(newX, newY, selectedPiece);
							//snap to square
							if(selectedPiece.setLocation(newX, newY)) System.out.println("location set"); //debug line
							if((eatenPiece != null)){
								View.this.text += selectedPiece.getType() + " eats " + eatenPiece.getType() + "\n";
								View.this.textBox.setText(View.this.text);
							}
							View.this.text += selectedPiece.getType() + " was moved to: " + View.this.columns[newX] +  (newY+1) + "\n"; 
							System.out.println("Snapped to square");
						}
						//repaint();
						/*Note: newY+1 is used to 
						 * read in the console as if rows and cols start
						*from 1 instead of 0, as they do in the program*
						*/
						View.this.textBox.setText(View.this.text); //record to console
						
						View.this.game.checkVictory(View.this.board);
						if(View.this.game.getVictory()){
							if(View.this.game.getTurn() == false){
								View.this.text += "White has taken black's king and won the game!";
							}else{
								View.this.text += "Black has taken white's king and won the game!";
							}
							View.this.textBox.setText(View.this.text);
						}else{
							View.this.game.changeTurn();
							if(View.this.game.getMode() != 0){ //case that the game is playing with AI
								
								/*
								 * This Thread is what runs in teh background and computes the AI's best
								 * move and makes that move.  It also updates the textArea with the appropriate text
								 */
								Thread t = new Thread(new Runnable(){
									public void run(){
										View.this.text += View.this.game.getAI().makeMove(View.this.board); 
										View.this.textBox.setText(View.this.text);
										View.this.game.checkVictory(View.this.board);
										if(View.this.game.getVictory()){
											if(View.this.game.getTurn() == false){
												View.this.text += "White has taken black's king and won the game!";
											}else{
												View.this.text += "Black has taken white's king and won the game!";
											}
											View.this.textBox.setText(View.this.text);
										}else{
										View.this.game.changeTurn();
										}
										repaint();  //repaints board after AI makes move
									}
								});
								//Have AI make move if game is in AI mode
								t.start(); //begins thread

							}
						}
					}else{ //case that move was illegal
						//snap back to original square
						selectedPiece.setLocation(oldX, oldY);
						View.this.text += "Illegal move! \n";
						View.this.textBox.setText(View.this.text);
					}
					selectedPiece.setSelected(false);
				}
				repaint();
			}
		});
	}
	
	/**
	 * the paintComponent(Graphics g) method paints the view component.  It begins by
	 * painting the black and white squares, and then iterates throught the board to paint
	 * all the pieces on the board.  
	 */
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		boolean isBlack = false; //keeps track of whether a square should be painted black or white
		/*
		 * This for loop cycles through an 8x8 grid, alternating black and white squares
		 */
		for(int i = 0; i<8; i++){
			isBlack = !(isBlack);
			for(int j = 0; j<8; j++){
				isBlack = !(isBlack);
				Rectangle rect = new Rectangle(i*62,j*62,62,62);
				if(isBlack){	
					g2.setColor(Color.darkGray);
				}else{
					g2.setColor(Color.white);
				}
				g2.fill(rect);
			}
		}
		/*
		 * This for loop cycles through the board and for any board square with a piece,
		 * it paints draws the piece.  
		 */
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				if(board.hasPiece(i, j)){ //perform draw action if piece exists on board
					board.getSquare(i, j).draw(g2);
				}
			}
		}
		//draws selected pieces on top to ensure they are on the top layer
		for(int i=0; i<8; i++){
			for(int j =0; j<8; j++){
				if(board.hasPiece(i, j)){
					if(board.getSquare(i, j).isSelected()){
						board.getSquare(i,j).draw(g2);
					}
				}
			}
		}
	}
}
