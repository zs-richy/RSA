package Algorithms;

import java.math.BigInteger;

import static Algorithms.Algorithms.FME;

public class Cypher {

    public static BigInteger Encrypt(Key kulcs, BigInteger message) {
        BigInteger[] publicKey = kulcs.getPublicKey();
        return (FME(publicKey[0], message, publicKey[1]));
    }

    public static BigInteger Decrypt(Key kulcs, BigInteger message) {
        BigInteger[] secretKey = kulcs.getSecretKey();
        return FME(secretKey[0], message, secretKey[1]);
    }


    public static String DecryptString(Key kulcs, BigInteger message) {
        BigInteger[] secretKey = kulcs.getSecretKey();
        String result = new String(FME(secretKey[0], message, secretKey[1]).toByteArray());
        return result;
    }


    public static BigInteger Encrypt(Key kulcs, String message) {
        byte[] allBytes = message.getBytes();
        BigInteger mToEncrypt = new BigInteger(allBytes);

        return Encrypt(kulcs, mToEncrypt);

    }

}
