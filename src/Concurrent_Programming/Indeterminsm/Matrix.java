package Concurrent_Programming.Indeterminsm;

// Multiply by 10 the elements of the matrix concurrently and measure the time

import java.util.Random;

public class Matrix  extends Thread{

    private static int size = 4;
    private static int [][]matrix =new int[size][size];

    private int start, end;

    public Matrix(int start, int end){
        this.start = start;
        this.end = end;
    }

    public void run(){
        for(int i=start; i < end; i++){
            for(int j=0; j < matrix[0].length; j++){
                matrix[i][j] *= 10;
            }
        }
    }

    public static void main(String[] args) {
        Random rand =new Random(System.nanoTime());

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                matrix[i][j] = rand.nextInt(10);
            }
        }

        Matrix h1 =new Matrix(0,2);
        Matrix h2 =new Matrix(2,4);

        h1.start();
        h2.start();

        try{
            h1.join();
            h2.join();
        }catch (Exception e){}

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }

    }
}
