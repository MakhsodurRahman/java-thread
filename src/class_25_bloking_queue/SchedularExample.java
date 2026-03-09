package class_25_bloking_queue;


import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Delayed {

    private final Runnable task;
    private final long time; //// execute time
    private final Integer pv;

    public DelayedTask(Runnable task, long delay, Integer pv) {
        this.task = task;
        this.time = System.currentTimeMillis() + delay;
        this.pv = pv;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = time - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o instanceof DelayedTask that) {
            return that.pv.compareTo(this.pv);
        }
        return 0;
    }

    public Runnable getTask() {
        return task;
    }
}

class TaskSchedular{
    DelayQueue<DelayedTask> queue = new DelayQueue<>();

    public void schedule(Runnable task, long time){
        var delayTask = new DelayedTask(task,time,0);
        queue.add(delayTask);
    }

    public TaskSchedular() {
        executor();
    }

    public void executor(){
        var worker = new Thread(()->{
            while (true){
                try {
                    queue.take().getTask().run();
                }catch (Exception exception){

                }
            }
        },"worker-thread");
        worker.start();
    }
}
public class SchedularExample {
    public static void main(String[] args) {

        TaskSchedular schedular = new TaskSchedular();

        schedular.schedule(()->{
            System.out.println("my-task");
        },3000);

        schedular.schedule(()->{
            for (int i = 0; i<100; i++)
                System.out.println(Thread.currentThread().getName()+" :: "+i);
        },7000);

    }
}
