package demo.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
  *  @Time:     2020/8/12  15:24
  *  @author：  Mr.tiantian
  *             newSingleThreadExecutor:线程池,只创建一个线程
  */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Stask());
        }
    }
}
