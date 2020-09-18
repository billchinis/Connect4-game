import java.util.ArrayList;

public class Board {
	public static final int X = 1;
	public static final int O = -1;
	public static final int EMPTY = 0;
	
	public int fourInLine;
	public int threeInLine;
	public int twoInLine;
	
	private int difficulty;
	
	private Move lastMove;
	
	private int lastLetterPlayed;
	
	private int [][] gameBoard;
	
	public Board(){
		lastMove = new Move();
		lastLetterPlayed = O;
		
		fourInLine = 1000;
		threeInLine = 10;
		twoInLine = 6;
		
		difficulty = 2;
		
		gameBoard = new int[6][7];
		
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				gameBoard[i][j] = EMPTY;
			}
		}
	}
	
	public Board(int difficulty){
		lastMove = new Move();
		lastLetterPlayed = O;
		
		this.difficulty = difficulty;
		
		switch (difficulty) {
		case 1:
			fourInLine = 8;
			threeInLine = 6;
			twoInLine = 4;
			break;
		case 2:
			fourInLine = 1000;
			threeInLine = 10;
			twoInLine = 6;
			break;
		case 3:
			fourInLine = Integer.MAX_VALUE / 2;
			threeInLine = 1000;
			twoInLine = 1;
			break;
		}
		
		gameBoard = new int[6][7];
		
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				gameBoard[i][j] = EMPTY;
			}
		}
	}
	
	public Board(Board board){
		lastMove = board.lastMove;
		lastLetterPlayed = board.lastLetterPlayed;
		gameBoard = new int[6][7];
		
		fourInLine = 1000;
		threeInLine = 10;
		twoInLine = 6;
		
		difficulty = 2;
		
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				gameBoard[i][j] = board.gameBoard[i][j];
			}
		}
	}
	
	public Board(int difficulty, Board board){
		lastMove = board.lastMove;
		lastLetterPlayed = board.lastLetterPlayed;
		gameBoard = new int[6][7];
		
		this.difficulty = difficulty;
		
		switch (difficulty) {
		case 1:
			fourInLine = 8;
			threeInLine = 6;
			twoInLine = 4;
			break;
		case 2:
			fourInLine = 1000;
			threeInLine = 10;
			twoInLine = 6;
			break;
		case 3:
			fourInLine = Integer.MAX_VALUE / 2;
			threeInLine = 1000;
			twoInLine = 1;
			break;
		}
		
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				gameBoard[i][j] = board.gameBoard[i][j];
			}
		}
	}
	
	public Move getLastMove(){
		return lastMove;
	}
	
	public int getLastLetterPlayed(){
		return lastLetterPlayed;
	}
	
	public int[][] getGameBoard(){
		return gameBoard;
	}
	
	public void setLastMove(Move lastMove){
		this.lastMove = lastMove;
	}
	
	public void setLastLetterPlayed(int lastLetterPlayed){
		this.lastLetterPlayed = lastLetterPlayed;
	}
	
	public void setGameBoard(int[][] gameBoard){
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				gameBoard[i][j] = gameBoard[i][j];
			}
		}
	}
	
	public void makeMove(int col, int letter){
		int counter = 0;
		for(int i = 0; i < 6; i++){
			if(gameBoard[i][col] != EMPTY){
				gameBoard[i-1][col] = letter;
				
				lastMove = new Move(col, letter);
				lastLetterPlayed = letter;
				break;
			}
			else{
				counter++;
			}
		}
		if(counter == 6){
			gameBoard[5][col] = letter;
			
			lastMove = new Move(col, letter);
			lastLetterPlayed = letter;
		}
	}
	
	public boolean isValidMove(int col){
		if(col > 6 || col < 0) return false;
		if(gameBoard[0][col] != EMPTY) return false;
		
		return true;
	}
	
	public ArrayList<Board> getChildren(int letter){
		ArrayList<Board> children = new ArrayList<Board>();
		for(int j = 0; j < 7; j++){
			if(isValidMove(j)){
				Board child = new Board(difficulty, this);
				child.makeMove(j, letter);
				children.add(child);
			}
		}
		
		return children;
	}
	
	public int evaluate(){
		int Xlines = 0;
		int Olines = 0;
		
		// Checking rows
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 4; j++){	
				int s = 0;
				
				for(int count = 0; count < 3; count++){
					if(gameBoard[i][j + count] == gameBoard[i][j + count + 1] && gameBoard[i][j + count] != 0){
						s++;
					}
					else if( (gameBoard[i][j + count] != gameBoard[i][j + count + 1] && gameBoard[i][j + count] != 0) || count == 2){
						if(gameBoard[i][j + count] == 1){
							if(s == 1){
								Xlines = Xlines + twoInLine;
							}
							else if(s == 2){
								Xlines = Xlines + threeInLine;
							}
							else if(s == 3){
								Xlines = Xlines + fourInLine;
							}
						}
						else if(gameBoard[i][j + count] == -1){
							if(s == 1){
								Olines = Olines + twoInLine;
							}
							else if(s == 2){
								Olines = Olines + threeInLine;
							}
							else if(s == 3){
								Olines = Olines + fourInLine;
							}
						}
						
						s = 0;
					}
					if(gameBoard[i][j + count] == 0){
						s = 0;
					}
				}
				
			}
		}
		
		// Checking columns
		for(int j = 0; j < 7; j++){
			for(int i = 0; i < 3; i++){
				int s = 0;
				
				for(int count = 0; count < 3; count++){
					
					if(gameBoard[i + count][j] == gameBoard[i + count + 1][j] && gameBoard[i + count][j] != 0){
						s++;
					}
					else if((gameBoard[i + count][j] != gameBoard[i + count + 1][j] && gameBoard[i + count][j] != 0) || count == 2){
						if(gameBoard[i + count][j] == 1){
							if(s == 1){
								Xlines = Xlines + twoInLine;
							}
							else if(s == 2){
								Xlines = Xlines + threeInLine;
							}
							else if(s == 3){
								Xlines = Xlines + fourInLine;
							}
						}
						else if(gameBoard[i + count][j] == -1){
							if(s == 1){
								Olines = Olines + twoInLine;
							}
							else if(s == 2){
								Olines = Olines + threeInLine;
							}
							else if(s == 3){
								Olines = Olines + fourInLine;
							}
						}
						
						s = 0;
					}
					if(gameBoard[i + count][j] == 0){
						s = 0;
					}
				}
			}
		}
		
		// Checking diagonals to right
		for(int i = 3; i < 6; i++){
			for(int j = 0; j < 4; j++){
				int s = 0;
				
				for(int count = 0; count < 3; count++){
					
					if(gameBoard[i - count][j + count] == gameBoard[i - count - 1][j + count + 1] && gameBoard[i - count][j + count] != 0){
						s++;
					}
					else if((gameBoard[i - count][j + count] != gameBoard[i - count - 1][j + count + 1] && gameBoard[i - count][j + count] != 0) || count == 2){
						if(gameBoard[i - count][j + count] == 1){
							if(s == 1){
								Xlines = Xlines + twoInLine;
							}
							else if(s == 2){
								Xlines = Xlines + threeInLine;
							}
							else if(s == 3){
								Xlines = Xlines + fourInLine;
							}
						}
						else if(gameBoard[i - count][j + count] == -1){
							if(s == 1){
								Olines = Olines + twoInLine;
							}
							else if(s == 2){
								Olines = Olines + threeInLine;
							}
							else if(s == 3){
								Olines = Olines + fourInLine;
							}
						}
						
						s = 0;
					}
					if(gameBoard[i - count][j + count] == 0){
						s = 0;
					}
				}
			}
		}
			
		// Checking diagonals to left
		for(int i = 3; i < 6; i++){
			for(int j = 3; j < 7; j++){
				int s = 0;
				
				for(int count = 0; count < 3; count++){
					
					if(gameBoard[i - count][j - count] == gameBoard[i - count - 1][j - count -1] && gameBoard[i - count][j - count] != 0){
						s++;
					}
					else if((gameBoard[i - count][j - count] != gameBoard[i - count - 1][j - count -1] && gameBoard[i - count][j - count] != 0)){
						if(gameBoard[i - count][j - count] == 1){
							if(s == 1){
								Xlines = Xlines + twoInLine;
							}
							else if(s == 2){
								Xlines = Xlines + threeInLine;
							}
							else{
								Xlines = Xlines + fourInLine;
							}
						}
						else if(gameBoard[i - count][j - count] == -1){
							if(s == 1){
								Olines = Olines + twoInLine;
							}
							else if(s == 2){
								Olines = Olines + threeInLine;
							}
							else{
								Olines = Olines + fourInLine;
							}
						}
						
						s = 0;
					}
					if(gameBoard[i - count][j - count] == 0){
						s = 0;
					}
				}
			}
		}
		
		return Xlines - Olines;
	}
	
	/* return -1 if O won
	 * return  0 if draw
	 * return  1 if X won
	 * return  2 if nothing happens
	 */
	public int isTerminal(){
		// horizontal
		for(int i = 0; i < 6; i++){
			int counter = 1;
			
			for(int j = 0; j < 6; j++){
				if(gameBoard[i][j] == gameBoard[i][j + 1] && gameBoard[i][j] != EMPTY){
					counter++;
				}
				else{
					counter = 1;
				}
				
				if(counter == 4) return gameBoard[i][j];
			}
		}
		
		// vertical
		for(int j = 0; j < 7; j++){
			int counter = 1;
			
			for(int i = 0; i < 5; i++){
				if(gameBoard[i][j] == gameBoard[i + 1][j] && gameBoard[i][j] != EMPTY){
					counter++;
				}
				else{
					counter = 1;
				}
				
				if(counter == 4) return gameBoard[i][j];
			}
		}
		
		// diagonal to right
		for(int i = 3; i < 6; i++){
			for(int j = 0; j < 4; j++){
				if(gameBoard[i][j] == gameBoard[i - 1][j + 1] && 
				   gameBoard[i][j] == gameBoard[i - 2][j + 2] &&
				   gameBoard[i][j] == gameBoard[i - 3][j + 3] &&
				   gameBoard[i][j] != EMPTY){
					return gameBoard[i][j];
				}
			}
		}
		
		// diagonal to left
		for(int i = 3; i < 6; i++){
			for(int j = 3; j < 7; j++){
				if(gameBoard[i][j] == gameBoard[i - 1][j - 1] && 
				   gameBoard[i][j] == gameBoard[i - 2][j - 2] &&
				   gameBoard[i][j] == gameBoard[i - 3][j - 3] &&
				   gameBoard[i][j] != EMPTY){
					return gameBoard[i][j];
				}
			}
		}
		
		// at least one empty tile
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				if(gameBoard[i][j] == EMPTY){
					return 2;
				}
			}
		}
		
		return 0;
	}
	
	public void print(){
		System.out.println("*****************");
		
		for(int i = 0; i < 6; i++){
			System.out.print("* ");
			
			for(int j = 0; j < 7; j++){
				switch(gameBoard[i][j]){
					case X:
						System.out.print("X ");
						break;
					case O:
						System.out.print("0 ");
						break;
					case EMPTY:
						System.out.print("- ");
						break;
					default:
						break;
				}
			}
			
			System.out.println("*");
		}
		
		System.out.println("*****************");
	}
}
