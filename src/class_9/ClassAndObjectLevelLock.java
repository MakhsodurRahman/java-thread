package class_9;

public class ClassAndObjectLevelLock {

    /*
    synchronized void even(){
        for (int i = 2; i<=10;i+=2){
            System.out.println("even     : "+i);
        }
    }

    synchronized void odd(){
        for (int i = 1; i<=10;i+=2){
            System.out.println("odd     : "+i);
        }
    }



     synchronized void even(){
        for (int i = 2; i<=10;i+=2){
            System.out.println("even     : "+i);
        }
    }

      void odd(){
        for (int i = 1; i<=10;i+=2){
            System.out.println("odd     : "+i);
        }
        }
     */

    synchronized void even(){
        for (int i = 2; i<=10;i+=2){
            System.out.println("even     : "+i);
        }
    }

    void odd() {
       synchronized (this){
           for (int i = 1; i <= 10; i += 2) {
               System.out.println("odd     : " + i);
           }
       }
    }

    public static void main(String[] args) {
        ClassAndObjectLevelLock obj = new ClassAndObjectLevelLock();
        ClassAndObjectLevelLock obj2 = new ClassAndObjectLevelLock();

        new Thread(() -> {
            obj.even();// this is  class level lock
        }).start();

        new Thread(()->{
            obj.odd();// this is  class level lock
        }).start();
    }
}
