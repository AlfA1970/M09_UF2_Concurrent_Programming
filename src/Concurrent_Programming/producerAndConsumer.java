package Concurrent_Programming;

//CASES: Examples with a cake production
//Consumer: if there is a cake, substract one from the variable
//Consumer: if there is not a cake, notify to the baker and the consumer goes to sleep
//Baker: is sleep waiting someone call him
//Baker: if someone call him, cook 10 cake portions and then goes to sleep again

public class producerAndConsumer implements Runnable{

    private boolean consumer;
    private static int cake = 0;

    private static Object look = new Object();

    public producerAndConsumer(boolean consumer){
        this.consumer = consumer;
    }

    public void run(){
        while (true){
            if(consumer){
                eating();

            } else {
                baking();
            }
        }
    }

    private void baking(){
        synchronized (look){
            if(cake==0){
                cake = 10;
                System.out.println("I'm the baker and there are " + cake + " portions left");
                look.notifyAll(); // wake up all threads
            }

            try {
                look.wait(); // The baker goes to sleep when has backed the cake portions
            }catch (Exception e){}
        }
    }

    private void eating(){
        synchronized (look){
            if(cake>0){
                cake--;
                System.out.println("I'm the consumer, there are " + cake + " cake portions");
                try {
                    Thread.sleep(1500); // Force the thread to wait for one second.
                } catch (InterruptedException e) {}

            } else {
                look.notifyAll(); // Wake up all threads if there aren't cake portions.

                try {
                    look.wait();
                } catch (InterruptedException e) {}
            }
        }
    }

    public static void main(String[] args) {
        int nthreads = 11;

        Thread[]threads = new Thread[nthreads];

        for(int i=0; i<threads.length; i++){
            Runnable runnable = null;

            if(i!=0){
                runnable = new producerAndConsumer(true);

            } else {
                runnable = new producerAndConsumer(false);
            }

            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        for(int i=0; i<threads.length; i++){
            try{
                threads[i].join();

            }catch (Exception e){}
        }
    }
}
