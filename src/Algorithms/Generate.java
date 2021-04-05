package Algorithms;

import java.math.BigInteger;
import java.security.SecureRandom;

import static Algorithms.Algorithms.MRPT;

public class Generate {

    public static BigInteger generatePrime(int _bitLength){

        SecureRandom sR;
        SecureRandom sR2;

        BigInteger prime = BigInteger.ONE;
        BigInteger base;

        boolean foundPrime = false;

        while(!foundPrime) {
            foundPrime = true;
            sR = new SecureRandom();
            sR2 = new SecureRandom();
            prime = new BigInteger(_bitLength, sR);

            for(int i=0; i<5;i++){
                base = new BigInteger(32+5*i, sR2);
                if(!MRPT(prime, base)){
                    foundPrime = false;
                    break;
                }
            }
        }

        return prime;
    }

}
