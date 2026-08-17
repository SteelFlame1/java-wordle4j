package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {
    @Test
    public void testIsValidWord_ReturnsTrueForExistingWord() {

        List<String> words = Arrays.asList("школа", "арбуз", "время");
        WordleDictionary dictionary = new WordleDictionary(words);

        boolean isValidLowercase1 = dictionary.isValidWord("арбуз");
        boolean isValidUppercase1 = dictionary.isValidWord("АРБУЗ");

        boolean isValidСase = dictionary.isValidWord("шишка");

        assertTrue(isValidLowercase1, "Слово арбуз должно быть найдено");
        assertTrue(isValidUppercase1, "Слово АРБУЗ должно быть найдено благодаря приведению к нижнему регистру");

        assertFalse(isValidСase, "Слово шишка не должно быть найдено");
    }

    @Test
    public void testGetRandomWord_ReturnWordIsDictionary() {
        List<String> words = Arrays.asList("школа", "арбуз", "время");
        WordleDictionary dictionary = new WordleDictionary(words);

        Set<String> allowedWords = new HashSet<>(words);

        int iterations = 100;
        for (int i = 0; i < iterations; i++) {
            String randomWord = dictionary.getRandomWord();

            assertNotNull(randomWord, "getRandomWord не должен возвращать null");

            assertTrue(allowedWords.contains(randomWord), "Выпало слово '" + randomWord + "', которого нет в словаре! Итерация: " + i);
        }

    }

    @Test
    public void testCoincidence_ReturnsAllPlus_WhenWordIsGuessed() {
        List<String> words = Arrays.asList("школа", "арбуз", "время", "кошка");
        WordleDictionary dictionary = new WordleDictionary(words);

        WordleGame game = new WordleGame(dictionary);

        String answer = game.getAnswer();

        assertNotNull(answer);
        assertEquals(5, answer.length(), "Загаданное слово должно быть длиной 5 букв");

        String result = game.coincidence(answer);

        assertEquals("+++++", result, "При полном совпадении результат должен быть '+++++'");

        assertEquals(5, result.length(), "Результат сравнения всегда должен быть длиной 5 символов");
    }

    @Test
    public void testCoincidence_ReturnsAllMinus_WhenWordIsNotGuessed() {
        List<String> words = Arrays.asList("шишка", "время");
        WordleDictionary dictionary = new WordleDictionary(words);

        WordleGame game = new WordleGame(dictionary);
        String answer = game.getAnswer();

        assertNotNull(answer);
        assertEquals(5, answer.length());

        String guess;
        if (answer.equals("шишка")) {
            guess = "время";
        } else {
            guess = "шишка";
        }

        String result = game.coincidence(guess);

        assertEquals("-----", result, "При не совпадении результат дожнен быть '-----'");

        assertEquals(5, result.length(), "Результат сравнения всегда должен быть длиной 5 символов");
    }
}

