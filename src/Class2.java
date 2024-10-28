import javax.sql.DataSource;

class Task3 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i<= 20; i++){
            System.out.println((i % 2 == 0 ? "even ": "odd ")+Thread.currentThread().getState());
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Class2 {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread();
//        System.out.println(thread.getState()   );
        Task3 task = new Task3();
        Thread thread = new Thread(task);
        thread.start();

        Thread.sleep(100);
        System.out.println("current thread : "+thread.getState());


        /*
        when TERMINATED state ke kore kivebe hoy eta. os thered kivabe TERMINATED hoy hardware therad kivbae kaj kore

        1.jokhon core pai na. tokhon amader thread, os thread hardware thread tokon ki kore
        2.jokhon amra sleep use kori tokhon java thread , os and hardware thered sobai sleep hoye jay?
        3.amra jokhon sleep dibo tar age to se kicho kaj kore tokhon jege uthle se to next kaj golo kore
        age runnable e ase tar por cpu pele running hoy age koto toko kaj korche seta koi mention thake je age
        jei toko korci sei golo bad diye next part running korbo?
         */

        
    }
}
