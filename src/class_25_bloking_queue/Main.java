package class_25_bloking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> value = new ArrayBlockingQueue<>(2);

        /*
        // store the data
        value.offer(33);// if not store then not throw the exception
        value.add(44);// // if not store then  throw the exception
        value.put(88);// when capacity reduce then try to put other wise block the program

        System.out.println(value);

         */

        // get the data
        System.out.println(value.poll());// if not any data found return null
        //System.out.println(value.remove());// if data not found return NoSuchElementException


        new Thread(()->{

            try {
                Thread.sleep(3000);
                value.put(99);
                value.put(99);
                value.put(99);
                value.put(99);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();


        System.out.println(value.take());// block the program for when data available then run this program

//        Thread.sleep(1000);

    }
}
