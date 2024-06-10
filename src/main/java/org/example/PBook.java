package org.example;

public interface PBook {
    /**
     * @apiNote Добавляет контакт в телефонную книгу
     * @param name Имя контакта
     * @param number Номер телефона контакта
     */
    void addContact(String name, long number);

    /**
     * @apiNote Удаляет контакт из телефонной книги
     * @param name Имя контакта
     */
    void removeContact(String name);

    /**
     * @apiNote Удаляет номер телефона контакта
     * @param name Имя контакта
     * @param number Номер телефона контакта
     */
    void deleteNumber(String name, long number);

    /**
     * @apiNote Проверяет существует ли контакт
     * @param name Имя контакта
     * @return
     */
    boolean isContact(String name);

    /**
     * @apiNote Проверяет существует ли номер телефона у контакта
     * @param name Имя контакта
     * @param number Номер телефона контакта
     * @return
     */
    boolean isNumber(String name, long number);
}