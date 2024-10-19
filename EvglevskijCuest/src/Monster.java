
import java.util.Random;
import java.util.concurrent.TimeUnit;



    public class Monster extends Thread {
    private int sleepTime;
    private boolean awake;
    private Logger logger;
    private boolean running;

    public Monster(int sleepTime, Logger logger) {
        this.sleepTime = sleepTime;
        this.logger = logger;
        this.awake = false;
        this.running = true;
    }

    public boolean isAwake() {
        return awake;
    }

    public void stopMonster() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
                awake = true; // Монстр просыпается
                logger.logEvent("Монстр проснулся!");
                awake = false; // Возвращается обратно в сон
            } catch (InterruptedException e) {
                logger.logEvent("Монстр прерван.");
            }
        }
    }
}


