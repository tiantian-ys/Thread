package demo.threadcoreknowledge;

import java.util.Date;
import java.util.LinkedList;

/**
 * @Time: 2020/8/3 19:00
 *
 * @author： Mr.tiantian
 *     <p>使用wait/notify 实现消费者,生产者
 */
public class ProducerConsumerModel {}

/** 容器类 */
class EventStorage {

  /** 最大容量 */
  private int maxSize = 10;

  /** 容器 */
  private LinkedList<Date> warehouse;

  /** 容器取操作 */
  public synchronized void get() {

      if(warehouse.size() == 0){
                notify();
      }

      System.out.println("消费者执行消费"+warehouse.poll()+"，目前容器容量：" + warehouse.size());

  }


  /** 容器存操作 */
  public synchronized void push() {

    //超过容器最大限度
    if (warehouse.size() >= maxSize) {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    warehouse.push(new Date());
    System.out.println("生产者执行添加操作，目前容器容量：" + warehouse.size());
  }
}

/** 生产者 */
class Producer {}

/** 消费者 */
class Consumer {}
