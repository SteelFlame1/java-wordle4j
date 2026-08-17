package ru.yandex.practicum;

import java.util.HashMap;
import java.util.Map;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {


    private String answer;

    private int steps;

    private WordleDictionary dictionary;

    public String getAnswer() {
        return answer;
    }

    public WordleGame(WordleDictionary dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("Словарь не может быть null");
        }
        this.dictionary = dictionary;
        this.answer = dictionary.getRandomWord();
        this.steps = 0;
    }

    public String coincidence(String guess) {
        if (guess == null || guess.length() != 5) {
            throw new IllegalArgumentException("Слово должно состоять из 5 букв!");
        }
        if (!dictionary.isValidWord(guess)) {
            throw new IllegalArgumentException("Такого слова нет в словаре");
        }
        steps++;

        char[] result = new char[5];

        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                result[i] = '+';
            }
        }


        Map<Character, Integer> remainingLetters = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            if (result[i] != '+') {
                char letter = answer.charAt(i);
                remainingLetters.put(letter, remainingLetters.getOrDefault(letter, 0) + 1);
            }
        }


        for (int i = 0; i < 5; i++) {
            if (result[i] == '+') {
                continue;
            }

            char currentGuessLetter = guess.charAt(i);

            int count = remainingLetters.getOrDefault(currentGuessLetter, 0);

            if (count > 0) {
                result[i] = '^';

                remainingLetters.put(currentGuessLetter, count - 1);
            } else {

                result[i] = '-';
            }
        }

        return new String(result);
    }
}
