package ru.taratorkin.TestApp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ImportExportFile {

    private InputStream inputStream;
    public static final String DELIMITER = ":";
    public static final String DELIMITER_VALUE = ";";

    public ImportExportFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Task readFromFile() {
        Task task = new Task();

        try (BufferedReader read = new BufferedReader(new InputStreamReader(this.inputStream))) {
            String str;
            while ((str = read.readLine()) != null) {
                String[] line = str.split(DELIMITER);

                if (line[0].contains("Тип задачи")) {

                    task.setType(line[1]);
                }
                if (line[0].contains("Входные условия")) {

                    if (task.getType().equals(TypeTask.STRINGS.toString())) {

                        List<String> operands = Arrays.stream(line[1].split(DELIMITER_VALUE))
                                .map(s -> s.substring(s.indexOf("[") + 1, s.indexOf("]")))
                                .collect(Collectors.toList());

                        task.setLine1(operands.get(0));
                        task.setLine2(operands.get(1));
                    }
                    if (task.getType().equals(TypeTask.MAGIC_SQUARE.toString())) {
                        String matrixString = line[1];
                        task.setMatrixString(matrixString);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task;
    }

}
