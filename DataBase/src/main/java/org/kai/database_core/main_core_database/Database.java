package org.kai.database_core.main_core_database;


import lombok.AllArgsConstructor;

import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    /*
    Author - Kai
    Since - 23.12.23
     */
    public List<DataModel> storageData;

    private final ConfigDataBase config = new ConfigDataBase();

    public Database() {
        this.storageData = new ArrayList<>();
        loadDatabase();
    }

    public static void main(String[] args) {
        Database database = new Database();
        List<Object> test = new ArrayList<>();
        test.add("elnur");
        test.add("elnur");
        test.add("elnur");
        test.add("elnur");
        test.add("elnur");
        test.add("elnur");
        test.add("elnur");
        test.add("elnur");
        test.add("elnur");
        test.add("elnur");
        database.addToDatabase(test);
    }

    // Метод для загрузки всех данных из txt документа
    private List<DataModel> loadDatabase() {
        // При каждом вызове метода, лучше очищать лист для хранение данных, что бы преведущие данные из сессий не оставались
        if (storageData != null) {
            storageData.clear();

            // Читаем данные из txt-документа
            try (BufferedReader reader = new BufferedReader(new FileReader(config.getDatabasePath()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(config.getDataSeparator());
                    if (parts.length == config.getLinesInDatabaseRow()) {
                        DataModel entryData = DataModel.fromFileString(parts);
                        this.storageData.add(entryData); // Все полученные данные из txt-документа, ложим в List
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return storageData;
        } else {
            // Оброботка случая если вы забыли cоздать ArrayList
            this.storageData = new ArrayList<>();
            loadDatabase();
        }
        return storageData;
    }

    // Метод для сохранение новых данных в базе данных
    private void saveDatabase() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(config.getDatabasePath()))){
            for (DataModel entryData : storageData) {
                writer.println(entryData.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*
    Метод для добавление данных в базу данных
    Я использывал List, для хранение данных!, но вы можете использывать переменные которые прописаны в класcе DataModel, и добавив их в параметр метода
     */
    private void addToDatabase(List<Object> newData) {
        if (newData != null && !newData.isEmpty() && newData.size() == config.getLinesInDatabaseRow()) {
            DataModel data = new DataModel(
                    newData.get(0),
                    newData.get(1),
                    newData.get(2),
                    newData.get(3),
                    newData.get(4),
                    newData.get(5),
                    newData.get(6),
                    newData.get(7),
                    newData.get(8),
                    newData.get(9)
            );
            storageData.add(data); // Сохраняем новые данные в лист Storage Data
            saveDatabase();
        }
    }

    private void deleteIndDatabase() {

    }

}

