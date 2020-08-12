package demo.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Time: 2020/8/12  15:25
 * @author： Mr.tiantian
 * newCachedThreadPool：无界线程池，可无限创建线程
 * 线程太多，也会导致内存溢出问题。
 */
public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Stask());
        }
    }
}
