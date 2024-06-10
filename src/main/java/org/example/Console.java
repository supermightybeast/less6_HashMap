package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console {
    private Scanner scanner = new Scanner(System.in);

    /**
     * @apiNote Читает имя контакта введенное через консоль
     * @return Возвращает имя контакта
     */
    public String readConsole(){
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }

    /**
     * @apiNote Читает контакт введенный через консоль
     * @param readAll Перезагрузка метода
     * @return Возвращает контакт
     */
    public Map<String, Long> readConsole(boolean readAll){
        Map<String, Long> contact = new HashMap<>();
        if (readAll) {
            String name = readConsole();
            System.out.print("Enter number: ");
            if (scanner.hasNextLong()) {
                contact.put(name, scanner.nextLong());
            }
            scanner.nextLine();
        }
        return contact;
    }

    /**
     * @apiNote Читает команду введенную через консоль
     * @return Возвращает команду
     */
    public char readCommand(){
        char command = '-';
        String str = scanner.nextLine();
        if (!str.isEmpty()) {
            command = str.charAt(0);
        }
        return command;
    }

    /**
     * @apiNote Задержка вывода в конмоль
     */
    public void delayConsole(){
        System.out.println("Press enter to continue.");
        scanner.nextLine();
    }
}