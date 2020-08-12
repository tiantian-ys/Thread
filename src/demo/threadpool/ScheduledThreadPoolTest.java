package demo.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
  *  @Time:     2020/8/12  15:31
  *  @author：  Mr.tiantian
 *      scheduleAtFixedRate：
 *          new Stask()：线程对象
 *          1：启动等待时间，
 *          3：每次运行延迟时间，
 *          TimeUnit.SECONDS： 单位进制
  */
public class ScheduledThreadPoolTest {



    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Stask(), 1, 3, TimeUnit.SECONDS);
    }

}
