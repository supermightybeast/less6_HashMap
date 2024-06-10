package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Commands {
    public String delimetr = "* * *";
    private PhoneBook phoneBook = new PhoneBook();
    private Console console = new Console();

    /**
     * @apiNote Запускает программу
     */
    public void init() {
        boolean isPower = true;

        while (isPower) {
            System.out.println(delimetr);
            char command = enterCommand();
            System.out.println(delimetr);

            switch (command) {
                case 'q':
                    isPower = false;
                    commandExit();
                    break;
                case 'l':
                    commandList();
                    break;
                case 'a':
                    commandAdd();
                    break;
                case 'r':
                    commandRemove();
                    break;
                case 'd':
                    commandDelete();
                    break;
                case 'f':
                    commandFind();
                    break;
                case 's':
                    commandShow();
                    break;
                default:
                    System.out.println("\nIncorrect command");
                    break;
            }
        }
    }

    /**
     * @apiNote Отображает окно команд
     * @return Возвращает введенную команду
     */
    private char enterCommand() {
        String[] listCommands = {
                "a - Add contact", "d - Delete number", "r - Remove contact",
                "f - Find contact", "s - Show contacts", "l - List contacts",
                "q - Exit"};
        System.out.println("List commands:");
        System.out.println(String.join("\n", listCommands));
        System.out.print("Enter command: ");

        return console.readCommand();
    }

    /**
     * @apiNote Выводит сообщение при завершении программы
     */
    private void commandExit() {
        System.out.println("Exit");
        System.out.println("-----------");
    }

    /**
     * @apiNote Отображает список контактов
     */
    private void commandList() {
        System.out.println("List contacts.");
        phoneBook.viewList();
        console.delayConsole();
    }

    /**
     * @apiNote Отображает меню добаления контакта
     * @return
     */
    private boolean commandAdd() {
        System.out.println("Add contact.");
        Map<String, Long> contact = console.readConsole(true);

        if (contact.isEmpty()){
            System.out.println("\nInput error");
            return false;
        }
        phoneBook.addContact(contact);
        System.out.println("Success!");
        return true;
    }

    /**
     * @apiNote Отображает меню удаления контакта
     */
    private void commandRemove() {
        System.out.println("Remove contact.");
        String name = console.readConsole();
        phoneBook.removeContact(name);
    }

    /**
     * @apiNote Отображает меню удаления номера у контакта
     * @return
     */
    private boolean commandDelete() {
        System.out.println("Delete number.");
        Map<String, Long> contact = console.readConsole(true);

        if (contact.isEmpty()){
            System.out.println("\nInput error");
            return false;
        }
        phoneBook.deleteNumber(contact);
        System.out.println("Success!");
        return true;
    }

    /**
     * @apiNote Отображает меню поиска контакта и выводит его номера
     */
    public void commandFind(){
        System.out.println("Find contact.");
        String name = console.readConsole();
        phoneBook.printNumbers(name);
        console.delayConsole();
    }

    /**
     * Выводит сортированный контакты
     */
    public void commandShow(){
        phoneBook.printSortedContacts();
        console.delayConsole();
    }
}