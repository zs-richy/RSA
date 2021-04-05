package Algorithms;

import java.math.BigInteger;

import static Algorithms.Algorithms.FME;

public class Cypher {

    public static void Encrypt(Key kulcs, BigInteger message) {
        BigInteger[] publicKey = kulcs.getPublicKey();
        System.out.println(FME(publicKey[0], message, publicKey[1]));
    }

    public static void Decrypt(Key kulcs, BigInteger message) {
        BigInteger[] secretKey = kulcs.getSecretKey();
        System.out.println(FME(secretKey[0], message, secretKey[1]));
    }

}
