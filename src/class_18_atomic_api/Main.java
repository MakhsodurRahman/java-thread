package class_18_atomic_api;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

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

         */

        AtomicReference<Main> mainAtomicReference = new AtomicReference<>();
        Config config = new Config();
        AtomicMarkableReference<Config> configAtomicMarkableReference = new AtomicMarkableReference<>(config,false);
    }
}
