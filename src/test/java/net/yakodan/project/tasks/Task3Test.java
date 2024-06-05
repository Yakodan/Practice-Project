package net.yakodan.project.tasks;

import net.yakodan.project.tasks.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {

    private static Task3 task4 = new Task3();

    @Test
    @DisplayName("Задание 3. Параметризированный тест с корректными ответами")
    public void testCalculateERWithCorrectAnswers(){
        double dsh = 8.0;
        double answer = 0.000036;
        Double actual = task4.calculateER(dsh);
        Assertions.assertTrue(actual.compareTo(answer) == 0,
                String.format("actual = %f, expected = %f", actual, answer));
    }

    @Test
    @DisplayName("Задание 3. Параметризированный тест с некорректными ответами")
    public void testCalculateERWithIncorrectAnswers(){
        double dsh = 8.5;
        double answer = 0.000036;
        Double actual = task4.calculateER(dsh);
        Assertions.assertTrue(actual.compareTo(answer) != 0,
                String.format("actual = %f, expected = %f", actual, answer));
    }
}
