package demo.threadpool;

import java.util.concurrent.*;


/**
  *  @Time:     2020/7/29  17:32
  *  @author：  Mr.tiantian
  *             线程池创建线程
 *
 *             参考网站：https://www.cnblogs.com/dafanjoy/p/9729358.html
  */
public class ThreadPoolExecutor_create implements Runnable{


    private static ExecutorService pool;
    
    @Override
    public void run() {
        System.out.println("线程名："+Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        //maximumPoolSize设置为2 ，拒绝策略为AbortPolic策略，直接抛出异常

        /**
         *  @Time:     2020/7/29  17:33
         *  @author：  Mr.tiantian
         *             参数说明：
         *                  corePoolSize   ： 线程池中核心线程数的最大值
         *                  maximumPoolSize： 线程池中能拥有最多线程数
         *                  keepAliveTime  ： 当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
         *                  unit           ： 表示keepAliveTime的单位
         *                  workQueue      ： 任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种
         *                                     直接提交队列  ： new SynchronousQueue<Runnable>()，
         *                                     有界的任务队列： new ArrayBlockingQueue<Runnable>(10)，
         *                                     无界的任务队列： new LinkedBlockingQueue<Runnable>()，
         *                                     优先任务队列  ： new PriorityBlockingQueue<Runnable>()，
         *                  threadFactory  ： 线程工厂，用于创建线程，一般用默认即可
         *                  handler        ： 拒绝策略；当任务太多来不及处理时，如何拒绝任务
         *                                      AbortPolicy策略：该策略会直接抛出异常，阻止系统正常工作；
         *                                      CallerRunsPolicy策略：如果线程池的线程数量达到上限，该策略会把任务队列中的任务放在调用者线程当中运行；
         *                                      DiscardOledestPolicy策略：该策略会丢弃任务队列中最老的一个任务，也就是当前任务队列中最先被添加进去的，马上要被执行的那个任务，并尝试再次提交；
         *                                      DiscardPolicy策略：该策略会默默丢弃无法处理的任务，不予任何处理。当然使用此策略，业务场景中需允许任务的丢失；
          */
        pool = new ThreadPoolExecutor(
                1,
                20,
                1000,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for(int i = 0 ; i < 2 ;  i ++ ) {
            pool.execute(new ThreadPoolExecutor_create());
        }


/*        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();*/

    }

}
