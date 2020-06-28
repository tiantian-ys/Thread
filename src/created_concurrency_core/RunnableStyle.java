package created_concurrency_core;


/**
  *  @Time:     2020/6/23  22:49
  *  @author：  Mr.tiantian
  *             创建线程 两大方式
 *              实现 Runnable  接口  （最佳方式）
 *
 *              继承 Thraed 类 重写 run 方法
 *
  */
public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("用Runable 方法实现线程");
    }


    public static void main(String[] args) {


        Thread thread =  new Thread(new RunnableStyle());
        thread.start();
    }



}
