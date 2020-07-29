package demo.threadcoreknowledge;

import java.util.concurrent.*;


/**
  *  @Time:     2020/7/29  17:32
  *  @author：  Mr.tiantian
  *             线程池创建线程
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
         *                  workQueue      ：
         *                  threadFactory  ：
         *                  handler        ：
          */
        pool = new ThreadPoolExecutor(1, 20, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
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
