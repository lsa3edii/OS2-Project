/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectReadWrite;

/**
 *
 * @author DELL
 */
import java.util.concurrent.Semaphore;
import java.util.Random;
class ReaderWritersProblem {

    static Semaphore readLock = new Semaphore(1, true);
    static Semaphore writeLock = new Semaphore(1, true);
    static int readCount = 0;

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
                System.out.println("Thread " + Thread.currentThread().getName() + " is READING");
                Thread.sleep(1500);
                System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING");

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

                {
                    writeLock.acquire();
                    System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING");
                    Thread.sleep(2500);
                    System.out.println("Thread " + Thread.currentThread().getName() + " has finished WRITING");
                    writeLock.release();

                }
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
        Thread t4 = new Thread(read);
        t4.setName("thread 4");
        Thread t5 = new Thread(write);
        t5.setName("thread 5");
        Thread t6 = new Thread(read);
        t6.setName("thread 6");
        Thread t7 = new Thread(read);
        t7.setName("thread 7");
         
     // any thresd have write must do it join after start
        t1.start();
        t2.start();
        t2.join();
        t3.start();
        t4.start();
         
        t5.start(); 
         t5.join();
        t6.start();
         t7.start();
    }
}
