package org.example.util;

import org.example.constants.Menu;

import java.util.Scanner;

public class MenuUtil {
    public static void printMenu() {
        Menu[] menus = Menu.values();
        System.out.println("----------------------------------------");
        for (Menu menu : menus) {
            System.out.println(menu.getIndex() + "." + menu.getDescription());
        }
        System.out.println("----------------------------------------");
    }

    public static Menu chooseMenu() {
        while (true) {
            try {
                System.out.println("Please choose one: ");
                Scanner scanner = new Scanner(System.in);
                int menuIndex = scanner.nextInt();
                return Menu.getMenuByIndex(menuIndex);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice! Get valid choose!");
                MenuUtil.printMenu();
            }
        }
    }
}
