package demo.threadcoreknowledge;

public class WaitNotifyPrintOddEveWait {

  private static int count = 0;

  private static Object object = new Object();

  public static void main(String[] args) {
    new Thread(new print(), "偶").start();
    new Thread(new print(), "奇").start();
  }

  static class print implements Runnable {

    @Override
    public void run() {

      while (count <= 100) {
        synchronized (object) {
          System.out.println(Thread.currentThread().getName() + "打印：" + count++);
          object.notify();
          if (count <= 100) {
            try {
              object.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }


    }
  }
}
