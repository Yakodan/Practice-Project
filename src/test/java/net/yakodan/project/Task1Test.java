package net.yakodan.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {

    private static Task1Controller task1 = new Task1Controller();

    @Test
    public void testCalculateRWithCorrectAnswer(){
        Assertions.assertEquals(0, Double.compare(11.6444, task1.calculateR(15.5, 2, 5)));
    }

    @Test
    public void testCalculateRWithIncorrectAnswer(){
        Assertions.assertNotEquals(0, Double.compare(7.00001, task1.calculateR(10,2,5)));
    }
}
