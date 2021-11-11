package ru.taratorkin.TestApp.model;

import lombok.Getter;
import lombok.Setter;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ImportExportFile {

    private InputStream inputStream;
    private OutputStream outputStream;
    private static final String DELIMITER = ":";
    private static final String DELIMITER_VALUE = ";";
    private static final String TYPE_TASK = "Тип задачи";
    private static final String INPUT_VALUE = "Входные условия";

    public ImportExportFile() {
    }

    public ImportExportFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ImportExportFile(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    public Task readFromFile() throws IOException {
        String currentLine;
        HashMap<String, String> mapValues = new HashMap<>();
        try (BufferedReader read = new BufferedReader(new InputStreamReader(this.inputStream))) {

            while ((currentLine = read.readLine()) != null) {

                String[] line = currentLine.split(DELIMITER);
                mapValues.put(line[0], line[1]);
            }
        }

        Task task = new Task();
        task.setType(mapValues.get(TYPE_TASK));
        if (task.getType().equals(TypeTask.STRINGS.toString())) {

            List<String> operands = Arrays.stream(mapValues.get(INPUT_VALUE).split(DELIMITER_VALUE))
                    .map(s -> s.substring(s.indexOf("[") + 1, s.indexOf("]")))
                    .collect(Collectors.toList());
            task.setLine1(operands.get(0));
            task.setLine2(operands.get(1));
        }
        if (task.getType().equals(TypeTask.MAGIC_SQUARE.toString())) {

            task.setMatrixString(mapValues.get(INPUT_VALUE));
        }
        return task;
    }

    public void writeToFile(Task task) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Тип задачи:" + task.getType());
        bufferedWriter.newLine();
        bufferedWriter.write("Входные условия:" + task.toString());

        bufferedWriter.close();
    }

}
