package ru.taratorkin.TestApp.model;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component("Task")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type = "Найти все подстроки";

    @ElementCollection
    @CollectionTable(name="Lines", joinColumns=@JoinColumn(name="task_id"))
    @Column(name="operandFirst")
    private List<String> operandFirst;

    @ElementCollection
    @CollectionTable(name="Lines", joinColumns=@JoinColumn(name="task_id"))
    @Column(name="operandSecond")
    private List<String> operandSecond;

    @Column(name = "matrix")
    private String matrixString;

//    @Transient
    @Column
    private String line1;

//    @Transient
    @Column
    private String line2;

    @Transient
    private String selectTask;

    @Transient
    private int[][] matrix = new int[3][3];

    public Task(String line1, String line2){

        this.setLine1(line1);
        this.setLine2(line2);
    }

    public void setLine1(String line1) {
            operandFirst = Conversion.conversionOperand(line1);
            this.line1 = line1;
    }

    public void setLine2(String line2) {
            operandSecond = Conversion.conversionOperand(line2);
            this.line2 = line2;
    }

    public void setMatrixString(String matrixString) {
            this.matrix = Conversion.conversionMatrix(matrixString);
            this.matrixString = matrixString;
    }

    @Override
    public String toString() {
        if(type.equals(TypeTask.STRINGS.toString())){
            return String.format("a1 = %s; a2 = %s", operandFirst, operandSecond);
        }
        if(type.equals(TypeTask.MAGIC_SQUARE.toString())){
            return matrixString;
        }
        return "";
    }

}
