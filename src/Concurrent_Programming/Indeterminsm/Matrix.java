package Concurrent_Programming.Indeterminsm;

// Multiply by 10 the elements of the matrix concurrently and measure the time

import java.util.Random;

public class Matrix  extends Thread{

    private static int size = 19200;
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

        double initTime, endTime;

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                matrix[i][j] = rand.nextInt(10);
            }
        }

        initTime = System.nanoTime(); // Time in nanoseconds;

        Matrix h1 =new Matrix(0,19200);
        //Matrix h2 =new Matrix(4000,8000);
        //Matrix h3 =new Matrix(4000,6000);
        //Matrix h4 =new Matrix(6000,8000);

        /* Playing with the different instances it can observe how long it takes
         processors to finish the work assigned to each one. */
        h1.start();
        //h2.start();
        //h3.start();
        //h4.start();

        try{
            h1.join();
            //h2.join();
            //h3.join();
            //h4.join();
        }catch (Exception e){}

        endTime = System.nanoTime() - initTime; // Total time in nanoseconds

        System.out.println((endTime / 1000000) + " miliseconds");

        /*
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
               // System.out.print(matrix[i][j] + " ");
            }

            //System.out.println();
        } */

    }
}
