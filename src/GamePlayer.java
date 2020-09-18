import java.util.ArrayList;
import java.util.Random;

public class GamePlayer {
	private int maxDepth;
	
	private int playerLetter;
	
	private int difficulty;
	
	public GamePlayer(){
		maxDepth = 4;
		playerLetter = Board.X;
		difficulty = 2;
	}
	
	public GamePlayer(int maxDepth, int playerLetter, int difficulty){
		this.maxDepth = maxDepth;
		this.playerLetter = playerLetter;
		this.difficulty = difficulty;
	}
	
	public Move MiniMax(Board board){
		if(playerLetter == Board.X){
			return max(new Board(difficulty, board), 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
		else{
			return min(new Board(difficulty, board), 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
	}
	
	public Move max(Board board, int depth, int a, int b){
		Random r = new Random();
		
		if(board.isTerminal() != 2 || depth == maxDepth){
			Move lastMove = new Move(board.getLastMove().getCol(), board.evaluate());
			return lastMove;
		}
		
		ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.X));
		
		Move maxMove = new Move();
		maxMove.setValue(Integer.MIN_VALUE);
		
		for(Board child : children){
			Move move = min(child, depth + 1, a, b);
			
			if(maxMove.getValue() < move.getValue()){
				maxMove.setCol(child.getLastMove().getCol());
				maxMove.setValue(move.getValue());
			}
			
			if(a < maxMove.getValue()){
				a = maxMove.getValue();
			}
			
			if(b <= a){
				break;
			}
		}
		
		return maxMove;
	}
	
	public Move min(Board board, int depth, int a, int b){
		Random r = new Random();
		
		if(board.isTerminal() != 2 || depth == maxDepth){
			Move lastMove = new Move(board.getLastMove().getCol(), board.evaluate());
			return lastMove;
		}
		
		ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.O));
		
		Move minMove = new Move();
		minMove.setValue(Integer.MAX_VALUE);
		
		for(Board child : children){			
			Move move = max(child, depth + 1, a, b);
			
			if(minMove.getValue() > move.getValue()){
				minMove.setCol(child.getLastMove().getCol());
				minMove.setValue(move.getValue());
			}
			
			if(b > minMove.getValue()){
				b = minMove.getValue();
			}
			
			if(b <= a){
				break;
			}
		}
		
		return minMove;
	}
}
