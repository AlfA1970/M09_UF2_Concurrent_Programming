package Concurrent_Programming.Indeterminsm;

// RUNNABLE: Sometimes we can't distribute the elements so that each thread
// manage different items. In this case we can use the implementation of the Runnable interface.
// This method of throwing threads allows you to use the Thread class when is instanced  a Runnable object.

// SYNCHRONIZED(): This method allows that only one thread access to write into the variable.
//It needs a lock to control that only one thread it's inside. When the thread will finish
//to write, will go out from class synchronized allowing to go in the next thread.
//In this example, the for loop is inside the sinchonized method. Every
//thread will do all the iterations when go in. Reducing the process time and optimizing the resources.

public class SyncroAndRunnable implements Runnable {

    private static int cont = 0;
    private static Object lock = new Object();

    @Override
    public void run() {

        synchronized (lock) {
            for (int i = 0; i < 1000; i++) {
                cont++;
            }
        }
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cores = runtime.availableProcessors();

        Thread[] threads = new Thread[cores];

        for (int i = 0; i < threads.length; i++) {
            Runnable runnable = new SyncroAndRunnable();
            threads[i] = new Thread(runnable);  // Initialize a thread vector with a runnable object
            threads[i].start();
        }


        for (int i = 0; i < threads.length; i++) {

            try {
                threads[i].join();
            } catch (Exception e) {
            }
        }

        System.out.println(cont);
    }
}
