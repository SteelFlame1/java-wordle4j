package ru.yandex.practicum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {

    private static final int wordLength = 5;

    public List<String> load(String filename) throws IOException {
        List<String> words = new ArrayList<>();
        String line;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8) ) {
            while ((line = br.readLine()) != null) {
                if (line.length() == wordLength && line.matches("[а-яёА-ЯЁ]+")) {
                    words.add(line.toLowerCase().replace("ё", "е").replace("Ё", "Е"));

                }

            }
        }
        return words;
    }
}
