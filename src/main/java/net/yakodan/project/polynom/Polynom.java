package net.yakodan.project.polynom;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author FatNinja from comments in
 * <a href = "https://www.cyberforum.ru/java/thread1751861.html">this stackoverflow page</a>
 */
public class Polynom {

    final static String EMPTY_STRING = "";
    final static String POSITIVE_SIGN = " + ";
    final static String NEGATIVE_SIGN = " - ";
    final static String DEGREE_MARK_1 = "x";
    final static String DEGREE_MARK_NTH = "x^";

    private final Map<Integer, Double> members
            = new TreeMap<Integer, Double>(Collections.reverseOrder());

    public Polynom(Map<Integer, Double> members) {
        this.members.putAll(members);
        deleteMembersWithZeroCoefficient();
    }

    public double valueOf(double x){
        double result = 0;

        for(Integer currentKey : members.keySet()){
            result += members.get(currentKey)*Math.pow(x,currentKey);
        }

        return (double) Math.round(result * 1000000) /1000000;
    }

    /**
     * Сложение
     */
    public Polynom add(Polynom other) {
        Map<Integer, Double> result = new TreeMap<Integer, Double>();
        result.putAll(members);

        for (Integer currentKey : other.members.keySet()) {
            Double resultValue = other.members.get(currentKey);
            Double currentValue = result.get(currentKey);
            if (currentValue != null) {
                resultValue += currentValue;
            }
            result.put(currentKey, resultValue);
        }
        return new Polynom(result);
    }

    /**
     * Вычитание
     */
    public Polynom subtract(Polynom other) {
        Map<Integer, Double> result = new TreeMap<Integer, Double>();
        result.putAll(members);

        for (Integer currentKey : other.members.keySet()) {
            Double currentValue = result.get(currentKey);
            if (currentValue != null) {
                Double difference = currentValue - other.members.get(currentKey);
                result.put(currentKey, difference);
            } else {
                result.put(currentKey, other.members.get(currentKey));
            }
        }
        return new Polynom(result);
    }

    /**
     * Умножение
     */
    public Polynom multiply(Polynom other) {
        Map<Integer, Double> result = new TreeMap<Integer, Double>();

        for (Entry<Integer, Double> first : members.entrySet()) {
            for (Entry<Integer, Double> second : other.members.entrySet()) {
                Integer amountKey = first.getKey() + second.getKey();
                Double productValue = first.getValue() * second.getValue();
                if (result.containsKey(amountKey)) {
                    productValue += result.get(amountKey);
                }
                result.put(amountKey, productValue);
            }
        }
        return new Polynom(result);
    }

    public Polynom divideByNumber(double div){
        Map<Integer,Double> newMembers = new TreeMap<>();
        for(Integer currentKey : members.keySet()){
            double newValue = members.get(currentKey)/div;
            newMembers.put(currentKey, newValue);
        }
        return new Polynom(newMembers);
    }

    private void deleteMembersWithZeroCoefficient() {
        Iterator<Entry<Integer, Double>> iterator = members.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Integer, Double> pair = iterator.next();
            if (pair.getValue() == 0) {
                iterator.remove();
            }
        }
    }

    /**
     * Получение строкового представления знака одночлена в зависимости от знака
     * коэффициента и места размещения одночлена в многочлене. Если коэффициент
     * одночлена отрицательный - возвращается " - "; Если коэффициент одночлена
     * положительный и одночлен первый в многочлене - возвращается пустая
     * строка. Иначе - " + ";
     */
    private String viewSignMonomial(boolean isFirst, double coefficient) {
        final int MIN_POSITIVE_COEFFICIENT = 0;
        if (coefficient < MIN_POSITIVE_COEFFICIENT) {
            return NEGATIVE_SIGN;
        } else {
            return (isFirst) ? EMPTY_STRING : POSITIVE_SIGN;
        }
    }

    /**
     * Получение строкового представления коэффициента одночлена в зависимости
     * от степени. Если коэффициент равен единице или степень нулевая -
     * возвращается пустая строка. Иначе - возвращается коэффициент.
     */
    private String viewCoefficient(double coeff, int degree) {
        return ((coeff != 1) || (degree == 0))
                ? String.valueOf(coeff)
                : EMPTY_STRING;
    }

    /**
     * Получение строкового представления степени одночлена. Если степень
     * нулевая - возвращается пустая строка. Если степень 1 - возвращается "x".
     * Иначе - возвращается "x^" + степень.
     */
    private String viewDegree(int degree) {
        String result = EMPTY_STRING;
        if (degree != 0) {
            result = (degree == 1) ? DEGREE_MARK_1 : DEGREE_MARK_NTH + degree;
        }
        return result;
    }

    @Override
    public String toString() {
        boolean isFirstMember = true;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Double> current : members.entrySet()) {
            double currentCoeff = (double) Math.round(current.getValue() * 10000) /10000;
            int currentDegree = current.getKey();
            builder.append(viewSignMonomial(isFirstMember, currentCoeff));
            builder.append(viewCoefficient(Math.abs(currentCoeff),
                    currentDegree));
            builder.append(viewDegree(currentDegree));
            isFirstMember = false;
        }
        return builder.toString();
    }
}