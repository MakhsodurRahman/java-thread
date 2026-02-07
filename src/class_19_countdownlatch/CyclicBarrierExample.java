package class_19_countdownlatch;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("Race start with thread :: " + Thread.currentThread().getName());
        });


        new Thread(() -> {
            sleep(100);
            System.out.println("I am on track :: " + Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
                race();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Race done with :: " + Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> {
            sleep(500);
            System.out.println("I am on track   :: " + Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
                race();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Race done with :: " + Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> {
            sleep(1000);
            System.out.println("I am on track  :: " + Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
                race();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Race done with :: " + Thread.currentThread().getName());
            }
        }).start();


    }

    static Random random = new Random();

    static void race() {
        sleep(random.nextInt(100) + 50);
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
