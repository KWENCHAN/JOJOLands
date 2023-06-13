package ResidentsData;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Graph.Graph;
import java.util.*;

/**
 *
 * @author yaozh
 */
public class BurningDownTheHouse {

    public static class TreeNode {

        final String name;
        final List<TreeNode> children;

        public TreeNode(String name, List<TreeNode> children) {
            this.name = name;
            this.children = children;
        }

        public static TreeNode createTree(String root, Map<String, String> parent) {
            List<TreeNode> children = new ArrayList<>();

            for (Map.Entry<String, String> entry : parent.entrySet()) {
                String vertex = entry.getKey();
                String vertexParent = entry.getValue();
                if (vertexParent.equals(root)) {
                    TreeNode childNode = createTree(vertex, parent);
                    children.add(childNode);
                }
            }
            return new TreeNode(root, children);
        }

        public void printTree() {
            printTreeHelper(this, "", "");
        }

        private void printTreeHelper(TreeNode node, String prefix, String childrenPrefix) {
            System.out.print(prefix);
            System.out.print(node.name);
            System.out.println();

            for (int i = 0; i < node.children.size(); i++) {
                TreeNode child = node.children.get(i);
//                    child.printTreeHelper(child, childrenPrefix + "^-- ", childrenPrefix + "\t");
                if (i < node.children.size() - 1) {
                    child.printTreeHelper(child, childrenPrefix + "↖── ", childrenPrefix + "│   ");
                } else {
                    child.printTreeHelper(child, childrenPrefix + "↖── ", childrenPrefix + "    ");
                }
            }
        }
    }

    public void breadthFirstTraversal(Graph graph, String root) {
        ArrayList<String> visited = new ArrayList<>();
        Map<String, String> parent = new HashMap<>();

        Queue<String> queue = new LinkedList<>();

        visited.add(root);
        queue.offer(root);

        while (!queue.isEmpty()) {
            String currentVertex = queue.poll();

            // Get all adjacent vertices of the current vertex
            List<String> neighbors = graph.getNeighbors(graph.getVertex(currentVertex));

            // Enqueue unvisited neighbors and update parent-child relationship
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, currentVertex);
                    queue.offer(neighbor);
                }
            }
        }

        // Create the tree structure
        TreeNode treeRoot = TreeNode.createTree(root, parent);

        // Display the breadth-first traversal tree
        System.out.println("Breadth-First Traversal Tree:");
        treeRoot.printTree();
    }
}
