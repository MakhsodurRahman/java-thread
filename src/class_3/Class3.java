package class_3;

public class Class3 {
    static int value = 0;
    public static void main(String[] args) throws InterruptedException {

    /*
        Thread t = new Thread(()->{
            try{
                System.out.println("task-1");
                Thread.sleep(5000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
            System.out.println("resource/value "+value);
            System.out.println("task exec done");
        });



        t.start();
        Thread.sleep(1000);
        value = 10;
        t.interrupt();
        System.out.println("main end");

     */

        Thread t = new Thread(()->{

            Thread tt = Thread.currentThread();
            long count = 0;
//            while (!Thread.interrupted()){
//                count++;
//            }
            while (!tt.isInterrupted()){
                count++;
            }
            System.out.println("count value is  "+count);
            System.out.println("interrupted status : "+tt.isInterrupted());
            System.out.println("task exec done");
        });



        t.start();
        Thread.sleep(100);
        value = 10;
        t.interrupt();
        System.out.println("main end");
    }
}
