package class_25_bloking_queue;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10,true);
//    public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10,true, List.of(1,3,4,4,5));

    static void produce(Integer value){
        try {
             queue.put(value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static Integer consume(){
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 1; i<=10; i++){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                produce(i);
                System.out.println(Thread.currentThread().getName()+" Product value of :: "+i);
            }
        }).start();

        new Thread(()->{
            for (int i = 1; i<=10; i++){
                System.out.println(Thread.currentThread().getName()+" Product value of :: "+consume());
            }
        }).start();

    }
}
