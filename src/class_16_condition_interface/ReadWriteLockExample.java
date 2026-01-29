package class_16_condition_interface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

interface Resource {
    void read();

    void write(int value);
}

class SingleLockResource implements Resource {

    private int data;
    private Lock lock = new ReentrantLock();

    public SingleLockResource(int data) {
        this.data = data;
        System.out.println("Initial value is :: "+data);
    }

    @Override
    public void read() {
        lock.lock();
        try {
            System.out.println(data+" Read by :: "+ Thread.currentThread().getName());
        } finally {
        lock.unlock();
        }
    }

    @Override
    public void write(int value) {
        lock.lock();
        try {
            this.data += value;
            System.out.println(data + " read by :: "+Thread.currentThread().getName());
        }finally {
            lock.unlock();
        }
    }
}
class ReadWriteLockResource implements Resource{

    private int data;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();
    public ReadWriteLockResource(int data){
        this.data = data;
    }

    @Override
    public void read() {
        readLock.lock();
        try {
            System.out.println(data+" write by :: "+ Thread.currentThread().getName());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void write(int value) {
        writeLock.lock();
        try {
            this.data += value;
            System.out.println(data + " write by :: "+Thread.currentThread().getName());
        }finally {
            writeLock.unlock();
        }
    }
}

public class ReadWriteLockExample {
}
