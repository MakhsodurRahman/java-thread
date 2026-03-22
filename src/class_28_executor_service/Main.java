package class_28_executor_service;

import utils.Utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(1);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5, 10
                , TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                r -> {
                    Thread t = new Thread(r);
                    t.setName("Worker-thread-" + count.getAndIncrement());
                    t.setDaemon(true);
                    return t;
                });



        executor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                Utils.sleep(1000);
                System.out.println(Thread.currentThread().getName()+ " :: "+i);
            }
        });

        executor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                Utils.sleep(1000);
                System.out.println(Thread.currentThread().getName()+ " :: "+i);
            }
        });


        Utils.sleep(5000);
        System.out.println("Main done");
    }

}
