package demo.stop;
/**
  *  @Time:     2020/7/29  14:48
  *  @author：  Mr.tiantian
  *             正确的停止线程：interrupt
 *
 *
  */
public class RightWayStopThreadWithSleep implements  Runnable {

        @Override
        public void run() {

              int i = 0;

              try {
                  while (!Thread.currentThread().isInterrupted() && i < 10000000){
                      System.out.println("线程运行中 i->"+i);
                      i++;
                  }
                  Thread.sleep(1000);
              }catch (InterruptedException e){
                    e.printStackTrace();
              }
               System.out.println("当前线程执行结束了，打印isInterrupted（）"+Thread.currentThread().isInterrupted());
        }

        public static void main(String[] args) throws InterruptedException {
                Thread thread = new Thread(new RightWayStopThreadWithSleep(),"线程别名");
                thread.start();
                //运行两秒通知停止线程
                Thread.sleep(300);
                thread.interrupt();

        }
}
