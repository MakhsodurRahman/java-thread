package class_12_producer_consumer_pattern;

class SharedData {
    private  boolean running = true; // Shared flag

    public void stopRunning() {
        System.out.println(Thread.currentThread().getName() + " is stopping the thread.");
        running = false; // Update the flag
    }

    public void doWork() {
        System.out.println(Thread.currentThread().getName() + " started.");
        while (running) {
            // Thread will keep running as long as the flag is true
            System.out.println(Thread.currentThread().getName() + " is running...");
            try {
                Thread.sleep(500); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
        }
        System.out.println(Thread.currentThread().getName() + " stopped.");
    }
}

public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {
        SharedData sharedData = new SharedData();

        // Start the worker thread
        Thread workerThread = new Thread(sharedData::doWork, "Worker-Thread");
        workerThread.start();

        // Main thread waits for 2 seconds
        Thread.sleep(2000);

        // Stop the worker thread using the shared flag
        sharedData.stopRunning();

        // Ensure the worker thread has finished
        workerThread.join();

        System.out.println("Main thread finished.");
    }
}
