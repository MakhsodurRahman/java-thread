package class_28_executor_service;

import utils.Utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,10, TimeUnit.SECONDS,queue);

        for (int i = 0; i < 15; i++) {
            poolExecutor.execute(taskFactory(i));
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(poolExecutor.getQueue().size());
            Utils.sleep(100);
        }
        poolExecutor.allowCoreThreadTimeOut(true);

        System.out.println("done");

    }

    static Runnable taskFactory(int id){
        return () -> {
            System.out.println("Task-"+id+" , exe by :: "+Thread.currentThread().getName());
            Utils.sleep(5000);
        };
    }
}
