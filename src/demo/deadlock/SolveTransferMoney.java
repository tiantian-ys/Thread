package demo.deadlock;

/**
 * @Time: 2020/8/11 10:00
 *
 * @author： Mr.tiantian 模拟同时占用对方锁,引发的死锁
 *     <p>解决方案一: hascode 有顺序 获取锁
 */
public class SolveTransferMoney implements Runnable {
  static Person person1 = new Person(500);

  static Person person2 = new Person(500);

  static Object lock = new Object();
  int flag = 0;

  public static void main(String[] args) throws InterruptedException {
    /** 模拟两个用户 */
    SolveTransferMoney transferMoney1 = new SolveTransferMoney();
    SolveTransferMoney transferMoney2 = new SolveTransferMoney();

    transferMoney1.flag = 0;
    transferMoney2.flag = 1;

    Thread thread1 = new Thread(transferMoney1);
    Thread thread2 = new Thread(transferMoney2);

    thread1.start();
    thread2.start();
    // 使用join 等待主线程静态方法数据加载完毕
    thread1.join();
    thread2.join();

    // 使用join 等待主线程静态方法数据加载完毕

    System.out.println("用户一剩余金额:" + person1.money);
    System.out.println("用户二剩余金额:" + person2.money);
  }

  @Override
  public void run() {

    int aCode = System.identityHashCode(person1);
    int bCode = System.identityHashCode(person2);
    if (aCode > bCode) {
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
    } else if (aCode < bCode) {
      synchronized (person2) {
        try {
          Thread.sleep(200);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (person1) {
          transferMoney(person1, person2, 200);
        }
      }
    } else {

      synchronized (lock) {
        try {
          Thread.sleep(200);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (person2) {
          synchronized (person1) {
            transferMoney(person1, person2, 200);
          }
        }
      }
    }
  }
  /**
   * @param from 转出
   * @param to 转入
   * @param money 金额
   */
  public static void transferMoney(Person from, Person to, int money) {
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
