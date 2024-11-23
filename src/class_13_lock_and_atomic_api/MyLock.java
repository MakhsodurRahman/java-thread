package class_13_lock_and_atomic_api;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

class MakLock{
    public volatile AtomicBoolean flag = new AtomicBoolean(false);

    public void lock(){
        while (!flag.compareAndSet(false,true)){// AtomicOperation -> CAS

        }

    }

    public void unlock(){
        flag.set(false);
    }
}
public class MyLock {
    public static void main(String[] args) {
        MakLock lock = new MakLock();

        new Thread(()->{
            sleep(100);
            lock.lock();
            System.out.println("lock acquired by "+Thread.currentThread().getName());
            lock.unlock();

        }).start();

        lock.lock();
        sleep(500);
        System.out.println("work done");
        sleep(500);
        lock.unlock();
    }

    public static void sleep(int value) {
        try {
            Thread.sleep(value);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
