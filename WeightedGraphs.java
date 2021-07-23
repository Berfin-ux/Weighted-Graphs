public class WeightedGraphs {
    private int INF = Integer.MAX_VALUE;
    private int numberVertices;
    private Vertex vertexList[];
    private int[][] weightedGraph;
    private int nVerts;
    private TheQueue theQueue;
    private int currentVert;
    private ThePriorityQueue thePQ;
    private int nTree;

    public WeightedGraphs(int[][] weightedGraph) {
        this.weightedGraph = weightedGraph;
        numberVertices = weightedGraph.length;
        vertexList = new Vertex[numberVertices];
        nVerts = 0;
        theQueue = new TheQueue();
        thePQ = new ThePriorityQueue();
    }

    public void addVertex(char name){
        vertexList[nVerts++] = new Vertex(name);
    }

    public void displayVertex(int index) {
        System.out.print(vertexList[index].name);
    }

    public void breadthFirstTraverse() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        theQueue.insert(0);
        int secondV;
        while (!theQueue.isEmpty()) {
            int firstV = theQueue.remove();
            secondV = getAdjUnvisitedVertex(firstV);
            while (secondV != -1) {
                vertexList[secondV].wasVisited = true;
                displayVertex(secondV);
                theQueue.insert(secondV);
                secondV = getAdjUnvisitedVertex(firstV);
            }
        }
        for (int j = 0; j < nVerts; j++)
            vertexList[j].wasVisited = false;
    }

    public int getAdjUnvisitedVertex(int vertex) {
        for (int j = 0; j < nVerts; j++)
            if (weightedGraph[vertex][j] != INF && !vertexList[j].wasVisited)
                return j;
        return -1;
    }

    public void minimumSpanningTree() {
        currentVert = 0;
        while (nTree < nVerts - 1) {
            vertexList[currentVert].isINTree = true;
            nTree++;
            for (int j = 0; j < nVerts; j++) {
                if (j == currentVert)
                    continue;
                if (vertexList[j].isINTree)
                    continue;
                int distance = weightedGraph[currentVert][j];
                if (distance == INF)
                    continue;
                putInPQ(j, distance);
            }
            if (thePQ.size() == 0) {
                System.out.println("GRAPH IS NOT CONNECTED");
                return;
            }
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.sourceV;
            currentVert = theEdge.endV;

            System.out.print(vertexList[sourceVert].name);
            System.out.print(vertexList[currentVert].name);
            System.out.print(" ");
        }

        for (int j = 0; j < nVerts; j++)
            vertexList[j].isINTree = false;
    }

    public void putInPQ(int newVertex, int newDist) {
        int queueIndex = thePQ.find(newVertex);
        if(queueIndex != -1)
        {
            Edge tempEdge = thePQ.peekN(queueIndex);
            int oldDist = tempEdge.distance;
            if(oldDist > newDist) {
                thePQ.removeN(queueIndex);
                Edge theEdge =
                        new Edge(currentVert, newVertex, newDist);
                thePQ.insert(theEdge);
            }
        }
        else {
            Edge theEdge = new Edge(currentVert, newVertex, newDist);
            thePQ.insert(theEdge);
        }
    }


    public int[] dijkstrasShortestPath() {
        int[] distanceTable = new int[weightedGraph[0].length];
        boolean[] visited = new boolean[weightedGraph[0].length];

        int min;
        int v;
        int w;
        int i;
        visited[0] = true;
        distanceTable[0] = 0;
        for (v = 1; v < weightedGraph.length; v++) {
            visited[v] = false;
            distanceTable[v] = weightedGraph[0][v];
        }

        for (i = 1; i < weightedGraph.length; i++) {
            min = Integer.MAX_VALUE;
            for (w = 1; w < weightedGraph.length; w++) {
                if (!visited[w]) {
                    if (distanceTable[w] < min) {
                        v = w;
                        min = distanceTable[w];
                    }
                }
            }

            visited[v] = true;
            for (w = 1; w < weightedGraph.length; w++) {
                if (!visited[w]) {
                    if ((min + weightedGraph[v][w]) < distanceTable[w]) {
                        distanceTable[w] = min + weightedGraph[v][w];
                    }
                }
            }
        }
        return distanceTable;
    }

}
