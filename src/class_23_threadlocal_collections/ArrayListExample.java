package class_23_threadlocal_collections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class ArrayListExample {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new CopyOnWriteArrayList<>();
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(()->{
            for (int i = 1; i<=1000; i++){
                list.add(i);
            }
            latch.countDown();
        }).start();

        new Thread(()->{
            for (int i = 1001; i<=2000; i++){
                list.add(i);
            }
            latch.countDown();
        }).start();

        latch.await();

        list.forEach(System.out::println);
        System.out.println(list.contains(null));
        System.out.println(list.size());

    }
}
