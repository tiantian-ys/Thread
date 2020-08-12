package demo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
  *  @Time:     2020/8/12  15:10
  *  @author：  Mr.tiantian
  *             可能会出现内存溢出(OOM)问题
  *
  */
public class FixedThreadPoolOOM {

    /** 自动创建指定数量线程  */
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0 ;i<Integer.MAX_VALUE ; i++){
            executorService.submit(new Stask());
        }
    }


}
 class Stask implements  Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread.name："+Thread.currentThread().getName());
    }
}