#!/bin/bash
echo "Welcome to the maze solving program"
echo "NOTE: Every maze generated will have 16 nodes and the goal is to find a way from node 0 to node 15"
echo "Enter the number of mazes that you want to create and solve"
read count
javac Lab5.java Graph.java
java Lab5 $count