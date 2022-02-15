package Concurrent_Programming.Indeterminsm;

import java.util.Random;

/* This code shows an example of a dynamic workload distribution based on
number of cores that the processor has on the computer running it */

public class DinamicConcurrency extends Thread {

    private static int size = 380;
    private static int [][]matrix =new int[size][size];

    private int start, end;

    public DinamicConcurrency(int start, int end){
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
        Runtime runtime = Runtime.getRuntime(); // Creates an intances of runtime class
        int cores = runtime.availableProcessors(); // Saves the number of cores on the computer where it's running.

        Random rand =new Random(System.nanoTime());

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                matrix[i][j] = rand.nextInt(10);
            }
        }

        Thread[]threads = new Thread[cores];

        int range = size / cores;
        int init = 0;
        int finish = range;

        double initTime = System.nanoTime();

        for(int i=0; i < cores;  i++){

            // Checks if the number of cores are even or odd
            if(i!= cores -1) {
                threads[i] = new DinamicConcurrency(init, finish);
                threads[i].start();

                init = finish;
                finish += range;

            } else {
                threads[i] = new DinamicConcurrency(init, size);
                threads[i].start();
            }
        }

        for(int i = 0; i < cores; i++) {
            try {
                threads[i].join();

            } catch (Exception e) {}
        }

        double endTime = System.nanoTime() - initTime;

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }

        System.out.print("\nTime working: " + (endTime / 1000000) + " milliseconds\n");

    }
}
