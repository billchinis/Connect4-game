# Connect4 board game

"Connect 4 is a two player game in which they take turns dropping discs into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the lowest available space within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs." 

In this implementation the player "O" is a MiniMax algorithm with alpha-beta pruning and player "X" is the user. The user can choose the difficulty of the game (1. Easy, 2. Normal, 3. Hard). There is no GUI, everything is on the cmd, including the board game and the menu which displays the score of the players.

## Classes
* Move: A class that includes the column on which there will be a move on and also which player will make that move.
* Board: This class is consisted of a two dimension array that contains firstly the moves that have already been played. Secondly which player made the last move. Finally there is the `evaluate()` method, that the AI uses in order to choose the best possible move to be played.
* GamePlayer: In this class there is the implementation of the MiniMax with a-b pruning algorithm.
* Main: In the main class, all the necessary messages are printed. There is the initialization of the board and also of the AI. After every move there is a check of the board if someone won or the game ended in a tie, then the score is updated.  

## HowTo
Open cmd on the same folder as the .java files, first run
`javac Main.java` and then `java Main`
