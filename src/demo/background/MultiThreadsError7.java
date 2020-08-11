package demo.background;

public class MultiThreadsError7 {

  int count;

  private EventListener listener;

  private MultiThreadsError7(MySource source) {

    listener =new EventListener() {

      @Override
      public void onEvent(Event e) {
        System.out.println("\n我得到的数字是" + count);
      }

    };
    for (int i = 0; i < 10000; i++) {
      System.out.print(i);
    }
    count = 100;
  }


  public static MultiThreadsError7 getInstance(MySource mySource){
    MultiThreadsError7 multiThreadsError5 = new MultiThreadsError7(mySource);
     mySource.registerListener(multiThreadsError5.listener);
    return multiThreadsError5;
  }


  public static void main(String[] args) {

    MySource mySource = new MySource();
    new Thread(
        new Runnable() {
          @Override
          public void run() {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
              mySource.eventCome(new Event() {
              });;
          }
        }).start();
 //   MultiThreadsError7 multiThreadsError5 = new MultiThreadsError7(mySource);
    MultiThreadsError7.getInstance(mySource);

  }


  static class MySource {

    private EventListener listener;

    void registerListener(EventListener eventListener) {
      this.listener = eventListener;

    }

    void eventCome(Event e) {
      if (listener != null) {
        listener.onEvent(e);
      } else {
        System.out.println("\n还未初始化完毕");
      }
    }
  }

  interface EventListener {
  
    void onEvent(Event e);
  }

  interface Event {}
}
