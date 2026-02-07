package class_19_countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);//

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " finished work");
            latch.countDown(); // কাজ শেষের signal
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        latch.await(); // main thread এখানে wait করবে
        System.out.println("All tasks completed, main thread continues");
    }
}
