# maze-solver
Description: Just a small project in school about find a path in a graph using depth first seach. 

The project contains only 3 files and in order for the program to execute, 3 files must be put in the same directory.
The manual for the program is:

1. Go into the directory you downloaded the 3 files

2. If the file `main.sh` is not executable, make it executable by the command: `chmod 744 main.sh`

3. Run the program by the command: `./main.sh`

4. The program will ask you to enter how many mazes (n) you want to generate: Enter an integer (There is no limit to how much mazes you want)

5. The program will output to the console the simplified result containing the solution for each maze.

6. The result is saved in `result.txt`

7. Mazes is saved in `mazeX.grh` files with X is a number from 0 -> n-1

8. Use `cat mazeX.grh` to view the content of mazeX

NOTE: Because there is no specific specification about some aspects of the project,

1. Every maze is created randomly

2. For simplicity, every maze only has 16 nodes and the goal is to go from 0 to 15, if you want to customize it, you need to change the variable `TO` in the file Lab5.java
