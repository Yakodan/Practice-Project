package net.yakodan.project.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class Task7Test {

    Task7 task7 = new Task7();

    @Test
    public void testRectangleIntegral(){
        Function<Double,Double> function = x -> 1/(x+1);
        Assertions.assertEquals(2.6454, task7.calculateRectangleIntegral(function,0,10,20));
    }

    @Test
    public void testTrapezeIntegral(){
        Function<Double,Double> function = x -> 1/(x+1);
        Assertions.assertEquals(2.4181, task7.calculateTrapezeIntegral(function,0,10,20));
    }

    @Test
    public void testSimsonIntegral(){
        Function<Double,Double> function = x -> 5*x*x-25*x;
        Assertions.assertEquals(416.6667, task7.calculateSimpsonIntegral(function,0,10,20));
    }

}
