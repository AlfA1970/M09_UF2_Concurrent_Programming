package Concurrent_Programming;

import java.util.Random;

public class ThreadPool implements Runnable{

    private static int size = 0;
    private static int[][] matrix = new int[size][size];

    @Override
    public void run() {


    }

    public static void main(String[] args) {
        Random rand = new Random(System.nanoTime());
        double initTime, endTime;

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                matrix[i][j] = rand.nextInt(10);
            }
        }

        initTime = System.nanoTime();

        Thread[]threads =new Thread[size];

        for(int i = 0; i < threads.length; i++){
            Runnable runnable = new ThreadPool();
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        for(int i = 0; i < threads.length; i++){
            try {
                threads[i].join();

            }catch (Exception e){}

        }

        endTime = System.nanoTime() - initTime;

        System.out.println((endTime/1000000) + "milliseconds");

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                System.out.println(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
