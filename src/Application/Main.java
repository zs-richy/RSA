package Application;

import Algorithms.Key;

import static Algorithms.Algorithms.*;
import static Algorithms.Cypher.*;
import static Algorithms.Generate.generatePrime;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        EEA(BigInteger.valueOf(212680), BigInteger.valueOf(268));
        EEA(BigInteger.valueOf(60), BigInteger.valueOf(35));
        FME(BigInteger.valueOf(234), BigInteger.valueOf(325423), BigInteger.valueOf(42342));
        FME(new BigInteger("2344"), new BigInteger("32542356577997792178912345"), new BigInteger("4234254779977281234577"));
        MRPT(BigInteger.valueOf(577),BigInteger.valueOf(13));
        MRPT(BigInteger.valueOf(438592389),BigInteger.valueOf(23));

        System.out.println(generatePrime(128));

        Key kulcs = new Key();
        kulcs.generateKeys(256);
        kulcs.showYourself();

        //kulcs.generateKeys(BigInteger.valueOf(293), BigInteger.valueOf(317), BigInteger.valueOf(1079));

        BigInteger enc = Encrypt(kulcs, "Text to encrypt");
        System.out.println("Encrypted text: " + enc);
        System.out.println("Decrypted text: " + DecryptString(kulcs, enc));

    }

}
