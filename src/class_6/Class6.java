package class_6;

public class Class6 {
    public  static int[] a = {1,2,3,4,5};
    private static int sum = 0;
    public static int mul = 1;
    public synchronized static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println("start task for sum");
            for (int i : a){
                sum += i;
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("end  task for sum");
        });
        t.setName("TASK");

        Thread t2 = new Thread(()->{
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("start task for multiplier");
            for (int i: a){
                mul *= i;
            }
            System.out.println("end task for multiplier");
        });

        t.start();
        t2.start();

        try {

            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("total sum and multiplication is : "+(sum+mul));

    }
}
