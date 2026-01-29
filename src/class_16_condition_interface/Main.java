package class_16_condition_interface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) throws Exception {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        // Read vs Read and Read vs Write
        /*new Thread(() -> {
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " :: acquired by read lock");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readLock.unlock();
                System.out.println(Thread.currentThread().getName() + " :: release by read lock");
            }
        }).start();


        Thread.sleep(1000);
        *//*readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " :: acquired by read lock");
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " :: release by read lock");
        }*//*

        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " :: acquired by write lock");
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + " :: acquired by write lock");
        }*/

        /*// write vs write
        new Thread(() -> {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " :: acquired by writeLock lock");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                writeLock.unlock();
                System.out.println(Thread.currentThread().getName() + " :: release by writeLock lock");
            }
        }).start();


        Thread.sleep(1000);
        *//*readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " :: acquired by read lock");
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " :: release by read lock");
        }*//*

        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " :: acquired by write lock");
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + " :: acquired by write lock");
        }*/


        // write with read
        new Thread(() -> {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " :: acquired by writeLock lock");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                writeLock.unlock();
                System.out.println(Thread.currentThread().getName() + " :: release by writeLock lock");
            }
        }).start();


        Thread.sleep(1000);
        /*readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " :: acquired by read lock");
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " :: release by read lock");
        }*/

        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " :: acquired by readLock lock");
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " :: acquired by readLock lock");
        }
    }
}