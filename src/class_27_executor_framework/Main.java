package class_27_executor_framework;

import java.util.concurrent.ThreadFactory;

class MyThreadFactory implements ThreadFactory {

    private int count = 1;

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("MyThread-" + count++);
        return t;
    }
}

public class Main {
    public static void main(String[] args) {

        ThreadFactory factory = new MyThreadFactory();
        Runnable task = () -> System.out.println("My task ::: "+Thread.currentThread().getName());
        factory.newThread(task).start();
        factory.newThread(task).start();
        factory.newThread(task).start();
        factory.newThread(task).start();
        factory.newThread(task).start();
    }
}