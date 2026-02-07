package class_18_atomic_api;

import java.util.concurrent.atomic.*;

class Config{

}
public class Main {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        /*int value = atomicInteger.incrementAndGet();
        System.out.println(value);
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println(andIncrement);
        int i = atomicInteger.decrementAndGet();
        System.out.println(i);
        System.out.println(atomicInteger.getAndDecrement());
        int andDecrement = atomicInteger.getAndDecrement();
        System.out.println(andDecrement);

        // this method work on current value and previous value
        int accumulateAndGet = atomicInteger.accumulateAndGet(20, (prev, curr) -> prev * curr);
        System.out.println(accumulateAndGet);



        // if match 1st value then replace this value
        boolean isSame = atomicInteger.compareAndSet(10, 5);
        System.out.println(isSame);
        System.out.println(atomicInteger.get());

        atomicInteger.addAndGet(100);
        System.out.println(atomicInteger.get());



        AtomicReference<Main> mainAtomicReference = new AtomicReference<>();
        Config config = new Config();
        AtomicMarkableReference<Config> configAtomicMarkableReference = new AtomicMarkableReference<>(config,false);



        Config config = new Config();
        AtomicStampedReference<Config> atomicStampedReference = new AtomicStampedReference<>(config,0);



        // A-B-A problem set
        Config a = new Config();
        AtomicReference<Config> atomicReference = new AtomicReference<>(a);

        new Thread(()->{
            sleep(100);
            System.out.println(Thread.currentThread().getName() +" : "+ atomicReference.compareAndSet(a,new Config()));
        }).start();

        Config b = new Config();
        System.out.println(Thread.currentThread().getName() +" : "+ atomicReference.compareAndSet(a,b));
        sleep(50);


        System.out.println(Thread.currentThread().getName() +" : "+ atomicReference.compareAndSet(b,a));



        //Resolve  A-B-A problem set with AtomicStampedReference
        Config a = new Config();
        AtomicStampedReference<Config> atomicReference = new AtomicStampedReference<>(a,1);

        new Thread(()->{
            sleep(100);
            int stamp = atomicReference.getStamp();
            System.out.println(Thread.currentThread().getName() +" : "+ atomicReference.compareAndSet(a,new Config(),stamp,stamp +1));
        }).start();

        Config b = new Config();
        int stamp = atomicReference.getStamp();
        System.out.println(Thread.currentThread().getName() +" : "+ atomicReference.compareAndSet(a,b,stamp,stamp + 1));
        sleep(50);


        System.out.println(Thread.currentThread().getName() +" : "+ atomicReference.compareAndSet(b,a,stamp,stamp + 1));


        int a = 10;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                a += 20;
            }
        };

        new Thread(runnable);
        System.out.println(a);
 */

        AtomicInteger atomic  = new AtomicInteger(10);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                atomic = new AtomicInteger(20);
            }
        };


    }

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
