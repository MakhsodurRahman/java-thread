package class_29_executor_framework;

import utils.Utils;

import java.util.concurrent.*;

public class FutureClassTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5, 10
                , TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MakThreadFactory(),
//                new MakRejectionHandler()
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Main thread done");
                Utils.sleep(2000);
                return 1000;
            }
        });


        System.out.println(future.isDone());
        future.cancel(true);

//        Utils.sleep(2100);
        System.out.println(future.isDone());

        try {
            System.out.println(future.get(2, TimeUnit.SECONDS));// this get method is blocking call when future not resole main thread block
        } catch (CancellationException e) {
            System.err.println("CancellationException for future");
            throw new RuntimeException();
        }
        catch (InterruptedException e) {
            System.err.println("InterruptedException exception for future");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.err.println("ExecutionException exception for future");
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.err.println("timeout exception for future");
            throw new RuntimeException(e);
        }

    }
}
