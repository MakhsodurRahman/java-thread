package class_10;

import static java.lang.Thread.sleep;

class ServiceA{
    private int recourseA = 10;

    public synchronized void process(ServiceB serviceB) throws InterruptedException {
        sleep(5);
        System.out.println("process start by "+Thread.currentThread().getName()+"inside serviceA");
        System.out.println(serviceB.getResourceB());
        System.out.println("process end by "+Thread.currentThread().getName()+" inside serviceA");
    }

    public synchronized int getRecourseA(){
        return recourseA;
    }
}

class ServiceB{
    private int resourceB = 20;


    public synchronized void process(ServiceA serviceA) throws InterruptedException {
        sleep(5);
        System.out.println("process start by "+Thread.currentThread().getName()+"inside serviceB");
        System.out.println(serviceA.getRecourseA());
        System.out.println("process end by "+Thread.currentThread().getName()+" inside serviceB");
    }

    public synchronized int getResourceB(){
        return resourceB;
    }
}
public class DeadLockAnalysis {
    public static void main(String[] args) {

        ServiceA serviceA = new ServiceA();
        ServiceB serviceB = new ServiceB();

        Thread t0 = new Thread(()-> {
            try {
                serviceA.process(serviceB);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t1 = new Thread(()-> {
            try {
                serviceB.process(serviceA);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t0.start();
        System.out.println("main end");
    }
}
