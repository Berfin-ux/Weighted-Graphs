public class Soru4 {
    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int[][] graph = {{0, 5, 3, INF, 2},
                {INF, 0, 2, 6, INF},
                {INF, 1, 0, 2, INF},
                {INF, INF, INF, INF, INF},
                {INF, 6, 10, 4, 0}};


        WeightedGraphs weightedGraph = new WeightedGraphs(graph);

        String alphabet = "ABCDEFGHIJKLMNOPRSTUVWXYZ";
        String subS = alphabet.substring(0, graph.length);
        for (int j = 0; j<subS.length(); j++){
            weightedGraph.addVertex(subS.charAt(j));
        }

        System.out.print("Breadth First Traverse: ");
        weightedGraph.breadthFirstTraverse();
        System.out.println("\n");

        int[] x = weightedGraph.dijkstrasShortestPath();
        System.out.println("Dijkstra's Shortest Path: ");
        for (int i=0; i<x.length; i++){
            System.out.println("0-" +i + ": "+ x[i]);
        }
        System.out.println(" ");

        System.out.print("Minimum Spanning Tree: ");
        weightedGraph.minimumSpanningTree();
        System.out.println();

    }
}
