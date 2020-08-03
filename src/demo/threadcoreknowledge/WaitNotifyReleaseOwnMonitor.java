package demo.threadcoreknowledge;

/**
 * @Time: 2020/7/30 12:05
 *
 * @author： Mr.tiantian   wait：只释放当前锁
 *
 *
 */
public class WaitNotifyReleaseOwnMonitor {

  public static Object objectA = new Object();

  public static Object objectB = new Object();

  public static void main(String[] args) throws InterruptedException {

    Thread thread1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                synchronized (objectA) {
                  System.out.println("objectA：获取锁资源");
                    synchronized (objectB) {
                        System.out.println("objectB：获取锁资源");
                        try {
                            System.out.println("objectA：进入等待状态");
                            objectA.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
              }
            });

    Thread thread2 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                synchronized (objectA) {
                  System.out.println("获取objectA");
                    synchronized (objectB) {
                        System.out.println("获取objectB");
                    }
                }

              }
            });

        thread1.start();
      Thread.sleep(1000);
        thread2.start();
  }
}

