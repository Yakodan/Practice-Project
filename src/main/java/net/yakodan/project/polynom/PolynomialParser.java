package net.yakodan.project.polynom;

import java.util.*;
import java.util.regex.*;

/**
 * @author FatNinja from comments in
 * <a href = "https://www.cyberforum.ru/java/thread1751861.html">this stackoverflow page</a>
 */
public class PolynomialParser {

    private final static int INDEX_SIGN_COEFF = 1;
    private final static int INDEX_COEFF = 2;
    private final static int INDEX_VARIABLE = 3;
    private final static int INDEX_DEGREE = 4;

    private final static String NEGATIVE_SIGN = "-";
    private final static String MONOMIAL_TEMPLATE =
            "([+-])?(\\d+(?:[.,]\\d+)?)?(x)?(?:\\^(\\d+))?";

    /**
     * Получение многочлена одной переменной из строки, записанной в
     * стандартной математической форме.
     * Переменная многочлена должна быть "x".
     */
    public static Polynom parse(String rawPolynomial) {
        String source = normalizeSourceString(rawPolynomial);
        Map<Integer, Double> result = new HashMap<>();
        Pattern monomial = Pattern.compile(MONOMIAL_TEMPLATE);
        Matcher m = monomial.matcher(source);
        while ((!m.hitEnd() && (m.find()))) {
            boolean isNegative = NEGATIVE_SIGN.equals(m.group(INDEX_SIGN_COEFF));
            int currentDegree = calcDegree(m.group(INDEX_DEGREE),
                    m.group(INDEX_VARIABLE));
            double currentCoeff = calcCoeff(isNegative, m.group(INDEX_COEFF));
            result.put(currentDegree, currentCoeff);
        }
        return new Polynom(result);
    }

    /**
     * Вычисление коэффициента одночлена
     */
    private static double calcCoeff(boolean isNegative, String coefficient) {
        double result = (coefficient != null) ? Double.parseDouble(coefficient) : 1;
        return (isNegative) ? -result : result;
    }

    /**
     * Вычисление степени одночлена
     */
    private static int calcDegree(String degree, String symbolVarialble) {
        int result = (symbolVarialble != null) ? 1 : 0;
        return (degree != null) ? Integer.parseInt(degree) : result;
    }

    /**
     * Удаление из строки всех пробелов и
     * преобразование всех символов в нижний регистр
     */
    private static String normalizeSourceString(String source) {
        final String EMPTY_STRING = "";
        final String WHITESPACE_TEMPLATE = "\\s+";
        String result = source.replaceAll(WHITESPACE_TEMPLATE, EMPTY_STRING);
        return result.toLowerCase();
    }
}
