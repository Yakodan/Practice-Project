package net.yakodan.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {

    private static Task4Controller task4 = new Task4Controller();

    @Test
    @DisplayName("Задание 3. Параметризированный тест с корректными ответами")
    public void testCalculateERWithCorrectAnswers(){
        double dsh = 8.0;
        double e0 = 0.003;
        double answer = 0.000036;
        Double actual = task4.calculateER(dsh,e0);
        Assertions.assertTrue(actual.compareTo(answer) == 0,
                String.format("actual = %f, expected = %f", actual, answer));
    }

    @Test
    @DisplayName("Задание 3. Параметризированный тест с некорректными ответами")
    public void testCalculateERWithIncorrectAnswers(){
        double dsh = 8.5;
        double e0 = 0.003;
        double answer = 0.000036;
        Double actual = task4.calculateER(dsh,e0);
        Assertions.assertTrue(actual.compareTo(answer) != 0,
                String.format("actual = %f, expected = %f", actual, answer));
    }
    
}
