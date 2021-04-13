package Algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Algorithms {

    private static boolean logging = true;
    private static String separator = "---------------------------------------------------------\n";

    public static BigInteger[] EEA(BigInteger p, BigInteger num) {
        boolean logging = false;
        BigInteger iteration = BigInteger.ZERO;
        BigInteger r1 = p;
        BigInteger r2 = num;
        BigInteger q1 = BigInteger.ZERO;
        BigInteger q2 = BigInteger.ZERO;

        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;

        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;

        BigInteger swap;

        if (logging) {
            System.out.println(String.format(separator +
                            "Extended Euclidean with numbers %s and %s",
                    String.valueOf(p.intValue()), String.valueOf(num.intValue())));
        }

        while (!r2.equals(BigInteger.ZERO)) {
            q1 = q2;
            iteration = iteration.add(BigInteger.valueOf(1));
            swap = r2;
            q2 = r1.divide(r2);
            r2 = r1.remainder(r2);
            r1 = swap;

            if(iteration.intValue() < 2) continue;

            swap = x1;
            x1 = x1.multiply(q1);
            x1 = x1.add(x0);
            x0 = swap;

            swap = y1;
            y1 = y1.multiply(q1);
            y1 = y1.add(y0);
            y0 = swap;

            //System.out.println("LNKO: " + String.valueOf(r1));
        }

        if (logging) {
            System.out.println("Iteration: " + iteration);
            System.out.println("LNKO: " + String.valueOf(r1));
            System.out.println("X: " + x1);
            System.out.println("Y: " + y1);
            System.out.println(separator);
        }

        BigInteger[] result = new BigInteger[4];
        result[0] = r1;
        result[1] = x1;
        result[2] = y1;
        result[3] = iteration;
        return result;
    }

    public static boolean isRelativePrime(BigInteger num1, BigInteger num2) {
        boolean logging = false;
        if(EEA(num1, num2)[0].equals(BigInteger.valueOf(1))) {
            if (logging) {
                System.out.println(separator +
                        num1 + " and " + num2 + " are relatively prime!\n" +
                        separator);
            }
            return true;
        } else {
            if (logging) {
                System.out.println(separator +
                        num1 + " and " + num2 + " are NOT relatively prime!\n" +
                        separator);
            }
            return false;
        }
    }

    public static BigInteger inverseCalc(BigInteger p, BigInteger num) {
        BigInteger[] eea_result = EEA(p, num);
        BigInteger result = BigInteger.valueOf(-1).pow(eea_result[3].intValue()+1).multiply(eea_result[2]);
        if (result.compareTo(BigInteger.ZERO) == -1) {
            result = p.subtract(result.abs());
        }

        return result;
    }

    public static BigInteger FME(BigInteger _p, BigInteger _base, BigInteger _exp) {
        boolean logging = false;
        if(logging) {
            System.out.println(String.format(separator +
                    "Starting Fast Modular Exponent with base %s, exponent %s and %s modulo\n", _base.toString(), _exp.toString(), _p.toString()));
        }
        BigInteger p = _p;
        BigInteger base = _base;

        ArrayList<BigInteger> remainders = new ArrayList<BigInteger>();
        BigInteger q = _exp;
        while (!q.equals(BigInteger.ZERO)) {
            remainders.add(q.remainder(BigInteger.valueOf(2)));
            q = q.divide(BigInteger.valueOf(2));
        }

        if(logging) {
            Collections.reverse(remainders);
            System.out.println("Exponent in BIN: ");
            for(BigInteger num:remainders) {
                System.out.printf(num.toString());
            }
            System.out.println();
            Collections.reverse(remainders);
        }


        ArrayList<BigInteger> exps = new ArrayList<BigInteger>();
        base = base.remainder(p);
        exps.add(base);
        for (int i = 1; i < remainders.size(); i++) {
            base = base.pow(2).remainder(p);
            exps.add(base);
        }

        //System.out.println(exps);

        BigInteger result = BigInteger.valueOf(1);
        for (int i = 0; i < remainders.size(); i++) {
            if (remainders.get(i).equals(BigInteger.valueOf(1))) {
                result = result.multiply(exps.get(i));
                result = result.remainder(_p);
            }
        }

        System.out.println("FME result: " + result);
        if(logging) {
            System.out.println(separator);
        }

        return result;
    }

    public static boolean MRPT(BigInteger _num, BigInteger _base) {
        boolean logging = true;
        if(logging) {
            System.out.println(String.format(separator + "Miller-Rabin test with number %s and %s base.", _num.toString(), _base.toString()));
        }

        if (!isRelativePrime(_num, _base)) {
            System.out.println("Miller-Rabin test failed with the given inputs!");
            System.out.println(separator);
            return false;
        }

        BigInteger num = _num.subtract(BigInteger.valueOf(1));

        int counter = 0;
        while(num.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            counter++;
            num = num.divide(BigInteger.valueOf(2));
        }


        BigInteger remainder_d = num;
        int pow = counter;

        if(logging) {
            System.out.println("Remainder (d): " + remainder_d);
            System.out.println("Number of 2 exponents: " + counter);
        }


        if (FME(_num, _base, remainder_d).equals(BigInteger.valueOf(1))) {
            System.out.println("The number is probably PRIME!");
            System.out.println(separator);
            return true;
        }

        BigInteger minusOne = _num.subtract(BigInteger.valueOf(1));
        for (int i = 0; i < counter; i++) {
            BigInteger c_result = FME(_num, _base, BigInteger.valueOf(2).pow(i).multiply(remainder_d));
            if(c_result.equals(minusOne)) {
                System.out.println("The number is probably PRIME!");
                System.out.println(separator);
                return true;
            }
        }

        if(logging) {
            System.out.println("The number is NOT prime!");
            System.out.println(separator);
        }

        return false;

    }

    public static void CRT(BigInteger p, BigInteger q, BigInteger n, BigInteger d, BigInteger c) {
        BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
        BigInteger dq = d.mod(q.subtract(BigInteger.ONE));
        System.out.println("dp: " + dp + " dq: " + dq);

        BigInteger mp = FME(p,c,dp);
        BigInteger mq = FME(q,c,dq);
        System.out.println("mp: " + mp + " mq: " + mq);

        BigInteger m1 = mp.mod(p);
        BigInteger m2 = mq.mod(q);
        System.out.println("m1: " + m1 + " m2: " + m2);

        BigInteger yp = EEA(p,q)[1].multiply(BigInteger.valueOf(-1).pow(EEA(p,q)[3].intValue()));
        BigInteger yq = EEA(p,q)[2].multiply(BigInteger.valueOf(-1).pow(EEA(p,q)[3].intValue()+1));
        System.out.println("yp: " + yp + " yq: " + yq);

        BigInteger message1 = mp.multiply(yq).multiply(q);
        BigInteger message2 = mq.multiply(yp).multiply(p);

        System.out.println("Message: " + (message1.add(message2)).mod(n));

    }

}
