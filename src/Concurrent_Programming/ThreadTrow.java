package Concurrent_Programming;

// Threading is done from thread-inheriting objects
// The function start() is used to launch threads
// The threads doesn't execute in order that are thrown
// We don't know the threads execution order

public class ThreadTrow extends Thread {

    private int id;
    public ThreadTrow(int id){
        this.id = id;
    }

    // Content the instructions that will be executed when diferents thread was thrown.
    public void run(){
        System.out.println("I'm the thread -> " + id);
    }

    public static void main(String[] args) {
        // Creates each object that it will be used to launch each thread
        ThreadTrow thread1 = new ThreadTrow(1);
        ThreadTrow thread2 = new ThreadTrow(2);
        ThreadTrow thread3 = new ThreadTrow(3);

        // Throwing the first thread
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("I'm de main thread");
    }
}
