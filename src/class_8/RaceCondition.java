package class_8;

public class RaceCondition {

    int count = 0;
    synchronized void increment(){
        count = count + 1;
    }
    public static void main(String[] args) throws InterruptedException {

        RaceCondition r1 = new RaceCondition();
        RaceCondition r2 = new RaceCondition();
        Thread thread1 = new Thread(()->{
            for (int i = 1; i<=10_000;i++){
                r1.increment();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 1; i<=20_000;i++){
                r2.increment();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(r1.count);



    }
}
