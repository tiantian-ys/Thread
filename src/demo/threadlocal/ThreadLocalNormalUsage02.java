package demo.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Time: 2020/8/13  15:14
 * @author： Mr.tiantian
 *                  使用线程打印时间 ->SimpleDateFormat
 *                  1: 使用new SimpleDateFormat   每次运行都会新建对象 , 不是最好的选项
 *                  2: 使用静态的 SimpleDateFormat 对象, 可能会出现线程安全问题
 *                  3:  在 2的基础上加锁 解决线程安全问题
 *                  4: 使用Threadlocal 线程私有,解决线程安全问题
 */
public class ThreadLocalNormalUsage02 {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void main(String[] args) throws InterruptedException {



        boolean flay = true;
        while(flay){

            HashSet<String> classSet = new HashSet<String>();
            for (int i = 0; i < 600; i++) {
                int finalI = i;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        String date = new ThreadLocalNormalUsage02().printDate(finalI);
                        //  System.out.println(date);
                        classSet.add(date);
                    }
                });
            }
            Thread.sleep(2000);
            System.out.println("最终的长度" + classSet.size());
            executorService.shutdown();
            if(classSet.size() != 600){
                flay =false;
                System.out.println("不等于 600 退出了");
            }


        }



    }

    /**
     *
     */
    public String printDate(int seconds) {
        Date date = new Date(1000 * seconds);
    //    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //      String s;
    //    synchronized (ThreadLocalNormalUsage02.class){
    //           s = dateFormat.format(date);
    //   }
            SimpleDateFormat dateFormat = ThreadLocalDateFormat.threadLocal.get();
        return dateFormat.format(date);
    }

}

/**
 * ThreadLocal  使用方法
 */
class ThreadLocalDateFormat{
    public static ThreadLocal<SimpleDateFormat>   threadLocal= new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            System.out.println(Thread.currentThread().getName()+"initialValue ->执行ThreadLocal  初始化函数");
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
}