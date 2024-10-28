package class_4;

import java.util.Map;

public class Class4 {
    public static void main(String[] args) {
        System.out.println(Thread.activeCount());

        m1();

        Thread thread = new Thread();
        thread.start();


    }

    private static void m1() {
        Map<Thread,StackTraceElement[]> map = Thread.getAllStackTraces();
        Thread thread = Thread.currentThread();
        for (StackTraceElement e : map.get(thread)){
            System.err.println(e.getClassName());
        }
    }
}
