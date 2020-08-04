package demo.threadcoreknowledge;

/**
 * @Time: 2020/8/4 15:49
 *
 * @author： Mr.tiantian 使用两个线程交替打印 奇偶数
 */
public class WaitNotifyPrintOddEvenSyn {

  private static int count;
  private static Object object = new Object();

  public static void main(String[] args) {

    // 打印期数
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                while (count < 100) {
                  synchronized (object) {
                    if ((count & 1) == 0) {
                      System.out.println(Thread.currentThread().getName() + "打印：" + count++);
                    }
                  }
                }
              }
            },
            "偶然线程")
        .start();

    new Thread(
            new Runnable() {
              @Override
              public void run() {
                while (count < 100) {
                  synchronized (object) {
                    if ((count & 1) == 1) {
                      System.out.println(Thread.currentThread().getName() + "打印：" + count++);
                    }
                  }
                }
              }
            },
            "奇数线程")
        .start();
  }
}
