package class_29_executor_framework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5, 10
                , TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
//                new MakThreadFactory(),
//                new MakRejectionHandler()
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        executor.prestartAllCoreThreads();

        System.out.println("Main thread done");
    }
}
