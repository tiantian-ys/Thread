package created_concurrency_core.sixstates;

/**
 * 描述：     展示Blocked, Waiting, TimedWaiting
 *
 *              TimedWaiting：计时等待 设置了等待时间
 *              Waiting：     无期限等待，（死锁）
 *              Blocked：     线程锁被占用，等待其他程序释放锁资源
 */
public class BlockedWaitingTimedWaiting implements Runnable{
    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出Timed_Waiting状态，因为正在执行Thread.sleep(1000);
        System.out.println(thread1.getState());
        //打印出BLOCKED状态，因为thread2想拿得到sync()的锁却拿不到
        System.out.println(thread2.getState());
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出WAITING状态，因为执行了wait()
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    // synchronized   Blocked：     线程锁被占用，等待其他程序释放锁资源
    private synchronized void syn() {
        try {
            //  TimedWaiting：计时等待 设置了等待时间
            Thread.sleep(1000);
             // Waiting：     无期限等待，（死锁）
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
