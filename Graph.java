import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.omg.DynamicAny.DynAnyPackage.TypeMismatch;


import java.util.Random;

public class Graph {

    private HashMap<Integer, Node> nodes = new HashMap<>();
    private int from, to;

    public Graph() {

    }

    // A randomize graph constructor
    public Graph(int from, int to) {
        this.from = from;
        this.to = to;
        Random r = new Random();
        for (int i = from; i < to + 1; i++) {
            nodes.put(i, null);
        }
        for (int i = from; i < to + 1; i++) {
            if (!r.nextBoolean()) {
                continue;
            }
            // Limit a node to only connect to 3-5 nodes initially
            int links = r.nextInt(3) + 3;
            for (int k = 0; k < links; k++) {
                int j = r.nextInt(to + 1);
                while (j == i) {
                    j = r.nextInt(to + 1);
                }
                if (nodes.get(i) == null) {
                    nodes.put(i, new Node(j));
                } else {
                    if (nodes.get(i).hasNode(j)) {
                        continue;
                    }
                    nodes.get(i).addNode(j);
                }
                if (nodes.get(j) == null) {
                    nodes.put(j, new Node(i));
                    continue;
                }
                if (nodes.get(j).hasNode(i)) {
                    continue;
                }
                nodes.get(j).addNode(i);
            }
        }
    }

    /* tell how many nodes a graph has */
    public int nodes() {
        return to + 1;
    }

    /* read a graph from the file */
    public boolean readGraph(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] nums = line.split(" ");
                Node prevNode = new Node(Integer.parseInt(nums[1]));
                nodes.put(Integer.parseInt(nums[0]), prevNode);
                for (int i = 2; i < nums.length; i++) {
                    Node newNode = new Node(Integer.parseInt(nums[i]));
                    prevNode.next = newNode;
                    prevNode = newNode;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /* print a graph */
    void printGraph() {
        System.out.println(this);
        // Iterator it = nodes.entrySet().iterator();
        // while (it.hasNext()) {
        //     Map.Entry pair = (Map.Entry) it.next();
        //     Node nextNode = (Node) pair.getValue();
        //     if (nextNode == null) {
        //         continue;
        //     }
        //     System.out.print(pair.getKey() + ": ");
        //     while (true) {
        //         System.out.print(nextNode.id + " ");
        //         if (nextNode.next == null) {
        //             break;
        //         }
        //         nextNode = nextNode.next;
        //     }
        //     System.out.println("");
        // }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = nodes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Node nextNode = (Node) pair.getValue();
            if (nextNode == null) {
                continue;
            }
            sb.append(pair.getKey() + ": ");
            while (true) {
                sb.append(nextNode.id + " ");
                if (nextNode.next == null) {
                    break;
                }
                nextNode = nextNode.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /* Depth First Search
      * start is the node from where the search begins
      * visited is an array of all the visited nodes
      * pred is an describing the search path
     */
    void dfs(int start, boolean[] visited, int[] pred) {
        Node node = nodes.get(start);
        visited[start] = true;
        while (node != null) {
            if (!visited[node.id]) {
                pred[node.id] = start;
                dfs(node.id, visited, pred);
            }
            node = node.next;
        }
    }

    private class Node {

        public int id;
        public Node next;

        public Node(int id) {
            this.id = id;
            this.next = null;
        }

        public boolean hasNode(int id) {
            Node nextNode = this;
            while (nextNode != null) {
                if (nextNode.id == id) {
                    return true;
                }
                nextNode = nextNode.next;
            }
            return false;
        }

        public void addNode(int id) {
            Node prevNode = this;
            Node nextNode = next;
            while (nextNode != null) {
                prevNode = nextNode;
                nextNode = nextNode.next;
            }
            prevNode.next = new Node(id);
        }
    }
}
