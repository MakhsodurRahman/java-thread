package class_28_executor_service;

import utils.Utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class MakRejectionHandler implements RejectedExecutionHandler {

    ThreadPoolExecutor differentExecutor = new ThreadPoolExecutor(
            5,
            5, 10
            , TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10));

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        differentExecutor.execute(r);
        //TODO
        //When use this real project store this task in db or other and after some time exec or run any other operation
    }
}

class MakThreadFactory implements ThreadFactory {
    AtomicInteger count = new AtomicInteger(1);
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("Worker-thread-" + count.getAndIncrement());
        t.setDaemon(true);
        return t;
    }
}

public class Main {
    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5, 10
                , TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MakThreadFactory(),
                new MakRejectionHandler()
        );


        for (int i = 0; i < 16; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.println("task id :: " + finalI+" exec by : "+ Thread.currentThread().getName());
            });

        }


        Utils.sleep(5000);
        System.out.println("Main done");
    }

}
