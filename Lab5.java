import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.SortedMap;

public class Lab5 {

    /* find a maze solution */
    private static int mazeSolution(int from, int to, int pred[], int steps[]) {
        int i, n, node;
        // first count how many edges between the end and the start 
        node = to;
        n = 1;
        while ((node = pred[node]) != from) {
            n++;
        }
        // then reverse pred[] array to steps[] array 
        node = to;
        i = n;
        while (i >= 0) {
            steps[i--] = node;
            node = pred[node];
        }
        // include also the end vertex
        return (n + 1);
    }

    private final static int FROM = 0;
    private final static int TO = 15;
    private static int COUNT;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a valid integer!");
            return;
        }
        try {
            COUNT = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Please enter a valid integer!");
            return;
        } 
        switch (COUNT) {
        case 1:
            System.out.println("The program will generate 1 maze randomly into file maze0.grh");
            break;
        case 2:
            System.out.println("The program will generate 2 maze randomly into file maze0.grh and maze1.grh");
            break;
        default:
            System.out.println("The program will generate " + COUNT + " maze randomly into files maze0.grh -> maze" + (COUNT - 1) + ".grh");
            break;
        }
        System.out.println();
        // Delete all maze files 
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            File f = new File("maze" + i + ".grh");
            if (!f.delete()) {
                break;
            }
        }
        File f = new File("result.txt");
        f.delete();
        PrintWriter resultOut;
        try {
            resultOut = new PrintWriter("result.txt");
            if (COUNT == 1) {
                resultOut.println("There is only 1 maze");
            } else {
                resultOut.println("There are " + COUNT + " mazes in total");
            }
            resultOut.println("The result is shown below!");
            resultOut.println();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        int noSolutionCount = 0;
        for (int k = 0; k < COUNT; k++) {
            Graph g = new Graph(FROM, TO);
            try {
                PrintWriter out = new PrintWriter("maze" + k + ".grh");
                out.println("Maze " + k + " adjacent list");
                out.println(g);
                resultOut.println("Maze " + k + ": ");
                System.out.print("Maze " + k + ": ");
                boolean visited[] = new boolean[g.nodes()];
                int pred[] = new int[g.nodes()];
                pred[TO] = -1;
                g.dfs(FROM, visited, pred);
                if (pred[TO] == -1) {
                    out.println("There is no solution for this maze");
                    resultOut.print("No solution");
                    System.out.print("No solution");
                    noSolutionCount++;
                } else {
                    // then check if there is a solution by looking from the backwards to the start 
                    int steps[] = new int[g.nodes()];
                    out.println("Maze solution from " + FROM + " to " + TO);
                    resultOut.print("Solution: ");
                    int n = mazeSolution(FROM, TO, pred, steps);
                    for (int i = 0; i < n; i++) {
                        resultOut.print(steps[i]);
                        out.print(steps[i]);
                        System.out.print(steps[i]);
                        if (i < n - 1) {
                            resultOut.print(" -> ");
                            out.print(" -> ");
                            System.out.print(" -> ");
                        }
                    }
                }
                resultOut.println();
                System.out.println();
               out.println();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }       
        }
        System.out.println();
        System.out.println("Every mazeX.grh file also contains the result of the finding");
        System.out.println("Use the command `cat mazeX.grh` to view the content of the maze along with the solution");
        System.out.println("The total result is saved in result.txt, displaying the number of mazes that don't have soltution'");
        resultOut.println();
        resultOut.println("The number of mazes that have solution is " + (COUNT - noSolutionCount));
        resultOut.println("The number of mazes that don't have solution is " + noSolutionCount);
        resultOut.close();
    }
}
