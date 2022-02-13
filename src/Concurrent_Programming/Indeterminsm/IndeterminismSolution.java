package Concurrent_Programming.Indeterminsm;

// Perform the multiply of all integer vector elements by 10
// The strategy for solving this problem is to divide the vector in two parts. Each thread
// will handle each piece, therefore won't access the part of the same vector. In this
// way indeterminacy doesn't occurs.

import java.util.Random;

public class IndeterminismSolution  extends Thread{

    // Define a static vector
    private final static int size = 8;
    private static int[]vec = new int[size];

    private int start, end;

    public IndeterminismSolution(int start, int end){
        this.start = start;
        this.end = end;
    }

    public void run(){
        for (int i = start; i < end; i++){
            vec[i] *= 10;
        }
    }

    public static void main(String[] args) {
        Random rand =new Random(System.nanoTime());

        for(int i=0; i < vec.length; i++){
            vec[i] = rand.nextInt(10);
        }

        IndeterminismSolution h1 = new IndeterminismSolution(0,4);
        IndeterminismSolution h2 = new IndeterminismSolution(4,8);

        h1.start();
        h2.start();

        try{
            h1.join();
            h2.join();
        }catch (Exception e){}

        for (int j : vec) {
            System.out.print(j + " ");
        }
    }
}
