package Concurrent_Programming.Monitors.Order;

// Monitor:
// Provides mutual exclusion in each of the method.
// For to be a monitor, each public method have to put the word "sinchronized" before method name
// Whith this word, when one thread runs a synchronize method, the other threads cannot use another

public class Monitor_Order {

    private int cont = 0;

    public synchronized int inc(){

        for(int i=0; i<20000; i++){
            cont++;
        }

        return cont;
    }

    public synchronized  int getCont(){
        return cont;
    }

}
