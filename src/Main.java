import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int col, choose;
		int firstPlayer = Board.X;
		int scoreX = 0;
		int scoreO = 0;
		
		int difficulty;
		
		do{
			System.out.println();
			System.out.println("Select difficulty:");
			System.out.println("1. Easy");
			System.out.println("2. Normal");
			System.out.println("3. Hard");
			System.out.print("Please select: ");
			choose = s.nextInt();
		}while(choose < 1 && choose >3);
		
		difficulty = choose;
		
		GamePlayer aiGamePlayerO;
		
		switch (difficulty) {
		case 1:
			aiGamePlayerO = new GamePlayer(1, Board.O, difficulty);
			break;
		case 2:
			aiGamePlayerO = new GamePlayer(3, Board.O, difficulty);
			break;
		case 3:
			aiGamePlayerO = new GamePlayer(9, Board.O, difficulty);
			break;
		default:
			aiGamePlayerO = new GamePlayer();
			break;
		}
				
		Board board = new Board(difficulty);

		do{
			System.out.println();
			System.out.println("OPlayer: " + scoreO +  "   XPlayer: " + scoreX);
			System.out.println("1. New Game");
			System.out.println("2. Exit");
			System.out.print("Please select: ");
			choose = s.nextInt();
		}while(choose != 1 && choose!=2);
		
		while(choose != 2){
			System.out.println();
			board = new Board(difficulty);
			board.setLastLetterPlayed(-firstPlayer);
			board.print();
			
			while(board.isTerminal() == 2){
				System.out.println();
				
				switch (board.getLastLetterPlayed()){
					case Board.X:
						System.out.print("O moves: ");
						
						Move OMove = aiGamePlayerO.MiniMax(board);
						
						if(board.isValidMove(OMove.getCol())){
							board.makeMove(OMove.getCol(), Board.O);
						}
						
						break;
					case Board.O:
	                    System.out.print("X moves: ");
	                    col = s.nextInt() - 1;
						Move XMove = new Move(col);
	                    
						if(board.isValidMove(XMove.getCol())){
							board.makeMove(XMove.getCol(), Board.X);
						}
						
						break;
					default:
						break;
				}
				System.out.println();
				board.print();
			}
			
			firstPlayer = firstPlayer * (-1);
			
			if(board.isTerminal() == 1){
				System.out.println("X won!");
				scoreX++;
			}
			else if(board.isTerminal() == -1){
				System.out.println("O won!");
				scoreO++;
			}
			else{
				System.out.println("It's a draw!");
			}
			
			System.out.println();
			do{
				System.out.println("OPlayer: " + scoreO +  "   XPlayer: " + scoreX);
				System.out.println("1. New Game");
				System.out.println("2. Exit");
				System.out.print("Please select: ");
				choose = s.nextInt();
			}while(choose != 1 && choose!=2);
		}
	}
}
