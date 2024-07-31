# Dijkstra's Shortest Path Algorithm in Java

## Overview

This Java program implements Dijkstra's Shortest Path Algorithm to find the shortest paths from a source node to all other nodes in a weighted graph. It reads graph data from an input file, processes it using Dijkstra's algorithm, and writes the results to output files. Additionally, it provides debugging information during computation.

## Classes and Methods

### `DijktraSSS` Class

The `DijktraSSS` class encapsulates the core functionality of Dijkstra's algorithm. 

**Attributes:**

- `int numNodes`: Number of nodes in the graph.
- `int sourceNode`: The starting node for the shortest path computation.
- `int minNode`, `currentNode`, `newCost`: Variables used during the computation.
- `int[][] costMatrix`: Matrix representing the costs between nodes.
- `int[] father`: Array to store the parent of each node in the shortest path.
- `int[] toDo`: Array to keep track of nodes to be processed.
- `int[] best`: Array to store the best known distances from the source node.

**Constructor:**

- `DijktraSSS(int N)`: Initializes the class with `N` nodes, setting up matrices and arrays. Initially, all costs are set to a high value (9999), and all nodes are marked for processing.

**Methods:**

- `void loadCostMatrix(BufferedReader File) throws IOException`: Loads the graph's cost matrix from the provided file.
- `void setBest(int sourceNode)`: Initializes the `best` array with distances from the source node.
- `void setFather(int sourceNode)`: Initializes the `father` array to indicate that the source node is the predecessor for all other nodes.
- `void setToDo(int sourceNode)`: Marks all nodes as needing processing except the source node.
- `int findMinNode()`: Finds the node with the minimum cost that has not been processed yet.
- `int computeCost(int minNode, int currentNode)`: Computes the cost from the `minNode` to `currentNode`.
- `void debugPrint(FileWriter File) throws IOException`: Prints debugging information to the provided file.
- `boolean doneToDo()`: Checks if there are any nodes left to process.
- `void printShortestPath(int currentNode, int sourceNode, FileWriter sourceFile) throws IOException`: Prints the shortest path from the source node to the specified `currentNode`.

### `Shaxzod_Karimov_Main` Class

The `Shaxzod_Karimov_Main` class contains the `main` method, which drives the execution of the algorithm.

**Main Method:**

1. **File Operations:**
   - Opens input and output files.
   - Reads the number of nodes from the input file.

2. **Algorithm Execution:**
   - Initializes an instance of `DijktraSSS`.
   - Loads the cost matrix from the input file.
   - Iteratively computes shortest paths for each source node.

3. **Processing:**
   - For each source node, the algorithm sets up the `best`, `father`, and `toDo` arrays.
   - Finds the minimum cost node, updates distances, and records paths.
   - Writes the shortest path from each source node to the output file.

4. **File Closing:**
   - Closes all opened files.
