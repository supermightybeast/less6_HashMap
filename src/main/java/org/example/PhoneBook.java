package org.example;

import java.util.*;

public class PhoneBook implements PBook {
    private static Map<String, List<Long>> phoneBook = new HashMap<>();

    /**
     * @apiNote Проверяет существование контакта
     * @param name Имя контакта
     * @return
     */
    public boolean isContact(String name){
        return phoneBook.containsKey(name);
    }

    /**
     * @apiNote Добавляет контакт в телефонную книгу
     * @param name Имя контакта
     * @param number Номер телефона контакта
     */
    @Override
    public void addContact(String name, long number) {
        List<Long> list = new ArrayList<>();
        if (isContact(name)) {
            list = phoneBook.get(name);
        }
        list.add(number);
        phoneBook.put(name, list);
    }

    /**
     * @apiNote Добавляет контакт в телефонную книгу
     * @param contact
     */
    public void addContact(Map<String, Long> contact) {
        for (String name : contact.keySet()){
            addContact(name, contact.get(name));
        }
    }

    /**
     * @apiNote Удаляет контакт
     * @param name Имя контакта
     */
    @Override
    public void removeContact(String name) {
        if (isContact(name)) {
            phoneBook.remove(name);
        }
    }

    /**
     * @apiNote Удаляет номер телефона контакта
     * @param name Имя контакта
     * @param number Номер телефона контакта
     */
    @Override
    public void deleteNumber(String name, long number) {
        if (isNumber(name, number)) {
            var list = phoneBook.get(name);
            list.remove(list.indexOf(number));
            phoneBook.put(name, list);
        }
    }

    /**
     * @apiNote Удаляет номер телефона контакта
     * @param contact
     */
    public void deleteNumber(Map<String, Long> contact) {
        for (String name : contact.keySet()){
            deleteNumber(name, contact.get(name));
        }
    }

    /**
     * @apiNote Проверяет существование номера телефона у контакта
     * @param name Имя контакта
     * @param number Номер телефона контакта
     * @return
     */
    @Override
    public boolean isNumber(String name, long number) {
        if (isContact(name)){
            return phoneBook.get(name).contains(number);
        }
        return false;
    }

    /**
     * @apiNote Возвращает номера телефонов контакта
     * @param name
     * @return
     */
    public List<Long> getNumbers(String name){
        if (isContact(name)){
            return phoneBook.get(name);
        }
        return new ArrayList<>();
    }

    /**
     * @apiNote Выводит на печать номера телефонов контакта
     * @param name
     */
    public void printNumbers(String name){
        List<Long> numbers = getNumbers(name);
        if (numbers.isEmpty()) {
            System.out.println("Empty");
        } else {
            System.out.println(getNumbers(name).toString());
        }
    }

    /**
     * @apiNote Выводит на печать все контакты
     */
    public void viewList(){
        for (String key : phoneBook.keySet()){
            System.out.printf("%s: %s\n", key, phoneBook.get(key).toString());
        }
        System.out.println();
    }

    /**
     * @apiNote Сортирует контакты по наибольшему количеству номеров тедефона
     * @return Отсортированный список контактов
     */
    private List<Map<String, List<Long>>> sortContacts(){
        // 1. Сформировать список длины
        Map<Integer, List<String>> listSizeNumbers = countPhoneNumbers();

        // 2. Получить ключи в виде списка
        List<Integer> keys = new ArrayList<>(listSizeNumbers.keySet());

        // 3. Сформировать сортированный список

        List<Map<String, List<Long>>> sorted = new ArrayList<>();
        for (int i = keys.size()-1; i >= 0 ; i--) {

            int quantity = keys.get(i);
            List<String> namesList = listSizeNumbers.get(quantity);
            for (String name : namesList){
                Map<String, List<Long>> contact = new HashMap<>();
                List<Long> numbers = phoneBook.get(name);
                contact.put(name, numbers);
                sorted.add(contact);
            }
        }
        return sorted;
    }

    /**
     * @apiNote Создает список с количеством номеров телефона у контактов
     * @return Список с количеством номеров
     */
    private Map<Integer, List<String>> countPhoneNumbers(){
        Map<Integer, List<String>> list = new HashMap<>();

        for (String name : phoneBook.keySet()){
            int contactSize = phoneBook.get(name).size();
            List<String> namesList = new ArrayList<>();
            if (list.containsKey(contactSize)){
                namesList = list.get(contactSize);
            }
            namesList.add(name);
            list.put(contactSize, namesList);
        }
        return list;
    }

    /**
     * @apiNote Выводит на печать отсортированный список контактов
     * по наибольшему количеству номеров тедефона
     */
    public void printSortedContacts(){
        List<Map<String, List<Long>>> contacts = sortContacts();
        for (int i = 0; i < contacts.size(); i++) {
            String name = (new ArrayList<>(contacts.get(i).keySet())).get(0);
            System.out.printf("%s: %s\n", name, contacts.get(i).get(name).toString());
        }
    }
}