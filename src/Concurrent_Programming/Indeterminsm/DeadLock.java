package Concurrent_Programming.Indeterminsm;

/* DEADLOCK: Occurs when the same thread is inside of two or more "synchronized"
   methods with differents "locks". The program execution is blocked and can't
   continue. How more cores have the processor, more possibilites there are that
   deadlock is produced. That usually happens from eight or more cores.
*/

import java.util.GregorianCalendar;
import java.util.Random;

public class DeadLock implements Runnable{

    private  int id;
    private static Random lockA = new Random();
    private static GregorianCalendar lockB = new GregorianCalendar();

    public DeadLock(int id){
        this.id = id;
    }

    @Override
    public void run() {
        if( id % 2 == 0){
            synchronized(lockA){
                showA();
            }
        } else{
            synchronized(lockB){
                showB();
            }
        }
    }

    private void showA(){
        synchronized(lockB){
            System.out.println("I'm the thread: " + id);
        }
    }

    private void showB(){
        synchronized(lockA){
            System.out.println("I'm the thread: " + id);
        }
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cores = runtime.availableProcessors();

        Thread[]threads = new Thread[cores + 30];

        for(int i=0; i<threads.length; i++){
            Runnable runnable = new DeadLock(i);
            threads[i] =new Thread(runnable);
            threads[i].start();
        }

        for(int i=0; i<threads.length; i++){

            try {
                threads[i].join();;
            }catch (Exception e){}
        }

        System.out.println("I'm the main thread");

    }
}
