package Concurrent_Programming.Indeterminsm;

// INDETERMINISM: When two or more threads write in the same static variable the final varible value will be indeterminated
// For resolve indeterminism is used the mutual exclusion
// MUTUAL EXCLUSION: Only one thread every time will be able to access inside a static variable
// CRITICAL SECTION: Code part where it's could produce indeterminism


public class Indetermination extends Thread{

    public static int cont = 0;

    public void run(){
        for(int i=0; i < 10; i++){ // cont = 1000 * 1000 = 1.000.000
            cont++;
        }
    }

    public static void main(String[] args) {
        Indetermination[] vec = new Indetermination[10];

        for(int i = 0; i < vec.length; i++){
            vec[i] = new Indetermination();
            vec[i].start();
        }

        try{
            for(int i=0; i<vec.length;i++){
                vec[i].join();
            }

        }catch (Exception e){}

        System.out.println(cont);
    }
}
