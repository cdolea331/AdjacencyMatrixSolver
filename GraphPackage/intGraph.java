package GraphPackage;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Constructs a matrix of Vertex objects, as well as has methods to provide shortest path using Dijkstra's algorithm
 * as well as a method to provide shortest paths of a given matrix using Floyd's algorithm
 */
public class intGraph {
    private Vertex[] vertices;
    public intGraph(int[][] adjacencyMatrix){
        vertices = new Vertex[adjacencyMatrix.length];
        for (int i = 0; i < adjacencyMatrix.length; i++){
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < adjacencyMatrix.length; i++){
            for(int j = 0; j < adjacencyMatrix.length; j++){
                if(adjacencyMatrix[i][j] > 0)
                vertices[i].addNeighbor(vertices[j], adjacencyMatrix[i][j]);
            }
        }
    }

    /**
     * Returns shortest path using Dijkstra's algorithm
     * @param i Starting vertex
     * @param j Ending vertex
     * @return path length
     */
    public int getShortestPath(int i, int j){
        for(Vertex v : vertices){
            v.setVisited(false);
            v.setWeight(0);
        }
        Vertex current = vertices[i];
        Vertex end = vertices[j];
        Iterator<Vertex> neighbors;
        Iterator<Integer> weights;
        PriorityQueue<Vertex> vertexPriorityQueue = new PriorityQueue<>(new vertexComparator());
        vertexPriorityQueue.enqueue(current);
        if(i == j)
            return 0;


        while(current != end && !vertexPriorityQueue.isEmpty()){
            current = vertexPriorityQueue.dequeue();
            neighbors = current.getNeighborIterator();
            weights = current.getWeightIterator();
            while (neighbors.hasNext()){
                Vertex vertex = neighbors.next();
                int weight = weights.next();
                if((vertex.getWeight() > (current.getWeight() + weight) || vertex.getWeight() == 0
                        && !(vertex.isVisited())) && vertex != current) {
                    vertex.setWeight(current.getWeight() + weight);
                    vertexPriorityQueue.enqueue(vertex);
                }
            }
            current.setVisited(true);
        }
        if(current == end) {
            return current.getWeight();
        }else {
            return 0;
        }
    }

    /**
     * Returns shortest path using Floyd's algorithm
     * @param matrix Matrix to be evaluated
     * @return Path length
     */
    public int[][] floydShortestPath(int[][] matrix){
        int length = matrix.length;
        int[][][] matrices = new int[length][length][length];
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                for(int k = 0; k < length; k++){
                    if(i == 0){
                        int loopDistance = matrix[j][i] + matrix[i][k];
                        if(j == k){
                            matrices[i][j][k] = 0;
                        }else if(((matrix[j][k] > loopDistance)||(matrix[j][k] == 0)) &&
                                (( matrix[j][i] != 0) && (matrix[i][k] != 0))){
                            matrices[i][j][k] = loopDistance;
                        }else {
                            matrices[i][j][k] = matrix[j][k];
                        }
                    }else {
                        int loopDistance = matrices[i-1][j][i] + matrices[i-1][i][k];
                        int isZero = (matrices[i-1][j][i] * matrices[i-1][i][k]);
                        if(j == k){
                            matrices[i][j][k] = 0;
                        }else if(((matrices[i-1][j][k] > loopDistance) || (matrices[i-1][j][k] == 0)) &&
                                (isZero != 0)){
                            matrices[i][j][k] = loopDistance;
                        }
                        else {
                            matrices[i][j][k] = matrices[i-1][j][k];
                        }

                    }
                }
            }

        }
        return matrices[length-1];
    }

    /**
     * Inner vertex class, contains it's own list of neighbors and edges and provides iterators to iterate through them
     */
    private class Vertex{
        int id;
        LinkedList<Vertex> neighbors;
        LinkedList<Integer> edges;
        int weight;
        boolean visited;
        Vertex(int id){
            this.id = id;
            neighbors = new LinkedList<>();
            edges = new LinkedList<>();
            visited = false;
            weight = 0;

        }

        int getId() {
            return id;
        }

        /**
         * Adds a neighbor to the vertex
         * @param neighbor Vertex to be added as a neighbor
         * @param weight Weight of edge to neighboring vertex
         */
        void addNeighbor(Vertex neighbor, int weight){
            neighbors.add(neighbor);
            edges.add(weight);
        }

        Iterator<Vertex> getNeighborIterator(){
            return neighbors.iterator();
        }
        Iterator<Integer> getWeightIterator(){
            return edges.iterator();
        }

        int getWeight() {
            return weight;
        }

        void setWeight(int weight) {
            this.weight = weight;
        }

        boolean isVisited() {
            return visited;
        }

        void setVisited(boolean visited) {
            this.visited = visited;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Vertex)obj).getId() == this.id;
        }
    }

    private class vertexComparator implements Comparator<Vertex>{
        @Override
        public int compare(Vertex o1, Vertex o2) {
            return o1.weight - o2.weight;
        }
    }

}
