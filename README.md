# CPSC 210 Project: Recreating the 2048 game

- **What will the application do?**

    This application is a *single-player sliding block game*. Users can slide and merge blocks that has the same value
    to create a new block with doubled value. A block with random small value appears every time the user slides a 
    block. Block values start from 2, and then 4, 8, 16, and so on.
 
    Users win the game once the value 2048 is reached, and lost the game once the whole game board is filled up with
    blocks and cannot merge anymore.

- **Who will use it?**

    People who are looking for a new game to play will use it, although anyone can use it since it is a simple
    entertaining game.

- **Why is this project of interest to you?**

    This game was very popular a few years ago, and I really loved playing it. Therefore I would love to recreate it.

## User Stories

- As a user, I want to be able to add a new game-record to my game record list

- As a user, I want to be able to view the game record list

- As a user, I want to be able to start a new game

- As a user, I want to be able to restart the game

- As a user, I want to be able to move the blocks

- As a user, I want to be able to end a game during the game

- As a user, when I select quit option from the application menu, I want the game record list to be saved to file

- As a user, I want to be able to optionally load the game record list from file when the program starts

##Instructions for Grader
- You can load and see the saved record list by clicking on "View Record List" button

- After a round of game is over, you can add a record by enter the month and day and then click "OK" button

- You can locate my visual component by clicking "OK" after entering the date

- You can save the state of my application by clicking "OK" button. Note that game itself does not get saved

- You can load the state of my application by clicking "View Record List" button.

##Phase 4: Task 2
I chose to test and design a class that is robust. The update method in GameRecord class throws the 
ImpossibleScoreException. In actionPerformed method in Game class, if a ImpossibleScoreException is caught, the console
out prints the stacktrace.

##Phase 4: Task 3
- The GameBoard class in model package had int[][] as a field to represent the board, which would not be a preferable
option since the cells on the board should be an object which I can later implement a GUI for.

To deal with this problem, I created a new Block class to represent a single cell/block on the game board. A block has
a coordinate on the board and it has a value. It also has a setValue method in case of the constant value changes in
this game. The GameBoard class now has Block[][] as a field instead of int[][].

- In the Game class in ui package, all the console related methods and GUI related methods were mixed together in one 
class.
I made a new BoardBuilder class in ui package, and moved methods related to GameBoard to the new class. The 
BoardBuilder class is responsible for creating the actual board and deal with updates of the board.