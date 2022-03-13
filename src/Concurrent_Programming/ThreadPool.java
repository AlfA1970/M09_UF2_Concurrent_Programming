package Concurrent_Programming;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
THREAD POOL: The threads will be created, when one physical thread will be free.
In this way,  each thread will always run in parellel

To run a thread pool is used the Executor class and there are three kinds of thread pools:
   -> Fix (newFixedThreadPool()): is setted with a fix number of threads. it's the most efficient setting
      the same number of thread that have the processor.
   -> Hibrid (newCachedThreadPool()):  Hybrid (newCachedThreadPool ()): set dynamically, if a physical thread is not free,
      a new one will be created and, if it is, it will be reused
   -> Single (newSingleThreadPool()):  is setted with a single thread. In this way never will be parallelism
*/

public class ThreadPool implements Runnable{

    private int row;

    private static int size = 19200;
    private static int[][] matrix = new int[size][size];

    public ThreadPool (int row){
        this.row = row;
    }

    @Override
    public void run() {
        for(int i=0; i < size; i++){
            matrix[row][i] *= 10;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random(System.nanoTime());
        double initTime, endTime = 0;

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                matrix[i][j] = rand.nextInt(10);
            }
        }

        initTime = System.nanoTime();

        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for(int i=0; i < size; i++){
            Runnable runnable = new ThreadPool(i);
            pool.execute(runnable);
        }

        //Coroutine for threadpools
        pool.shutdown();
        while(!pool.isTerminated())

        endTime = System.nanoTime() - initTime;

        System.out.println((endTime / 1000000) + " milliseconds");
    }
}
