

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DZ3 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата рождения номер телефона пол");
            String input = scanner.nextLine();
            String[] data = input.split(" ");

            if (data.length != 6) {
                throw new IllegalArgumentException("Введено неверное количество данных. Введите 6.");
            }

            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            char gender = data[5].charAt(0);

            // Валидация данных
            validateData(birthDate, phoneNumber, gender);

            // Запись в файл
            writeToFile(surname, data);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static void validateData(String birthDate, String phoneNumber, char gender) {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения.");
        }
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Номер телефона должен содержать только цифры.");
        }
        if (gender != 'f' && gender != 'm') {
            throw new IllegalArgumentException("Пол должен быть указан как 'f' или 'm'.");
        }
    }

    private static void writeToFile(String surname, String[] data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(surname + ".txt", true))) {
            writer.write(String.join(" ", data));
            writer.newLine();
        }
    }
}
