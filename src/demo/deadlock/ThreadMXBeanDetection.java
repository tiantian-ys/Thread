package demo.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadMXBeanDetection implements  Runnable {
    static TransferMoney.Person person1 = new TransferMoney.Person(500);

    static TransferMoney.Person person2 = new TransferMoney.Person(500);
    int flag = 0;

    public static void main(String[] args) throws InterruptedException {
        /** 模拟两个用户 */
        ThreadMXBeanDetection transferMoney1 = new ThreadMXBeanDetection();
        ThreadMXBeanDetection transferMoney2 = new ThreadMXBeanDetection();

        transferMoney1.flag = 0;
        transferMoney2.flag = 1;

        Thread thread1 = new Thread(transferMoney1);
        Thread thread2 = new Thread(transferMoney2);
        thread1.start();
        thread2.start();
            
        Thread.sleep(1000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null && deadlockedThreads.length > 0) {
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
                System.out.println("发现死锁" + threadInfo.getThreadName());
            }
        }

    }

    @Override
    public void run() {

        if (flag == 0) {
            synchronized (person1) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (person2) {
                    transferMoney(person1, person2, 200);
                }
            }
        }

        if (flag == 1) {
            synchronized (person2) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (person1) {
                    transferMoney(person2, person1, 200);
                }
            }
        }
    }
    /**
     * @param from 转出
     * @param to 转入
     * @param money 金额
     */
    public static void transferMoney(TransferMoney.Person from, TransferMoney.Person to, int money) {
        if (from.money - money > 0) {
            from.money -= money;
            to.money += money;
            System.out.println("转账成功");
        } else {
            System.out.println("余额不足");
        }
    }

    static class Person {
        public Person(int money) {
            this.money = money;
        }

        public int money;
    }
}
