package Concurrent_Programming.Monitors.Base;

public class Main implements Runnable{

    private static Monitor mon =new Monitor();

    @Override
    public void run() {
        int cont = mon.inc();

        System.out.println(cont);

    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cores =  runtime.availableProcessors();

        Thread[] threads =new Thread[cores+8];

        for(int i=0; i<threads.length; i++){
            Runnable runnable = new Main();
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        for(int i=0; i<threads.length; i++){

            try {
                threads[i].join();
            } catch (InterruptedException e) {}
        }
    }
}
