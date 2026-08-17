package ru.yandex.practicum;


import java.util.*;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {
    private final List<String> words;
    private final Set<String> wordSet;

    public WordleDictionary(List<String> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Словарь не может быть пустым или null");
        }

        this.words = words;
        this.wordSet = new HashSet<>(words);
    }


    public String getRandomWord() {
        if (words.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    public boolean isValidWord(String word) {
        if (word == null) {
            return false;
        }
        return wordSet.contains(word.toLowerCase());
    }

}
