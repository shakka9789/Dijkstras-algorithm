import java.io.*;

class DijktraSSS {
    int numNodes, sourceNode, minNode, currentNode, newCost;
    int costMatrix[][];
    int father[];
    int toDo[];
    int best[];

    DijktraSSS(int N) {
        this.numNodes = N;
        costMatrix = new int[numNodes + 1][numNodes + 1];
        for (int i = 0; i <= numNodes; i++) {
            for (int j = 0; j <= numNodes; j++) {
                costMatrix[i][j] = 9999;
                costMatrix[i][i] = 0;
            }
        }

        father = new int[numNodes + 1];
        for (int i = 0; i <= numNodes; i++) {
            father[i] = 0;
        }

        toDo = new int[numNodes + 1];
        for (int i = 0; i <= numNodes; i++) {
            toDo[i] = 1;
        }

        best = new int[numNodes + 1];
        for (int i = 0; i <= numNodes; i++) {
            best[i] = 0;
        }
    }

    void loadCostMatrix(BufferedReader File) throws IOException {
        String line;
        while ((line = File.readLine()) != null) {
            String words[] = line.split("\\s+");
            int i = Integer.parseInt(words[0]);
            int j = Integer.parseInt(words[1]);
            costMatrix[i][j] = Integer.parseInt(words[2]);
        }
    }

    void setBest(int sourceNode) {
        for (int i = 0; i <= numNodes; i++) {
            best[i] = costMatrix[sourceNode][i];
        }
    }

    void setFather(int sourceNode) {
        for (int i = 0; i <= numNodes; i++) {
            father[i] = sourceNode;
        }
    }

    void setToDo(int sourceNode) {
        for (int i = 0; i <= numNodes; i++) {
            if (i == sourceNode) {
                toDo[i] = 0;
            }
            toDo[i] = 1;
        }
    }

    int findMinNode() {
        int minCost = 99999;
        int minNode = 0;
        int index = 1;

        while (index <= numNodes) {
            if (toDo[index] > 0) {
                if (best[index] < minCost) {
                    minCost = best[index];
                    minNode = index;
                }
            }
            index++;
        }
        return minNode;
    }

    int computeCost(int minNode, int currentNode) {
        return (best[minNode] + costMatrix[minNode][currentNode]);
    }

    void debugPrint(FileWriter File) throws IOException {
        File.write("Source Node = " + sourceNode + "\n");
        File.write("Father Array:\n");
        for (int i = 1; i <= numNodes; i++) {
            File.write(father[i] + ", ");
        }
        File.write("\n");
        File.write("Best Array:\n");
        for (int i = 1; i <= numNodes; i++) {
            File.write(best[i] + ", ");
        }
        File.write("\n");

        File.write("ToDo Array:\n");
        for (int i = 1; i <= numNodes; i++) {
            File.write(toDo[i] + ", ");
        }
        File.write("\n==========================\n");

    }

    boolean doneToDo() {
        for (int i = 0; i <= numNodes; i++) {
            if (toDo[i] != 0) {
                return true;
            }
        }
        return false;
    }

    void printShortestPath(int currentNode, int sourceNode, FileWriter sourceFile) throws IOException {
        int trail = father[currentNode];

        sourceFile.write("The path from " + sourceNode + " to " + currentNode + ": " + currentNode);

        while (trail != sourceNode) {
            sourceFile.write(" <- " + trail);
            trail = father[trail];
        }
        sourceFile.write(" <- " + sourceNode + " cost:" + best[currentNode] + "\n");

    }
};

public class Shaxzod_Karimov_Main {

    public static void main(String[] args) throws IOException {
        FileReader input = new FileReader(args[0]);
        FileWriter SSSFile = new FileWriter(args[1]);
        FileWriter deBugFile = new FileWriter(args[2]);

        BufferedReader inFile = new BufferedReader(input);
        int numNodes = Integer.parseInt(inFile.readLine());
        DijktraSSS DS = new DijktraSSS(numNodes);

        DS.loadCostMatrix(inFile);
        DS.sourceNode = 1;

        while (DS.sourceNode <= DS.numNodes) {

            DS.setBest(DS.sourceNode);
            DS.setFather(DS.sourceNode);
            DS.setToDo(DS.sourceNode);

            while (DS.doneToDo() == true) {
                DS.minNode = DS.findMinNode();
                DS.toDo[DS.minNode] = 0;
                DS.debugPrint(deBugFile);
                DS.currentNode = 1;
                while (DS.currentNode <= DS.numNodes) {
                    if (DS.toDo[DS.currentNode] > 0) {
                        DS.newCost = DS.computeCost(DS.minNode, DS.currentNode);

                        if (DS.newCost < DS.best[DS.currentNode]) {
                            DS.best[DS.currentNode] = DS.newCost;
                            DS.father[DS.currentNode] = DS.minNode;
                            DS.debugPrint(deBugFile);
                        }
                    }
                    DS.currentNode++;
                }
            }
            DS.currentNode = 1;

            SSSFile.write("Source Node = " + DS.sourceNode + "\n");
            while (DS.currentNode <= DS.numNodes) {
                DS.printShortestPath(DS.currentNode, DS.sourceNode, SSSFile);
                DS.currentNode++;
            }
            DS.sourceNode++;
        }
        inFile.close();
        inFile.close();
        SSSFile.close();
        deBugFile.close();
    }
}