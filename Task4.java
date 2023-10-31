import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Task4 {
    /**
     * Программа определяет, сколько символов записано в файл input.txt и выводит их в файл output.txt.
     * Вводить можно любые буквы, знаки препинания и символы.
     * Только нужно будет в inputFileName и в outputFileName указать полный путь к файлам.
     *
     * @author Artem Tukalenko
     */

    public static void main(String[] args) {
        String inputFileName = "C:/Users/БратскийПК/IdeaProjects/Java/src/input.txt"; // Имя и путь входного файла
        String outputFileName = "C:/Users/БратскийПК/IdeaProjects/Java/src/output.txt"; // Имя и путь выходного файла

        Map<Character, Integer> frequencyMap = new HashMap<>(); // Создаем Map для хранения частоты

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            int currentChar;
            while ((currentChar = reader.read()) != -1) { // Считываем символы из файла
                char character = (char) currentChar;
                if (Character.isWhitespace(character)) { // Исключаем пробелы и переводы строк
                    continue;
                }

                frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1); // Обновляем частоту символа
            }
            System.out.println("Результат успешно записан в файл output.txt!");
        } catch (IOException e) {
            System.out.println("Ошибка при обработке файла: " + e.getMessage());
        }

        Map<Character, Integer> sortedFrequencyMap = new TreeMap<>((a, b) -> {
            // Сортировка частоты по убыванию и символов по возрастанию
            int frequencyCompare = frequencyMap.get(b).compareTo(frequencyMap.get(a));
            if (frequencyCompare == 0) {
                return Character.compare(a, b);
            }
            return frequencyCompare;
        });

        sortedFrequencyMap.putAll(frequencyMap); // Сортируем Map по частоте
        try (FileWriter writer = new FileWriter(outputFileName)) { // Создаем FileWriter для записи в файл
            for (Map.Entry<Character, Integer> entry : sortedFrequencyMap.entrySet()) {
                writer.write("'" + entry.getKey() + "'=" + entry.getValue() + ", "); // Записываем символы и их частоту в формате 'символ'=частота
            }
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибки ввода-вывода
        }
    }
}