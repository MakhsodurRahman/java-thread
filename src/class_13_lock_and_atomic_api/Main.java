package class_13_lock_and_atomic_api;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static synchronized void m(){
        System.out.println("lock acquired by 1"+Thread.currentThread().getName());
        synchronized (Main.class){
            System.out.println("lock acquired by 2"+Thread.currentThread().getName());
            synchronized (Main.class){
                System.out.println("lock acquired by 3"+Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        new Thread(()->{
            try {
                Thread.sleep(100);
                lock.lock();// if use synchronize block thread are block if use lock thread are waiting
                System.out.println("lock acquired by other thread :: "+Thread.currentThread().getName());
                lock.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        lock.lock();
        System.out.println("lock acquired by 1"+Thread.currentThread().getName());
        lock.lock();
        System.out.println("lock acquired by 2 :: "+Thread.currentThread().getName());
        lock.lock();
        System.out.println("lock acquired by 3 :: "+Thread.currentThread().getName());

        System.out.println(lock.getHoldCount());
        int count = lock.getHoldCount();
        System.out.println("main done");
        Thread.sleep(1000);
        for (int i = 0; i<count;i++){
            lock.unlock();
        }
    }
}
