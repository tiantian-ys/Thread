package demo.threadcoreknowledge;

public class WaitNotifyAll implements Runnable {

    public static Object object =new Object();

    @Override
    public void run() {

            synchronized (object){
                System.out.println("线程 "+Thread.currentThread().getName()+"开始执行");

                try {
                    System.out.println("线程 "+Thread.currentThread().getName()+"进入休眠，释放锁资源");

                    object.wait();
                    System.out.println("线程 "+Thread.currentThread().getName()+"重新获取锁资源");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
    static class notify implements  Runnable{

        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程："+Thread.currentThread().getName()+"开始执行");
                object.notifyAll();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {


        Thread thread_syn1 = new Thread(new WaitNotifyAll(),"线程一");
        Thread thread_syn2 = new Thread(new WaitNotifyAll(),"线程二");

        Thread thread_syn3 = new Thread(new notify(),"释放线程");
        thread_syn1.start();
        Thread.sleep(200);
        thread_syn2.start();
        Thread.sleep(200);
        thread_syn3.start();
    }
}
