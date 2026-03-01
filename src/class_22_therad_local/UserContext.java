package class_22_therad_local;

public class UserContext {

    private static ThreadLocal<String> currentUser = new ThreadLocal<>();

    public static void setUser(String user) {
        currentUser.set(user);
    }

    public static String getUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}

 class TestThreadLocal {

    public static void main(String[] args) {

        Runnable task1 = () -> {
            UserContext.setUser("Rahim");
            System.out.println(Thread.currentThread().getName()
                + " User: " + UserContext.getUser());
            UserContext.clear();
        };

        Runnable task2 = () -> {
            UserContext.setUser("Karim");
            System.out.println(Thread.currentThread().getName()
                + " User: " + UserContext.getUser());
            UserContext.clear();
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}


