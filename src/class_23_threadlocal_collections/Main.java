package class_23_threadlocal_collections;

import static java.lang.Thread.sleep;

public class Main {
    static ThreadLocal<String> local = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            local.set("parent");
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            new Thread(()->{
                System.out.println(local.get());
                local.set("child");
                System.out.println(local.get());

            }).start();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(local.get());
        }).start();
    }
}
