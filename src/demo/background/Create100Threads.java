package demo.background;

public class Create100Threads {
  public static void main(String[] args) {
    for (int i =0 ; i< 2000; i++){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
  }
}
