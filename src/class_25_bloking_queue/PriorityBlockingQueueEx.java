package class_25_bloking_queue;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

class Task{
    private final String name;
    private final Integer pv;

    Task(String name, Integer pv) {
        this.name = name;
        this.pv = pv;
    }

    public Integer getPv() {
        return pv;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class PriorityBlockingQueueEx {
    public static void main(String[] args) {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>(10, (o1, o2) -> o2.getPv().compareTo(o1.getPv()));

        queue.add(new Task("task-1",10));
        queue.add(new Task("task-2",3));
        queue.add(new Task("task-5",3));
        queue.add(new Task("task-6",4));
        queue.add(new Task("task-4",5));

        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }

        System.out.println(queue);

    }
}
