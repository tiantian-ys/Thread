package demo.threadcoreknowledge;

import java.util.Date;
import java.util.LinkedList;

/**
 * @Time: 2020/8/3 19:00
 *
 * @author： Mr.tiantian 使用wait/notify 实现消费者,生产者
 *                       生产者,与消费者通过调用  wait：释放锁资源，notify：获取锁资源
 *                       生产判断容器是否到达阀值来判断 通过调用wait方法释放资源，通知消费者消费
 *                       消费者 判断容器释是否还有数据 来通知生产者 产生数据
 */
public class ProducerConsumerModel {

  public static void main(String[] args) {
    EventStorage eventStorage = new EventStorage();
    Thread thread = new Thread(new Producer(eventStorage), "Producer");
    Thread thread2 = new Thread(new Consumer(eventStorage), "Consumer");
      thread.start();
      thread2.start();
  }
}

/** 容器类 */
class EventStorage {

  /** 最大容量 */
  private int maxSize;

  /** 容器 */
  private LinkedList<Date> warehouse;

  /** 需要初始化容器大小 */
  public EventStorage() {
    maxSize = 10;
    warehouse = new LinkedList<>();
  }

  /** 容器取操作 */
  public synchronized void get() {

    while (warehouse.size() == 0) {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
      System.out.println("消费者执行消费" + warehouse.poll() + "，目前容器容量：" + warehouse.size());
      notify();
  }

  /** 容器存操作 */
  public synchronized void push() {

    // 超过容器最大限度
    if (warehouse.size() >= maxSize) {
      try {
             wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    warehouse.push(new Date());
    System.out.println("生产者执行添加操作，目前容器容量：" + warehouse.size());
    notify();
  }

}

/** 生产者 */
class Producer implements Runnable {

  private EventStorage eventStorage;

  public Producer(EventStorage eventStorage) {
    this.eventStorage = eventStorage;
  }

  @Override
  public void run() {
    for (int i = 0;i < 10000; i++){
        eventStorage.push();
    }
  }
}

/** 消费者 */
class Consumer implements Runnable {

  private EventStorage eventStorage;

  public Consumer(EventStorage eventStorage) {
    this.eventStorage = eventStorage;
  }

  @Override
  public void run() {
      for (int i = 0; i < 10000; i++) {
          eventStorage.get();
      }
      }

}
