# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Name:** Ravitheja Dandi

**Email:** dandi.r@northeastern.edu

**Preferred Name:** Ravi

### About/Overview

Give a general overview of the problem and how your program solve the problem

This project is about a game inspired by killing Dr Lucky's game.Here we have a 2D grid which is called as World with
atleast 20 spaces/rooms in it and each space has items associated with it, which are used to kill dr. Lucky. We need to
kill the target when he steps into the space with the items provided, each item has damage value and this is the one
which determines the health decrease of that amount. When the health reaches 0, the game is completed and the Dr lucky
is killed.

### List of Features

List all features that are present in your program. We can find neighbours of the space given, space description, target
details, item list, world image/graph, moving the target.

1. Adding human player.
2. Adding computer player.
3. We can move the player to the neighbouring spaces.
4. We can pick the item from the space.
5. We can look around the player which describes the player's current room and its neighbours.
6. We can display player information.
7. We can display space information
8. We can download the world map
9. We can attack the target with or without item.
10. Player can move the pet.
11. There is a wandering pet which moves based on dfs algorithm.
12. Before each turn clues are displayed to the player about target and current player details.
13. Hiding the space from its neighbours if it contains a pet.

### How to Run

1. To run the jar file, first you need to navigate to res folder with cd res/ command.
2. Run java -jar <jar> mansion.txt 5 (num of turns).
3. It asks for input from the user to create human and computer players. From here you can proceed with the
   requirements.

### How to Use the Program

As you run the jar file a world is created for you.Enter number of players playing the game.If you want to create human
player select 1 and for computer player enter 2. When creating human player give name and location of player. For
example ravi Kitchen. Enter 1 to move the player 2 to pick item from the space 3 to look around the player 4 to display
player information 5 to space information 6 to get world map 7 to attack the target 8 to move pet q to exit the game

### Example Runs

I have created a world named Doctor Lucky's Mansion on 36*30 grid, here Dr Lucky is the target with health 50 and there
are 21 rooms and 20 items associated with it.I have created 2 players one is human and the other is computer
player.First human player has to play according to the order he/she was added. Human player picks an item from the space
he was added. Then its computer turn it performs random operations, in the example run it has picked the item from the
current space, and human player looks around and finds his/her neighbours then now its computer players turn, it is
moved to the space which is a neighbour. And human player looks around again, there are only 5 turns given in the
example run so game is over after human player had his/her turn.

1. res/petEffect.txt->the target character's pet effect on the visibility of a space from neighboring spaces Here, at
   the start of the game first player is added into Billiard room and second player is added into Armory where target
   and pet are present, and it is a neighbour of Billiard room. When a lookaround is called at first player choice
   Armory is not displayed under neighbours as pet is present in that room.
2. movePet.txt->the player moving the target character's pet Here, at the start of the game 2 players are added into
   different rooms and each player tries to move the pet into different rooms, when the particular room description is
   displayed pet details are also shown as part of that room.
3. attack -> a human-player making an attempt on the target character's life
4. computerAttack -> a computer-controlled player making an attempt on the target character's life
5. playerWin.txt -> a human-player winning the game by killing the target character
6. computerAttack.txt -> a computer-controlled player winning the game by killing the target character
7. targetWins.txt-> the target character escaping with his life and the game ending
8. movepetdfs.txt -> implementing dfs for the pet.

### Design/Model Changes

Document what changes you have made from earlier designs. Why did you make those changes? Keep an on-going list using
some form of versioning so it is clear when these changes occurred.

Previously, I have implemented moveTarget() in Target class, getIndexOfSpace() in space class, these are now moved to
the World interface and implemented in the ConcreteWorld class. getItemsInSpace() is implemented in space class. Few
methods like assignItems() has been removed as it is a redundant method for getItemsInSpace(). New variables such as
itemsList,spaceList are added and datatypes for variables has been changed for few variables.

I have used controller and model here. Controller is used for inputs and oupts. Model is the world. I have added 8
command classes each for each command. Added an item interface,target interface,player interface,player class to the
world. Implemented movePlayer,adding human player,adding computer player,lookaround,picking
item,displayPlayerInfo,downloading a worldmap and modifying displaySpaceinfo with displaying players present in the
space.

### Design/Model Changes for Milestone-3

I have created pet interface and petImpl class which represents target's pet. I have also created pet and attack command
classes. And also implemented attack target and move pet functionalities. And added mocks for these implementations.

### Assumptions

1. There should not be overlapping spaces in the input file.
2. Players can be added only at the beginning of the game.
3. Space names are unique.
4. Player names are unique.
5. Item names are unique.

### Limitations

This program has no limitations.

### Citations

[1] GeeksforGeeks. 2022. Depth First Search or DFS for a Graph - GeeksforGeeks. [online] Available
at: <https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/> [Accessed 1 April 2022].



