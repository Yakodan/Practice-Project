package net.yakodan.project.tasks;

import net.yakodan.project.tasks.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {

    private static Task2 task2 = new Task2();

    @Test
    @DisplayName("Задание 2. Тест с корректным ответом")
    public void testCalculateMtWithCorrectAnswer(){
        Assertions.assertEquals(0, Double.compare(6.3993, task2.calculateMt(5.5, 0.13, 8.8,9.1)));
    }

    @Test
    @DisplayName("Задание 2. Тест с некорректным ответом")
    public void testCalculateMtWithIncorrectAnswer(){
        Assertions.assertNotEquals(0, Double.compare(4.676, task2.calculateMt(5,0.11,8,9)));
    }
}
