package ru.yandex.practicum;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    public static void main(String[] args) {
        String filename = "words_ru.txt";
        WordleDictionaryLoader loader = new WordleDictionaryLoader();

        List<String> words;
        try {
            words = loader.load(filename);

            if (words.isEmpty()) {
                System.out.println("Словарь пуст!");
                return;
            }
            System.out.println("Загружено слов: " + words.size());
        } catch (IOException e) {
            System.out.println("Произошла ошибка при загрузке словаря: " + e.getMessage());
            return;
        }

        WordleDictionary dictionary = new WordleDictionary(words);

        WordleGame game = new WordleGame(dictionary);

        System.out.println("Игра началась! У тебя есть 6 попыток");

        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        int maxAttempts = 6;
        boolean isWon = false;

        while (attempts < maxAttempts) {
            System.out.println("Твой ход (попытка " + (attempts + 1) + "/" + maxAttempts + "): ");
            String input = scanner.nextLine().trim().toLowerCase();
            try {
                String result = game.coincidence(input);
                System.out.println("Результат: " + result);

                if (result.equals("+++++")) {
                    isWon = true;
                    break;
                }
                attempts++;
            } catch (UnknownWordException | InvalidWordLengthException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }


        }
        if (isWon) {
            System.out.println("Ты угадал слово!");
        } else {
            System.out.println("Попытки закончились. Ты не угадал слово");
        }
        System.out.println("Загаданное слово: " + game.getAnswer());
    }

}
