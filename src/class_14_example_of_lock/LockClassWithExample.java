package class_14_example_of_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount{

    Lock lock = new ReentrantLock();
    int balance = 0;

    public void deposit(int amount){
        if (amount <=0){
            System.out.println("Invalid amount");
            return;
        }

        balance += amount;
    }

    public void withdraw(int amount){
        lock.lock();
        if(amount <= 0){
            System.out.println("Invalid amount");
            return;
        }

        if (amount > balance){
            System.out.println("Insufficient balance");
            return;
        }
        LockClassWithExample.sleep(100);
        balance -= amount;

        lock.unlock();
    }


    public int currentBalance(){
        return this.balance;
    }
}

public class LockClassWithExample {

    public static void main(String[] args) {


        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(10000);
        //bankAccount.withdraw(500);


        new Thread(()->{
            bankAccount.withdraw(10000);
        }).start();

        new Thread(()->{
            //LockClassWithExample.sleep(100);
            bankAccount.withdraw(10000);
        }).start();

        LockClassWithExample.sleep(1000);
        System.out.println(bankAccount.currentBalance());
    }


    public static void sleep(int time){
        try{
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
