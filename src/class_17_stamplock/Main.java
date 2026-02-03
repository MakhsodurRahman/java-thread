package class_17_stamplock;

import java.util.concurrent.locks.StampedLock;

class BankAccount {
    private double balance;
    private StampedLock lock = new StampedLock();

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        long stamp = lock.writeLock();
        try {
            balance += amount;
            System.out.println("Deposit " + amount + " And update balance : " + balance);
        } finally {
            lock.unlock(stamp);
        }
    }

    public void checkBalance() {
        long stamp = lock.tryOptimisticRead();
        double currentBalance = balance;

        Main.sleep(500);

        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentBalance = balance;
            } finally {
                lock.unlock(stamp);
            }

        }


        System.out.println("Current balance :: " + currentBalance);
    }


    public void withdraw(double amount) {
        long stamp = lock.readLock();
        try {
            if (amount <= balance) {
                long upStamp = lock.tryConvertToWriteLock(stamp);
                if (upStamp != 0) {
                    stamp = upStamp;
                    balance -= amount;
                } else {
                    lock.unlockRead(stamp);
                    stamp = lock.writeLock();
                    balance -= amount;
                }
            } else {
                System.out.println("Insufficient balance");
            }
        } finally {
            lock.unlock(stamp);
        }

    }


}

public class Main {
    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount(1000);

        new Thread(bankAccount::checkBalance).start();
        new Thread(() -> {
            bankAccount.deposit(500);
        }).start();
    }

    public static void sleep(int i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
