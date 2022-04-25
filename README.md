# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Name:** Ravitheja Dandi and Sai Sriker Reddy Vootukuri

**Email:**  dandi.r@northeastern.edu, vootukuri.s@northeastern.edu

**Preferred Name:** Sriker, Ravi

### About/Overview

This is a game about the world consisting of number of non-overlapping spaces that are laid out on a 2D grid. Items are present in the space and they can do a damage of n units. There is a target who will move in the world from one space to another space in a sequential way. Players of type human and computer are added to the world and they can attack target. At the end player wins the game if he kills target in maximum number of turns or else target wins the game. I have designed this project using java OOPS concept such as SOLID principles.

### List of Features

My project consits of following features:
1. Adjacent Spaces from a particular space can be found.
2. Displays information about particular space such as name of the space, neighbours of the space,items and playerspresent in the space.
3. Move the target around the world from one space to another space in sequential manner i.e.., from 1 to 2. Target starts at space 0.
4. Creates graphical representation of the world and saves it in .png format.
5. Adds a human player into the world in space of their choice.
6. Adds a computer player.
7. Moves player into adjacent spaces and this counts as a turn.
8. Allowing a player to pick a item from current space and this counts as a turn.
9. Allowing a player to look around by displaying information about where a specific player is in the world,other players present in that space, items present in that space including what spaces that can been seen from where they are and other players present in adjacent spaces and items present in adjacent spaces. This counts as a turn.
10. Display a description of a specific player including where they are in the world and what they are carrying.
11. Automatically move target around the world when players make a turn.
12. Provide clues to the player at the start of their turn.
13. Player can make an attempt to make an attack on target's life and this counts as a turn.
14. Move the pet around the world and this counts as a turn.
15. Hiding visibility of space if pet occupies that space.
16. When a turn is made, pet automatically moves according to the dfs[1] path traversal for that source vertex.

### How to Run

Command to run the jar file:
java -jar cs5010-final-project-ravisriker.jar argument1 argument2

argumnent1 is the fileName of the layout file.
argument2 is the total number of turns for a game.

### How to Use the Program

Following the execution of the driver class, the user is presented with a graphical user interface view that instructs them on how to play the game. The controller is controlled via a call to the driver class. Based on user input, the controller executes the necessary model commands. For example, if a user wishes to add a human player, he types the appropriate command and the appropriate inputs.


### Design/Model Changes

Milestone-4 We changed a few methods in the model class and significantly changed the controller that was previously in use.

### Assumptions

1. There should not be overlapping spaces in the input file.
2. Players can be added only at the beginning of the game.
3. Space names are unique.
4. Player names are unique.
5. Item names are unique.

### Limitations

 Currently there are no limitations in the project.

### Citations

[1] GeeksforGeeks. 2022. Depth First Search or DFS for a Graph - GeeksforGeeks. [online] Available at: <https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/> [Accessed 1 April 2022].



