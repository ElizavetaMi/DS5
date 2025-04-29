package ru.netology.javaqa.javaqamwn.services;

public class VacationCalculator {

    public int calculate(int income, int expenses, int threshold) {
        int count = 0; // Счётчик месяцев отдыха
        int money = 0; // Количество денег на счету

        // Эмуляция 12 месяцев
        for (int month = 0; month < 12; month++) {
            if (money >= threshold) { // Если на счету достаточно средств для отдыха
                // Тратим обязательные расходы
                money -= expenses;
                // Тратим оставшиеся средства на отдых (делим на 3)
                money -= (money / 3);
                count++; // Увеличиваем счётчик месяцев отдыха
            } else {
                // Если денег не хватает для отдыха, работаем
                money += income;  // Заработок
                money -= expenses; // Обязательные расходы
            }
        }
        return count; // Возвращаем количество месяцев отдыха
    }
}