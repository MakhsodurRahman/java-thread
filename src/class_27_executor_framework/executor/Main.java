package class_27_executor_framework.executor;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class MakExecutorService implements AutoCloseable{

    private static final AtomicInteger poolCount = new AtomicInteger(0);
    public final ThreadFactory factory;
    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private final Set<Worker> workers = new HashSet<>();
    private volatile boolean isShutdown = false;

    public MakExecutorService(){
        this(Runtime.getRuntime().availableProcessors());
    }

    public MakExecutorService(int numOfThread) {
        this.factory = new MakThreadFactory(poolCount.incrementAndGet());
        for (int i = 0; i<numOfThread; i++){
            workers.add(new Worker());
        }
        startAllWorkerThread();
    }

    private void startAllWorkerThread(){
        for (Worker worker : workers){
            worker.start();
        }
    }

    public void execute(Runnable runnable){
        if (isShutdown){
            throw new IllegalStateException("Executor already shoutdown");
        }
        taskQueue.add(runnable);
    }

    public void shutdown(){
        if (isShutdown){
            throw new IllegalStateException("Executor already shoutdown");
        }
        this.isShutdown = true;
        for (Worker worker : workers){
            worker.stop();
        }
    }

    @Override
    public void close() throws Exception {
        while (true){
            boolean flag = true;
            for (Worker worker : workers){
                if(worker.thread.getState() == Thread.State.WAITING){
                    flag = false;
                    break;
                }
            }
            if (flag){
                break;
            }
            Thread.sleep(1000);
        }
        shutdown();
    }


    private static class MakThreadFactory implements ThreadFactory{
        private final int poolNumber;
        private final AtomicInteger threadCount = new AtomicInteger();

        public MakThreadFactory(int poolNumber) {
            this.poolNumber = poolNumber;

        }

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(
                    Thread.currentThread().getThreadGroup(),
                    runnable,
                    "mak-pool-"+poolNumber+"-thread"+threadCount.incrementAndGet()
            );
        }
    }

    private  class Worker implements Runnable{
        private Thread thread;
        private volatile boolean isContinuable = true;

        @Override
        public void run() {
            while (isContinuable){
                try {
                    Runnable runnable = taskQueue.peek();
                    runnable.run();
                }catch (Exception ex){
                    if(!isContinuable && ex instanceof InterruptedException){
                        continue;
                    }
                    ex.getLocalizedMessage();
                }
            }
        }

        public void start(){
            this.thread = factory.newThread(this);
            this.thread.start();
        }

        public void stop(){
            this.isContinuable = false;
            this.thread.interrupt();
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        try(MakExecutorService executor = new MakExecutorService(5)) {

            executor.execute(() -> {
                System.out.println("Simple task by :: " + Thread.currentThread().getName());
            });
        }

        Thread.sleep(1000);
        System.out.println("done");



    }
}
