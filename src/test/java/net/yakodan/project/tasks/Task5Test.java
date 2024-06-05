package net.yakodan.project.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Task5Test {

    Task5 task5 = new Task5();

    @Test
    @DisplayName("Задание 5. Тест с корректным ответом")
    public void testSortingToGroupsWithCorrectAnswer(){
        ArrayList<Double> details = new ArrayList<>(Arrays.asList(10.2,10.45,11.12,12.4,12.54,13.0,13.24,14.2));
        double minValue = 10;
        double maxValue = 15;
        int numGroup = 5;
        ArrayList<Double>[] groups = task5.sortDetailsToGroups(details,numGroup,minValue,maxValue);

        String result = String.format("Изначальные значения: %s\n\nРаспределение по группам:\n", details);
        double h = (maxValue-minValue)/numGroup;
        int i=0;
        for(ArrayList<Double> group : groups){
            result += String.format("[%.2f, %.2f] : %s\n",minValue+i*h,minValue+(i+1)*h, group);
            i++;
        }

        String expected = "Изначальные значения: [10.2, 10.45, 11.12, 12.4, 12.54, 13.0, 13.24, 14.2]\n\n" +
                "Распределение по группам:\n" +
                "[10,00, 11,00] : [10.2, 10.45]\n" +
                "[11,00, 12,00] : [11.12]\n" +
                "[12,00, 13,00] : [12.4, 12.54]\n" +
                "[13,00, 14,00] : [13.0, 13.24]\n" +
                "[14,00, 15,00] : [14.2]\n";

        Assertions.assertTrue(result.compareTo(expected) == 0,
                String.format("actual = %s, expected = %s", result, expected));
    }

    @Test
    @DisplayName("Задание 5. Тест с некорректным ответом")
    public void testSortingToGroupsWithIncorrectAnswer(){
        ArrayList<Double> details = new ArrayList<>(Arrays.asList(10.0,10.45,11.0,12.0,12.54,13.0,13.24,14.0));
        double minValue = 10;
        double maxValue = 15;
        int numGroup = 5;
        ArrayList<Double>[] groups = task5.sortDetailsToGroups(details,numGroup,minValue,maxValue);

        String result = String.format("Изначальные значения: %s\n\nРаспределение по группам:\n", details);
        double h = (maxValue-minValue)/numGroup;
        int i=0;
        for(ArrayList<Double> group : groups){
            result += String.format("[%.2f, %.2f] : %s\n",minValue+i*h,minValue+(i+1)*h, group);
            i++;
        }

        String expected = "Изначальные значения: [10.0, 10.45, 11.0, 12.0, 12.54, 13.0, 13.24, 14.0]\n\n" +
                "Распределение по группам:\n" +
                "[10,00, 11,00] : [10.0, 10.45, 11.0]\n" +
                "[11,00, 12,00] : [11.0, 12.0]\n" +
                "[12,00, 13,00] : [12.0, 12.54, 13.0]\n" +
                "[13,00, 14,00] : [13.0, 13.24, 14.0]\n" +
                "[14,00, 15,00] : [14.0]\n";

        Assertions.assertTrue(result.compareTo(expected) != 0,
                String.format("actual = %s, expected = %s", result, expected));
    }
}
