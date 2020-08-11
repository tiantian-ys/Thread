package java_concurrency_core.singleton;

/**
 * 描述：     饿汉式（静态常量）（可用）
 */
public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
      System.out.println("初始化");
    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

      public static void main(String[] args) {

         // Singleton1.getInstance();
         // Singleton1.getInstance();
      }
}
