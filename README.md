# Connect4 board game

"Connect4 is a two player game in which they take turns dropping discs into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the lowest available space within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs." -Wikipedia

In this implementation the player "O" is a MiniMax algorithm with a-b pruning and player "X" is the user. The user can choose the difficulty of the game between(1. Easy, 2.Normal, 3. Hard). There is no GUI, everything is on the cmd, including the board game and a menu which shows the wins of the players.

## Classes
* Move: A class that includes the column on which there will be a move on and also which player will make that move.
* Board: This class contains a two dimension array that has all the moves that have been played and also which player was the last to make a move. Last there is the evaluate() method that is responsible for the next move of the AI player.
* GamePlayer: In this class there is the implementation of the MiniMax with a-b pruning algorithm.
* Main: In the main class, all the necessary messages are printed. There is the initialization of the board and also of the AI (the maxDepth that the algorithm uses, the higher the maxDepth, the more difficult the AI). After every move there is a check if someone won or the game is finished tie, then the score is updated.  
