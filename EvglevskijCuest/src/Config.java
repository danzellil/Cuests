import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Config {
    private int rooms;
    private int monsterSleep; // В секундах
    private int lives;

    public Config(int rooms, int monsterSleep, int lives) {
        this.rooms = rooms;
        this.monsterSleep = monsterSleep;
        this.lives = lives;
    }

    public static Config loadConfig() {
        int rooms = 10;
        int monsterSleep = 60;
        int lives = 3;

        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    int value = Integer.parseInt(parts[1].trim());
                    if (key.equals("rooms") && (value == 10 || value == 20 || value == 30)) {
                        rooms = value;
                    } else if (key.equals("monster_sleep") && (value == 30 || value == 45 || value == 60)) {
                        monsterSleep = value;
                    } else if (key.equals("lives") && (value == 1 || value == 2 || value == 3)) {
                        lives = value;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Ошибка при загрузке конфигурации: " + e.getMessage());
        }

        return new Config(rooms, monsterSleep, lives);
    }

    public int getLives() {
        return lives;
    }

    public int getMonsterSleep() {
        return monsterSleep;
    }
}
