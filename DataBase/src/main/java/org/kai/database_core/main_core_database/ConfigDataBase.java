package org.kai.database_core.main_core_database;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;

@Data
public class ConfigDataBase {
    private String databasePath = "data.txt";
    private String dataSeparator = ":";
    private int linesInDatabaseRow = getFieldCount(DataModel.class);

    public static void main(String[] args) {
        ConfigDataBase configDataBase = new ConfigDataBase();
        System.out.println(configDataBase.linesInDatabaseRow);
    }

    public static int getFieldCount(Class<?> clazz) {
        // Получаем массив всех полей класса
        Field[] fields = clazz.getDeclaredFields();

        // Возвращаем количество полей
        return fields.length - 1;
    }
}
