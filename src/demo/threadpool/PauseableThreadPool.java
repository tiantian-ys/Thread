package demo.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
  *  @Time:     2020/8/12  16:32
  *  @author：  Mr.tiantian
  *             编写线程池钩子函数
  */
public class PauseableThreadPool extends ThreadPoolExecutor {
    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    private final ReentrantLock lock = new ReentrantLock();
    private Condition unpaused = lock.newCondition();
    private boolean isPaused;
    /**
     * 执行之前
     * @param t
     * @param r
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r){
        super.beforeExecute(t, r);
        lock.lock();
        try{
            while (isPaused){
                unpaused.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    /**
     *  开始
     */
    public void start(){
        lock.lock();
        try{
            isPaused = false;
            unpaused.signalAll();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 停止
     */
    public void stop(){
        lock.lock();
        try {
            isPaused = true;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("执行了");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

     //   PauseableThreadPool pauseableThreadPool =new PauseableThreadPool(10,20,10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        PauseableThreadPool pauseableThreadPool = new PauseableThreadPool(10, 20, 10l,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for ( int  i = 0; i< 10000; i++){
            pauseableThreadPool.execute(thread);
        }
        Thread.sleep(800);
        pauseableThreadPool.stop();
        System.out.println("线程停止了");
        Thread.sleep(1500);
        pauseableThreadPool.start();
        System.out.println("线程又开始了");

    }


}
