package demo.background;

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Time: 2020/8/6 16:30
 *
 * @author： Mr.tiantian 演示多线程带来的数据问题
 */
public class MultiThreadError implements Runnable {

  static Object object = new Object();

  static MultiThreadError multiThreadError = new MultiThreadError();
  int index = 0;
  static AtomicInteger realIndex = new AtomicInteger();
  static AtomicInteger wrongCount = new AtomicInteger();

  static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
  static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
  // static Vector syn = new Vector(370000);

  final boolean[] syn = new boolean[10000000];

  @Override
  public void run() {
    syn[0] = true;
    for (int i = 0; i < 10000; i++) {
      try {
        cyclicBarrier2.reset();
        cyclicBarrier1.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      index++;

      realIndex.incrementAndGet();

      synchronized (multiThreadError) {
        if (syn[index] && syn[index - 1]) {
          System.out.println("发生错误的数字：" + index);
          wrongCount.incrementAndGet();
        }
        syn[index] = true;
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(multiThreadError);

    Thread thread1 = new Thread(multiThreadError);
    Thread thread2 = new Thread(multiThreadError);
    thread.start();
    thread1.start();
    //   thread2.start();
    thread.join();
    thread1.join();
    // thread2.join();
    System.out.println("实际添加数据：" + multiThreadError.index);
    System.out.println("实际执行数据：" + MultiThreadError.realIndex);
    System.out.println("添加失败数据：" + MultiThreadError.wrongCount);
  }
}
