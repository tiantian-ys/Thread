package demo.background;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
  *  @Time:     2020/8/6  16:30
  *  @author：  Mr.tiantian
  *             演示多线程带来的数据问题
  */
public class MultiThreadError implements Runnable {

  static Object object =new Object();

  static MultiThreadError multiThreadError = new MultiThreadError();
   int index = 0;
  static AtomicInteger realIndex = new AtomicInteger();
  static AtomicInteger wrongCount = new AtomicInteger();

  static Vector syn = new Vector(370000);


  @Override
  public  void run() {
    for (int i = 1; i < 10000; i++) {
          index++;

          realIndex.incrementAndGet();

          synchronized (multiThreadError){

              if(syn.indexOf(index) != -1 ){
                System.out.println("发生错误的数字："+index);
                 wrongCount.incrementAndGet();
              }
              syn.add(i);


          }
    }

  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(multiThreadError);

    Thread thread1 = new Thread(multiThreadError);

    thread.start();
    thread1.start();

    thread.join();
    thread1.join();
    System.out.println("实际添加数据："+multiThreadError.index);
    System.out.println("实际执行数据："+MultiThreadError.realIndex);
    System.out.println("添加失败数据："+MultiThreadError.wrongCount);
  }
}
