package at.compus02.swd.ss2022.game.common;

public class ConsoleLogger implements Logger {
    private static ConsoleLogger instance;

    public static ConsoleLogger getInstance() {
        if (instance == null) {
            instance = new ConsoleLogger();
        }
        return instance;
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
