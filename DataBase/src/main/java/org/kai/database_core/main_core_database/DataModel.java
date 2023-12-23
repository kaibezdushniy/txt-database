package org.kai.database_core.main_core_database;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.time.temporal.IsoFields;
import java.util.Scanner;


@Data
@AllArgsConstructor
public class DataModel<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> {
    private final ConfigDataBase config = new ConfigDataBase();
    private T1 yourData1;
    private T2 yourData2;
    private T3 yourData3;
    private T4 yourData4;
    private T5 yourData5;

    private T6 yourData6;
    private T7 yourData7;
    private T8 yourData8;
    private T9 yourData9;
    private T10 yourData10;

    /**
     * Создает объект DataModel из массива строк, представляющего данные файла.
     * Каждая строка массива parts соответствует одному полю объекта DataModel.
     * Метод предполагает, что данные в массиве parts соответствуют ожидаемому порядку полей.
     *
     * @param parts Массив строк, представляющий данные файла.
     * @param <T1> Тип данных для поля yourData1.
     * @param <T2> Тип данных для поля yourData2.
     * @param <T3> Тип данных для поля yourData3.
     * @param <T4> Тип данных для поля yourData4.
     * @param <T5> Тип данных для поля yourData5.
     * @param <T6> Тип данных для поля yourData6.
     * @param <T7> Тип данных для поля yourData7.
     * @param <T8> Тип данных для поля yourData8.
     * @param <T9> Тип данных для поля yourData9.
     * @param <T10> Тип данных для поля yourData10.
     * @return Созданный объект DataModel.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> DataModel<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> fromFileString(String[] parts) {
        // Преобразование строковых данных в соответствующие типы данных
        T1 yourData1 = (T1) parts[0];
        T2 yourData2 = (T2) parts[1];
        T3 yourData3 = (T3) parts[2];
        T4 yourData4 = (T4) parts[3];
        T5 yourData5 = (T5) parts[4];
        T6 yourData6 = (T6) parts[5];
        T7 yourData7 = (T7) parts[6];
        T8 yourData8 = (T8) parts[7];
        T9 yourData9 = (T9) parts[8];
        T10 yourData10 = (T10) parts[9];

        // Создание и возвращение объекта DataModel с заданными значениями
        return new DataModel<>(yourData1, yourData2, yourData3, yourData4, yourData5, yourData6, yourData7, yourData8, yourData9, yourData10);
    }


    /**
     * Преобразует объект DataModel в строку для записи в файл.
     * Каждое поле объекта добавляется в строку, разделенное конфигурируемым разделителем данных.
     *
     * @return Строка, представляющая объект DataModel для записи в файл.
     */
    public String toFileString() {
        StringBuilder stringBuilder = new StringBuilder();

        // Получение массива полей класса
        Field[] fields = this.getClass().getDeclaredFields();

        // Перебор полей объекта
        for (Field field : fields) {
            // Установка доступа к приватным полям
            field.setAccessible(true);

            try {
                // Добавление значения поля в строку с использованием разделителя
                stringBuilder.append(field.get(this)).append(config.getDataSeparator());
            } catch (IllegalAccessException e) {
                // Обработка исключения, если не удается получить доступ к полю
                e.printStackTrace();
            }
        }

        // Возврат строки, представляющей объект DataModel для записи в файл
        return stringBuilder.toString();
    }

}

