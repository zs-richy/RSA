package Algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

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
        BigInteger[] publicKey = kulcs.getPublicKey();
        BigInteger mToEncrypt = new BigInteger(allBytes);

        if (publicKey[0].compareTo(mToEncrypt) == -1) {
            throw new IllegalArgumentException("Message is longer than public key! Call block encrypt function!");
        }

        return Encrypt(kulcs, mToEncrypt);

    }

    public static ArrayList<BigInteger> blockEncrypt(Key kulcs, String message) {
        ArrayList<BigInteger> result = new ArrayList<BigInteger>();
        char[] msgChars = message.toCharArray();

        int i = 1;
        int j = 0;
        byte[] tempBytes;
        BigInteger tempRes;
        while (i <= msgChars.length) {
            tempBytes = new String(Arrays.copyOfRange(msgChars,j,i)).getBytes();
            tempRes = new BigInteger(tempBytes);
            if (tempRes.compareTo(kulcs.getPublicKey()[0]) == 0 || tempRes.compareTo(kulcs.getPublicKey()[0]) == 1) {
                tempBytes = new String(Arrays.copyOfRange(msgChars,j,i-1)).getBytes();
                tempRes = new BigInteger(tempBytes);
                result.add(Encrypt(kulcs,tempRes));
                j = i-1;
                i--;
            }
            if (i == msgChars.length) {
                result.add(Encrypt(kulcs,tempRes));
            }
            i++;
        }
        return result;
    }


}
