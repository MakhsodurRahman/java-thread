package class_12_producer_consumer_pattern;


import java.util.LinkedList;
import java.util.Queue;

class TaskQueue{
    private final int MAX_SIZE = 10;
    private final Queue<String> queue = new LinkedList<>();

    public synchronized void produce(String task){
        if (queue.size() == MAX_SIZE){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.add(task);
        this.notifyAll();
    }

    public synchronized String consume(){
        if(queue.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String task = queue.poll();
        this.notifyAll();
        return task;
    }
}
public class Main {
    public static void main(String[] args) {

        TaskQueue taskQueue = new TaskQueue();

        for(int i = 1; i<=3; i++){
            new Thread(()-> taskProducer(taskQueue)).start();
        }

        for (int i = 1; i<=5; i++){
            new Thread(()-> taskConsumer(taskQueue)).start();
        }
    }


    public static void taskProducer(TaskQueue taskQueue){
        int i = 0;
        while (true){
            i++;
            String task = "task-"+i;
            System.out.println("Producer "+Thread.currentThread().getName()+" by "+task);
            taskQueue.produce(task);
            Main.sleep(100);
        }
    }

    public static void taskConsumer(TaskQueue taskQueue){
        while (true){

            String task = taskQueue.consume();
            System.out.println("Consumer "+Thread.currentThread().getName()+" by "+task);
            Main.sleep(2000);
        }
    }

    public static void sleep(int value) {
        try {
            Thread.sleep(value);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
