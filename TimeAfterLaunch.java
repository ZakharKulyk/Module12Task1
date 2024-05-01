import java.util.concurrent.atomic.AtomicInteger;

public class TimeAfterLaunch {
    private static volatile int sec = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sec++;
                System.out.println(sec);
            }
        });
        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (sec % 5 == 0 && sec != 0) {
                    System.out.println("5 seconds have passed");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


        thread.start();
        thread1.setDaemon(true);
        thread1.start();


    }
}
