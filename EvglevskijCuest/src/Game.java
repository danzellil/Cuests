import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    private int lives;
    private Monster monster;
    private Logger logger;

    public Game() {
        Config config = Config.loadConfig();
        this.lives = config.getLives();
        this.logger = new Logger();
        this.monster = new Monster(config.getMonsterSleep(), logger);
    }

    public void start() {
        monster.start(); // Запуск потока монстра

        // Основной игровой цикл
        while (lives > 0) {
            logger.logEvent("Игрок начинает ход, оставшиеся жизни: " + lives);
            System.out.println("Вы просыпаетесь в тёмном подвале.");
            System.out.println("Ваши действия:");
            System.out.println("1 - Обыскать комод");
            System.out.println("2 - Осмотреть вещи");
            System.out.println("3 - Осмотреть подвал на наличие выхода");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    logger.logEvent("Игрок обыскал комод.");

                    break;
                case 2:
                    logger.logEvent("Игрок осмотрел вещи.");

                    break;
                case 3:
                    logger.logEvent("Игрок осмотрел подвал.");

                    break;
                default:
                    System.out.println("Некорректный выбор.");
                    break;
            }


            if (monster.isAwake()) {
                lives--;
                logger.logEvent("Игрок встретил монстра! Жизней осталось: " + lives);
            }

            if (lives <= 0) {
                System.out.println("Вы погибли. Игра окончена.");
                logger.logEvent("Игрок погиб.");
            }
        }

        monster.stopMonster();
    }
}
