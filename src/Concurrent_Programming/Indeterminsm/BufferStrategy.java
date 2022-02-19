package Concurrent_Programming.Indeterminsm;

/* Buffer strategy: The use of synchronized methods in a program generates
   bottlenecks, because only one thread works while the others are waiting
   in the queue of the method. This type of strategy involves using two variables,
   one of which must be static.
   The for iteration loop for each thread and the number of times it will be
   saved in each instance of the thread. Then within the synchronized method,
   it will assign the value of each non-static variable to the static one,
   reducing in a single time the update of this one.
*/

import java.util.GregorianCalendar;
import java.util.Random;

public class BufferStrategy implements Runnable{

    private  int id;
    private static Random lockA = new Random();
    private static GregorianCalendar lockB = new GregorianCalendar();

    private int contPrivate = 0;
    private static int cont = 0;

    public BufferStrategy(int id){
        this.id = id;
    }

    @Override
    public void run() {
        for(int i=0; i < 20000; i++){
            contPrivate++;
        }
        synchronized (lockA){
            cont += contPrivate;
        }

    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cores = runtime.availableProcessors();

        Thread[]threads = new Thread[cores+8];

        for(int i=0; i<threads.length; i++){
            Runnable runnable = new BufferStrategy(i);
            threads[i] =new Thread(runnable);
            threads[i].start();
        }

        for(int i=0; i<threads.length; i++){

            try {
                threads[i].join();;
            }catch (Exception e){}
        }

        System.out.println("The amount of itarations are: " + cont);

    }
}
