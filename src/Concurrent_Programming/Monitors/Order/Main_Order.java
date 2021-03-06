package Concurrent_Programming.Monitors.Order;

public class Main_Order implements Runnable{

    private int id;
    private static Monitor_Order mon = new Monitor_Order();

    public Main_Order(int id){
        this.id = id;
    }

    @Override
    public void run() {
        int cont = mon.inc();

        System.out.println(cont);

        mon.sorting(id);
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cores =  runtime.availableProcessors();

        Thread[] threads = new Thread[cores+8];

        for(int i=0; i<threads.length; i++){
            Runnable runnable = new Main_Order(i);
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
