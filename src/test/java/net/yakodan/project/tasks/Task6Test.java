package net.yakodan.project.tasks;

import net.yakodan.project.polynom.Polynom;
import net.yakodan.project.polynom.PolynomialParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {

    @Test
    public void testNewtonPolynom(){
        Polynom expected = PolynomialParser.parse("-0.125x^2 + 2.25x - 3");
        Polynom actual = Task6.getNewtonPolynomial(new double[]{0,2,4}, new double[]{-3,1,4});

        Assertions.assertEquals(expected.toString(),actual.toString());
    }

    @Test
    public void testLagrangePolynom(){
        Polynom expected = PolynomialParser.parse("-0.5x^2 + 2.5x - 1");
        Polynom actual = Task6.getLagrangePolynomial(new double[]{0,1,4}, new double[]{-1,1,1});

        Assertions.assertEquals(expected.toString(),actual.toString());
    }

}
