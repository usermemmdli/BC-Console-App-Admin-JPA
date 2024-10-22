package org.example;

import org.example.config.EntityManagerConfig;
import org.example.constants.Menu;
import org.example.util.MenuUtil;
import org.example.strategy.Strategy;

public class Main {
    public static void main(String[] args) {
        EntityManagerConfig.initEntityManager();
        while (true) {
            MenuUtil.printMenu();
            Menu menu = MenuUtil.chooseMenu();
            Strategy strategy = menu.getStrategy();
            strategy.operate();
        }
    }
}