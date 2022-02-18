package Concurrent_Programming.Indeterminsm;

import java.util.Random;

public class WaitAndNotifyAll implements Runnable {

    private int id;
    public static Random lock = new Random();
    private static int cont = 0;

    public WaitAndNotifyAll(int id){
        this.id = id;
    }

    @Override
    public void run() {
        synchronized (lock){
            while (id != cont) {
                try {
                    lock.wait();
                } catch (Exception e) {}
            }

                System.out.println("I'm the thread: " + id);
                cont++;
                lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cores = runtime.availableProcessors();

        Thread[] threads = new Thread[cores];

        for (int i = 0; i < threads.length; i++) {
            Runnable runnable = new WaitAndNotifyAll(i);
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {

            try {
                threads[i].join();
            } catch (Exception e) {
            }
        }

        System.out.println("I'm the thread: main");
    }
}
