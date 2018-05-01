import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import GraphPackage.*;
/**
 * Performs Dijkstra's algorithm and Floyd's algorithm on a set amount of matrices stored in .csv format. Then allows
 * user to verify correctness by asking for shortest paths between specific vertices.
 * Program could be greatly sped up by using an implementation of Dijkstra's that uses only an adjacency matrix rather
 * than a linked data structure. This will be noted and used whenever I have use for it in the future.
 */
public class AdjacencyMatrixSolver {

    public static void main(String[] args) throws IOException  {//I know this is bad practice but I really don't want
        Scanner kb = new Scanner(System.in);        //to have to do more user input checking for something like this
        BufferedWriter results = new BufferedWriter(new FileWriter("Results.txt"));
        System.out.println("Enter # of matrices to be evaluated");
        int count = Integer.parseInt(kb.nextLine());

        for(int p = 0; p < count; p++){
            System.out.println("Enter the filepath of an adjacency matrix separated by commas(.csv)");
            System.out.println("Example:\n1,2,3,4\n5,6,7,8");
            String filepath = kb.nextLine(); //Taking in user defined matrix
            int[][] matrix = new int[1][1]; //In case user does not input anything(to avoid having to input check)
            try{
                Scanner input = new Scanner(new File(filepath));
                int vertices = 0;
                while (input.hasNextLine()){ //Populate the list of vertices, also obtains the Y dimension of the matrix
                    input.nextLine();
                    vertices++;
                }
                input = new Scanner(new File(filepath));
                matrix = new int[vertices][vertices];
                for(int i = 0; i < vertices; i++){ //Populate the matrix with edges
                    String[] values = input.nextLine().split(",");
                    for(int j = 0; j < vertices; j++){
                        matrix[i][j] = Integer.parseInt(values[j]);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
                System.exit(0);
            }

            intGraph graph = new intGraph(matrix); //Creates a new "intGraph" object containing the searches
            double startTime = System.nanoTime();
            double endTime;
            matrix = graph.floydShortestPath(matrix);
            endTime = System.nanoTime();
            double floydMiliTime = (endTime - startTime) / 1000000; //Tracks timing for floyd's
            startTime = System.nanoTime();
            int[][] dijkstraMatrix = new int[matrix.length][matrix.length];
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix.length; j++){
                    dijkstraMatrix[i][j] = graph.getShortestPath(i,j);
                }
            }
            endTime = System.nanoTime();
            double dijMiliTime = (endTime - startTime) / 1000000; //Tracks timing for dijykstra's
            System.out.println("Dijkstra's execution time(ms): " + dijMiliTime + "\nFloyd's execution time(ms): "
                    + floydMiliTime);



            boolean looking = true;
            while (looking) {
                System.out.println("Please enter two vertex numbers separated by a space. Enter 0 for either to exit");
                String[] line = kb.nextLine().split(" ");
                int start = Integer.parseInt(line[0]) - 1;
                if(start <= -1){
                    looking = false;
                    continue;
                }
                if(line.length !=2){
                    System.out.println("Incorrect format");
                    continue;
                }
                int end = Integer.parseInt(line[1]) - 1;
                System.out.println("Dijkstra's min result: " + dijkstraMatrix[start][end] + "\nFloyd's min result: " +
                        matrix[start][end]);
            }
            results.write(matrix.length + "," + floydMiliTime + "," + dijMiliTime + "\n");
        }
        results.close();


    }
}
