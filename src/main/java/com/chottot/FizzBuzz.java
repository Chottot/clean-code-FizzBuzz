package com.chottot;

public final class FizzBuzz {

    private static final long MIN_DIVIDER = 1;
    private static final long MAX_DIVIDER = 1000000000000L;


    private static final int DEFAULT_FIZZ_DIVIDER = 3;
    private static final int DEFAULT_BUZZ_DIVIDER = 5;

    private final long divider_fizz;
    private final long divider_buzz;

    public FizzBuzz(final long divider_fizz, final long divider_buzz) {

        if (divider_buzz <= MIN_DIVIDER || divider_fizz <= MIN_DIVIDER || divider_buzz > MAX_DIVIDER || divider_fizz > MAX_DIVIDER) {
            throw new IllegalArgumentException("Divisor must be in range ]" + MIN_DIVIDER + "," + MAX_DIVIDER + "]");
        } else if (!isPrimeNumber(divider_fizz) || !isPrimeNumber(divider_buzz)) {
            throw new IllegalArgumentException("Divisor must be a prime");
        } else if (divider_buzz == divider_fizz) {
            throw new IllegalArgumentException("Divisors must be distinct");
        }

        this.divider_fizz = divider_fizz;
        this.divider_buzz = divider_buzz;
    }

    public FizzBuzz() {
        this(DEFAULT_FIZZ_DIVIDER, DEFAULT_BUZZ_DIVIDER);
    }

    private boolean isPrimeNumber(final long number) {
        if (number <= 1)
            return false;

        for (int i = 2; i < number; i++)
            if (number % i == 0)
                return false;

        return true;
    }

    public String printTo(final long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot fizzbuzz negative numbers");
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= number; i++) {
            if (i % divider_fizz != 0 && i % divider_buzz != 0) {
                stringBuilder.append(i).append(" ");
            } else {
                if (i % divider_fizz == 0) {
                    stringBuilder.append("fizz");
                }
                if (i % divider_buzz == 0) {
                    stringBuilder.append("buzz");
                }
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public void display(final long number) {
        System.out.println(printTo(number));
    }


    public static void createAndDisplayFizzBuzzFromCommandLine(String[] args) {
        FizzBuzz fizzBuzz;
        long number;

        if (args.length == 1) {
            try {
                fizzBuzz = new FizzBuzz();
                number = Long.parseLong(args[0]);

            } catch (NumberFormatException e) {
                System.err.println("Error not a number " + e.getMessage());
                return;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                return;
            }

        } else if (args.length == 2) {

            try {
                String[] dividers = args[0].split(",");
                if (dividers.length != 2) {
                    System.err.println("Error \"" + args[0] + "\" is not a valid valid FizzBuzz parameter.\nExpected \"x,y\" with x and y in range ]" + MIN_DIVIDER + "," + MAX_DIVIDER + "]");
                    return;
                }

                long fizz_divider = Long.parseLong(dividers[0]);
                long buzz_divider = Long.parseLong(dividers[1]);

                fizzBuzz = new FizzBuzz(fizz_divider, buzz_divider);
                number = Long.parseLong(args[1]);

            } catch (NumberFormatException e) {
                System.err.println("Error not a number " + e.getMessage());
                return;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                return;
            }

        } else {
            System.err.println("Wrong arguments number");
            return;
        }

        try {
            fizzBuzz.display(number);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

}
