package demo.deadlock;

/**
 * @Time: 2020/8/11 9:55
 *
 * @author： Mr.tiantian 必然死锁
 */
public class Mustdeadlock implements Runnable {

  int flag = 0;

  static Object objectA = new Object();
  static Object objectB = new Object();

  public static void main(String[] args) {
    Mustdeadlock mustdeadlockA = new Mustdeadlock();

    mustdeadlockA.flag = 0;

    Mustdeadlock mustdeadlockB = new Mustdeadlock();
    mustdeadlockB.flag = 1;

    Thread threadA = new Thread(mustdeadlockA);
    Thread threadB = new Thread(mustdeadlockB);

    threadA.start();
    threadB.start();
  }

  @Override
  public void run() {
    int aToHash = System.identityHashCode(objectA);
    int bToHash = System.identityHashCode(objectB);
    System.out.println("aToHash:"+aToHash  + "bToHash" +bToHash);

    System.out.println("flag = " + flag);
/*    if (flag == 0) {
      synchronized (objectA) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (objectB) {
          System.out.println("线程一进入,获取到了");
        }
      }
    }
    if (flag == 1) {
      synchronized (objectB) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (objectA) {
          System.out.println("线程二进入,获取到了");
        }
      }
    }*/
  }
}
