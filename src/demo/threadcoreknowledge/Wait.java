package demo.threadcoreknowledge;

/**
  *  @Time:     2020/7/29  15:22
  *  @author：  Mr.tiantian
  *         
  */
public class Wait {


    public static Object object = new Object();

    static class Thread1 implements  Runnable{
    @Override
    public void run() {
            synchronized (object){

                System.out.println("线程 "+Thread.currentThread().getName()+"开始执行");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程 "+Thread.currentThread().getName()+"捕获到了锁");
            }
    }


    }
    static class Thread2 implements  Runnable{

        @Override
        public void run() {
            synchronized (object){
                object.notify();
                System.out.println("线程 "+Thread.currentThread().getName()+"调用了notify()");

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        /**
          *  @Time:     2020/7/29  15:52
          *  @author：  Mr.tiantian
          *             执行流程
          *             线程一：执行start  ，wait() 无限期等待并释放锁资源，线程二获取锁资源，调用notify方法唤醒线程一
          */

        Thread thread_syn1 = new Thread(new Thread1(),"线程一");
        Thread thread_syn2 = new Thread(new Thread2(),"线程二");

        thread_syn1.start();
        Thread.sleep(200);
        thread_syn2.start();
        Thread.sleep(200);
        thread_syn2.start();
    }


}
