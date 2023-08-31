package ua.javarush.encoder;

import ua.javarush.encoder.interaction.CLI;
import ua.javarush.encoder.setting.Config;


public class Application {

    public static void main(String[] args) {

        Config config = new Config();

        CLI cli = new CLI();
        cli.StartApp(config, args);
        cli.StartWorkingWithFile(config);
    }
}