/**
 * The public interface AI provides a single method
 * makeMove.  Classes that implement AI must implement this method,
 * and this allows the View class to generically refer
 * to AI players as of type AI.  
 * @author sid123
 *
 */
public interface AI {
	/**
	 * The makeMove(Board b) method takes a board as a parameter
	 * and should make a move.  It should return as a String the move that
	 * was made so that the JTextArea can be updated with the move.
	 * @param b
	 * @return the move that was made as a String
	 */
	public String makeMove(Board b);
}
