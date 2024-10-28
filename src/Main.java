
class Task2 implements Runnable{

    @Override
    public void run() {
        System.out.println("using runnable interface");
    }
}

class Task extends Thread{

    // create custom thread
    @Override
    public void run(){
        System.out.println("new thread assign name :    "+Thread.currentThread().getName());
    }
}


public class Main {
    public static void main(String[] args) {

        //Thread.currentThread().getThreadGroup().getParent().list();

        Runnable target = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<=50; i++){
                    System.out.println(i+ " "+Thread.currentThread().getName());
                }
            }
        };



        Thread thread = new Thread(target,"mak");
        thread.start();

        Task task = new Task();
        task.start();

        Task2 task2 = new Task2();
        Thread thread1 = new Thread(task2);
        thread1.start();

        System.out.println(Thread.currentThread().getName());
    }
}


/*
        class-2 question
        jokhon amra thread er nam dei na java kivabe name dei sei process
        q-2 jokon amra thread group create na korbo ba mention na korbo oi thread kon group e porbe? and how to create thread group and how to assign thread in specific group?



*/