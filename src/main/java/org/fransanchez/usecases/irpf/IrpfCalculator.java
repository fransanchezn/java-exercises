package org.fransanchez.usecases.irpf;

import java.util.ArrayList;
import java.util.List;

public class IrpfCalculator {

    public record TaxBracket(double taxRate, double lowerBound, double upperBound){}

    final static List<TaxBracket> BRACKETS = new ArrayList<>();

    static {
        BRACKETS.add(new TaxBracket(0.19d, 0, 12_450d));
        BRACKETS.add(new TaxBracket(0.24d, 12_451d, 20_199d));
        BRACKETS.add(new TaxBracket(0.30d, 20_200d, 35_199d));
        BRACKETS.add(new TaxBracket(0.37d, 35_200d, 59_999d));
        BRACKETS.add(new TaxBracket(0.45d, 60_000d, 299_999d));
        BRACKETS.add(new TaxBracket(0.47d, 300_000d, Double.MAX_VALUE));
    }

    public double calculateIrpf(final double amount) {
        var taxAmount = 0.0d;

        for (var bracket : BRACKETS) {
            if (amount < bracket.lowerBound) {
                return taxAmount;
            }

            final var upperBoundBracket = Math.min(amount, bracket.upperBound);
            final var amountToTax = upperBoundBracket - bracket.lowerBound;
            taxAmount += amountToTax * bracket.taxRate;
        }

        return taxAmount;
    }

    public static void main(String[] args) {
        final var irpfCalculator = new IrpfCalculator();

        var salary = 498_000d;
        var taxPercentage = 0d;
        do {
            var taxAmount = irpfCalculator.calculateIrpf(salary);
            taxPercentage = taxAmount / salary;
            salary++;
        } while (taxPercentage < 0.44);

        System.out.println("Salary: " + salary);
    }
}
