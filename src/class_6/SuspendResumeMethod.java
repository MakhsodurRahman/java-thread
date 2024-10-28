package class_6;

public class SuspendResumeMethod {
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(()->{
            System.out.println("start");
            for (int i = 0; i<100; i++){
                System.out.println("the value of i : "+i);
            }
        });

        t.start();
        Thread.sleep(8);
        t.suspend();
        Thread.sleep(5_000);
        t.resume();
    }
}
