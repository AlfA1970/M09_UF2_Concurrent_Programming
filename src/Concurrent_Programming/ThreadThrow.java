package Concurrent_Programming;

// Threading is done from thread-inheriting objects
// The function start() is used to launch threads
// The threads doesn't execute in order that are thrown
// We don't know the threads execution order

public class ThreadThrow extends Thread {

    private int id;
    public ThreadThrow(int id){
        this.id = id;
    }

    // Content the instructions that will be executed when diferents thread was thrown.
    public void run(){
        System.out.println("I'm the thread -> " + id);
    }

    public static void main(String[] args) {
        // Creates each object that it will be used to launch each thread
        ThreadThrow thread1 = new ThreadThrow(1);
        ThreadThrow thread2 = new ThreadThrow(2);
        ThreadThrow thread3 = new ThreadThrow(3);

        // Throwing the first thread
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("I'm de main thread");
    }
}
