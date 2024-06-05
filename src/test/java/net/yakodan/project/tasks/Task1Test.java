package net.yakodan.project.tasks;

import net.yakodan.project.tasks.Task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {

    private static Task1 task1 = new Task1();

    @Test
    @DisplayName("Задание 1. Тест с корректным ответом")
    public void testCalculateRWithCorrectAnswer(){
        Assertions.assertEquals(0, Double.compare(11.6444, task1.calculateR(15.5, 2, 5)));
    }

    @Test
    @DisplayName("Задание 1. Тест с некорректным ответом")
    public void testCalculateRWithIncorrectAnswer(){
        Assertions.assertNotEquals(0, Double.compare(7.00001, task1.calculateR(10,2,5)));
    }
}
