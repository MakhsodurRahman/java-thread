package class_25_bloking_queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class MyTask implements Delayed {

    private final String name;
    private final long time; //// execute time
    private final Integer pv;

    public MyTask(String name, long delay, Integer pv) {
        this.name = name;
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
        if (o instanceof MyTask that) {
            return that.pv.compareTo(this.pv);
        }
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
public class DelayQueueExample {

    public static void main(String[] args) throws Exception {

        DelayQueue<MyTask> queue = new DelayQueue<>();

        queue.put(new MyTask("task1", 3000,10));
        queue.put(new MyTask("task2", 1000,20));
        queue.put(new MyTask("task3", 5000,30));

        while (!queue.isEmpty()) {

            System.out.println("Waiting to take...");
            MyTask task = queue.take();

            System.out.println("Executed: " + task);
        }
    }
}