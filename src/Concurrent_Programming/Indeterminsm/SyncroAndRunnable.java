package Concurrent_Programming.Indeterminsm;

// RUNNABLE: Sometimes we can't distribute the elements so that each thread
// manage different items. In this case we can use the implementation of the Runnable interface.
// This method of throwing threads allows you to use the Thread class when is instanced  a Runnable object.

public class SyncroAndRunnable implements Runnable{

    private static int cont = 0;
    private static Object object = new Object();

    @Override
    public void run() {
        for(int i=0; i < 120000; i++){
            synchronized (object){
                cont++;
            };
        }
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cores = runtime.availableProcessors();

        Thread[] threads = new Thread[cores];

        for(int i=0; i<threads.length; i++){
            Runnable runnable = new SyncroAndRunnable();
            threads[i] = new Thread(runnable);  // Initialize a thread vector with a runnable object
            threads[i].start();
        }

        for(int i=0; i<threads.length; i++) {

            try {
                threads[i].join();
            } catch (Exception e) {
            }
        }

        System.out.println(cont);
    }
}
