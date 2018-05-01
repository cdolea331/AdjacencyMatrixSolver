import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * Generates a user defined amount of matrices with user specified dimensions and density, as well as stores them
 * in .csv format to be used with AdjacencyMatrixSolver. Small matrices are made artificially more dense to avoid
 * disconnected graphs
 */
public class AdjacencyMatrixGenerator {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter # of matrices");
        int count = kb.nextInt();
        String filename = "Matrix";


        for(int i = 0; i < count; i++){
            int width;
            double density;
            System.out.println("Enter dimensions of adjacency matrix #" + (i + 1));
            width = kb.nextInt();
            System.out.println("Enter population %(double, automatic adjustment(increase) for small matrices");
            density = kb.nextDouble();
            int[][] matrix = generateMatrix(width, density);
            try{
                BufferedWriter writer = new BufferedWriter( new FileWriter(filename + i + ".txt"));
                for(int j = 0; j < width; j++){
                    for(int k = 0; k < width; k++){
                        if(k == width-1){
                            writer.append(matrix[j][k] + "");
                        }else {
                            writer.append(matrix[j][k] + ",");
                        }
                    }
                    writer.append("\n");
                }
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }


        }
    }

    public static int[][] generateMatrix(int width, double density){
        Random rng =  new Random();
        int[][] matrix = new int[width][width];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                if(i != j && ((rng.nextDouble() + ((double)1 / (double)width))*100) >= (100-density)){ //The smaller the matrix
                    matrix[i][j] = rng.nextInt(100);   //The less sparse as to prevent disconnected graphs before
                }else {                                       //path checking can be implemented.
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }
}
