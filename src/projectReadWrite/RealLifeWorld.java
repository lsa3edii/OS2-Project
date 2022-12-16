/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectReadWrite;

/**
 *
 * @author DELL
 */
import java.util.Random;
import java.util.concurrent.Semaphore;
      class BankAccount {

        private int acountNumber;
        private int balance;
        private String custmerName;

        public BankAccount(int acountNum, int bal, String cusName) {
            acountNumber = acountNum;
            balance = bal;
            custmerName = cusName;
        }

        public void setAcountNumber(int accountNumber) {
            acountNumber = accountNumber;
        }

        public void setBalance(int balnce) {
            balance = balnce;
        }

        public void setcustmerName(String custmrName) {
            custmerName = custmrName;
        }

        public int getAcountNumber() {
            return acountNumber;
        }

        public int getBalance() {
            return balance;
        }

        public String getcustmerName() {
            return custmerName;
        }

    }
class ReaderWritersProblemCashMachines {

 

    static Semaphore readLock = new Semaphore(1, true);
    static Semaphore writeLock = new Semaphore(1, true);
    static int readCount = 0;
    static BankAccount b1 = new BankAccount(2120, 2039, "Ahmed");
     static BankAccount b2 = new BankAccount(16788, 4090, "Ali");
    static BankAccount b3 = new BankAccount(2290, 3284, "Mohammed");

    ;

    static class Read implements Runnable {

        @Override
        public void run() {

            try {
                //Acquire Section
                readLock.acquire();
                readCount++;
                if (readCount == 1) {
                    writeLock.acquire();
                }
                readLock.release();

                //Reading section
            
                Random random = new Random();
                int x = random.nextInt(3);
                if (x == 1) {

                    System.out.println("Thread " + Thread.currentThread().getName() + " is READING BankAccount b1 " + "AcountNumber is " + b1.getAcountNumber() + "   Balance is " + b1.getBalance() + "   custmerName is " + b1.getcustmerName());
                    Thread.sleep(1500);
                    System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING  BankAccount b1");
                } else if (x == 2) {

                    System.out.println("Thread " + Thread.currentThread().getName() + " is READING BankAccount b2 " + "AcountNumber is " + b2.getAcountNumber() + "   Balance is " + b2.getBalance() + "   custmerName is " + b2.getcustmerName());
                    Thread.sleep(1500);
                    System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING BankAccount b2");
                } else {

                    System.out.println("Thread " + Thread.currentThread().getName() + " is READING BankAccount b3 " + "AcountNumber is " + b3.getAcountNumber() + "    Balance is " + b3.getBalance() + "     custmerName is " + b3.getcustmerName());
                    Thread.sleep(1500);
                    System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING BankAccount b3");
                }
            

                //Releasing section
                readLock.acquire();
                readCount--;
                if (readCount == 0) {
                    writeLock.release();
                }
                readLock.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Write implements Runnable {

        @Override
        public void run() {
            try {
                writeLock.acquire();
                
                Random random = new Random();
                int x = random.nextInt(3);
                if (x == 1) {
                    //   BankAccount b1= new BankAccount(2290,2000.239,"Ahmed");
                    int u = random.nextInt(2000);
                    int g = random.nextInt(5000);
                    b1.setAcountNumber(u);

                    b1.setBalance(g);
                    System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING in BankAccount b1");
                    Thread.sleep(2500);
                    System.out.println("Thread " + Thread.currentThread().getName() + " has finished WRITING of BankAccount b1");
                } else if (x == 2) {
                    int n = random.nextInt(10000);
                    int w = random.nextInt(2000);
                    b2.setAcountNumber(n);
                    b1.setBalance(w);
                    System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING BankAccount b2");
                    Thread.sleep(2500);
                    System.out.println("Thread " + Thread.currentThread().getName() + " has finished WRITING BankAccount b2");
                } else if (x == 3) {
                    int f = random.nextInt(1000);
                    int k = random.nextInt(4000);
                    b1.setAcountNumber(f);
                    b1.setBalance(k);
                    System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING BankAccount b3");
                    Thread.sleep(2500);
                    System.out.println("Thread " + Thread.currentThread().getName() + " has finished WRITINGBankAccount b3");
                }

                writeLock.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Read read = new Read();
        Write write = new Write();

        Thread t1 = new Thread(read);
        t1.setName("thread 1");
        Thread t2 = new Thread(write);
        t2.setName("thread 2");
        Thread t3 = new Thread(read);
        t3.setName("thread 3");
//        Thread t4 = new Thread(read);
//        t4.setName("thread 4");
//        Thread t5 = new Thread(write);
//        t5.setName("thread 5");
//        Thread t6 = new Thread(read);
//        t6.setName("thread 6");
//        
//        Thread t7 = new Thread(read);
//        t7.setName("thread 7");
//        Thread t8 = new Thread(read);
//        t8.setName("thread 8");
//        Thread t9 = new Thread(read);
//        t9.setName("thread 9");
//        
        t1.start();
        t2.start();
        t2.join();
        t3.start();
       
//        t4.start();
//        t5.start();
//        t5.join();
//        t6.start();
//        t7.start();
//        t7.join();
//        t8.start();
//        t9.start();
         
         
         

    }
}
