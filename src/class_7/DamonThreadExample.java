package class_7;

import static java.lang.Thread.sleep;

public class DamonThreadExample {
    public static void main(String[] args) throws InterruptedException {

        Thread mainThread = Thread.currentThread();
        System.out.println("Main thread starts");

        Thread t1 = new Thread(() -> {
            System.out.println("t1 starts");
            Thread t1Info = Thread.currentThread();

            Thread t2 = new Thread(() -> {
                // Daemon thread simulates background task
                for (int i = 1; i <= 100; i++) {
                    System.out.println(i + "  | Status of t1: " + t1Info.getState() +
                            " | Status of main thread: " + mainThread.getState());
                    try {
                        sleep(100); // Simulating work by sleeping
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("t2 completes its task (won't show if daemon stops it early)");
            });

            t2.setDaemon(true); // Mark t2 as daemon
            t2.start(); // Start the daemon thread
            try {
                sleep(500); // Main work for t1
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t1 ends");
        });

        t1.start(); // Start t1 (non-daemon thread)
        sleep(10000); // Main thread waits a bit before finishing
        System.out.println("Main thread done, will now exit");
    }
}
