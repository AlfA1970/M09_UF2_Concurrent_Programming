package Concurrent_Programming;

public class VectorThrow extends Thread{

    private int id;

    public VectorThrow (int id){
        this.id = id;
    }

    public void run(){
        System.out.println("Is the thread -> " + id);
    }

    public static void main(String[] args) {
        VectorThrow[] vec = new VectorThrow[5];

        // Could be launched threads from vectors positions using a for loop.
        for(int i=0; i < vec.length; i++){
            vec[i] = new VectorThrow(i+1);
            vec[i].start();
        }

        try{
            // The join () function controls that the main thread runs after each selected vector.
            // vec[0].join();
            // vec[3].join();

            // With a for loop, the main thread always it will run last after all other theads.
            for(int i = 0; i < vec.length; i++){
                vec[i].join();
            }


        }catch (Exception e){}

        // Could be launched threads from the diferents vectors positions (array positions)
        // vec[0].start();
        // vec[1].start();

        System.out.println("Is the thread -> main ");
    }

}
