package class_12_producer_consumer_pattern;

class SharedData {
    private boolean running = true;

    public void stop() {
        running = false; // Thread A changes running
    }

    public void runTask() {
        while (running) { // Thread B might not see the updated value
            // Do some work
        }
    }
}