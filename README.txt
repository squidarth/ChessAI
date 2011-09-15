ChessAI

by Sidharth Shanker
----------------------------------------------------------------------------------
FILES CONTAINED:

/src/AI.java
/src/Bishop.java
/src/Board.java
/src/CaptainRandom.java
/src/Game.java
/src/King.java
/src/Knight.java
/src/MainClass.java
/src/MinMax.java
/src/Move.java
/src/Pawn.java
/src/Piece.java
/src/Queen.java
/src/Rook.java
/src/View.java

/doc/ -- Javadoc is stored here

Note that all images came from Wikimedia and are free to be distributed
See JavaDoc and code documentation for descriptions of these different classes.

------------------------------------------------------------------------------------
PROGRAM DESCRIPTION:

This is a full chess game with three modes. The first is a player vs. player mode in which
one player is black and the other is white and the players can alternate turns as they play against each other in chess.  When users choose to play this mode, they are prompted to enter the white player's name, and the black player's name. A JTextArea at the bottom of the game board logs the moves that players make.  A JLabel above this JTextArea lets the players know whose turn it is.  The players must follow the rules of chess nad cannot make illegal moves.  There is no concept of check or checkmate in this version of chess, a player must capture the other player's king in order to win the game.  

Another mode players can choose to play is Captain Random, in which they play against a computer AI that makes completely random moves.  All the moves that Captain Random makes are legal, but the AI chooses them at random, using a random number generator to pick out a random index in an arraylist of moves. 

The final mode players can choose to play is Minimax.  Minimax is an AI that uses a relatively sophisticated algorithm to play chess.  Minimax, similarly to Captain Random, first generates all possible moves.  However, Minimax ranks these moves according to how well they score when boards generated that have each move played out are evaluated by an evaluatePosition function.  The evaluatePosition function is called recursively, and when the function's depth reaches 0, the function returns an integer that is computed using the evaluate function.  The evaluate function is an evaluation function that simply takes a board as a parameter and computes how advantageous that board is for the AI by counting up the number of pieces that it is, the number of pieces that the human opponent has, weighting them by the value of the piece, and then subtracting the two numbers to find the difference between the two players.  

If the depth is not 0, then evaluatePosition computes all possible moves from the board position given, and then calls evaluatePosition again on each of those, with depth decremented by 1.  evaluatePosition keeps track of whether it is white's turn or black's turn that it is evaluating, and if it is white's turn, it will try to minimize the evaluation of the board, whereas if it is black's turn it will try to maximize it.  Alpha-beta pruning is also used to short-circuit the evaluation of moves that are worse than moves already evaluated.  

My minimax class has a constant DEPTH that governs how far down the game tree minimax evaluates moves.  I have it set to 1, as going to 2 takes the AI 2-5 minutes to compute, although this constant can be set to 2.  Even looking one move ahead, Minimax performs well.  

In terms of overall class design, I have a Game class that keeps track of the state of each game and that checks victory.  This is passed as a parameter to my View class, which extends JComponent and is added to a JFrame.  All the chess pieces and the board itself are painted on the View, and all the ActionListeners for the board are added in the View class as well.  The Board is a seperate class that maintains a two-dimensional array of Piece objects and keeps track of which pieces are where.  For the sake of the minimax algorithm, I have defined a seperate Board constructor that copies the board.  My Piece class is extended by the various pieces on the board.  Each piece has its own implementation of draw and checkLegalMove. 

Finally, I have defined an AI interface that allows a user to define an AI generically, without having to declare it as a specific type of AI.  This is very useful for if I were to implement another AI class, I could simply implement the AI interface and then in my Game class, in which instances of the AI are stored, I can pass the new AI, which is referred to in the View class generically as being of type AI.  Also for the sake of my AI, I have defined a Move class that keeps track of different moves a player could make.  

Enjoy playing my chess game!

Note: For the sake of color of pieces in this game, I have chosen to use boolean values where false is white and true is black.  


