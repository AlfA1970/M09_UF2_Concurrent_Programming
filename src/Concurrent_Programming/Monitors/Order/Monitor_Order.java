package Concurrent_Programming.Monitors.Order;

// Disadvantiges using monitors:
//   - Using lockers causes "Mutual Exclusion" consequently the threads are running sequentially.
//   - More CPU & RAM consumption checking if the synchro methods are locked or not.

// Advantiges using monitors:
//   - Never causes indeterminism
//   - Allow to build reusable modules


// EXAMPLE MONITOR: Sort thread using monitors

public class Monitor_Order {

    private int cont = 0;
    private int sort = 0;

    public synchronized int inc(){

        for(int i = 0; i < 20000; i++){
            cont++;
        }

        return cont;
    }

    public synchronized void sorting(int id){
        while(id != sort){
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        System.out.println("I'm the thread " + id);
        sort++;
        notifyAll();

    }
}
