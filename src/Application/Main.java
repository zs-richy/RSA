package Application;

import Algorithms.Key;

import static Algorithms.Algorithms.*;
import static Algorithms.Cypher.*;
import static Algorithms.Generate.generatePrime;

import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        EEA(BigInteger.valueOf(212680), BigInteger.valueOf(268));
        EEA(BigInteger.valueOf(60), BigInteger.valueOf(35));
        FME(BigInteger.valueOf(234), BigInteger.valueOf(325423), BigInteger.valueOf(42342));
        FME(new BigInteger("2344"), new BigInteger("32542356577997792178912345"), new BigInteger("4234254779977281234577"));
        MRPT(BigInteger.valueOf(577),BigInteger.valueOf(13));
        MRPT(BigInteger.valueOf(438592389),BigInteger.valueOf(23));
        CRT(BigInteger.valueOf(463), BigInteger.valueOf(547), BigInteger.valueOf(253261),
                BigInteger.valueOf(166379),BigInteger.valueOf(47));

        //kulcs.generateKeys(BigInteger.valueOf(293), BigInteger.valueOf(317), BigInteger.valueOf(1079));
        Key kulcs = new Key();
        kulcs.generateKeys(128);
        kulcs.showYourself();

        try {
            BigInteger enc = Encrypt(kulcs, "Phase01 Phase02 Phase03 Phase04 Phase05 Phase06 Phase07 Phase08 Phase09  Phase10 Phase11 Phase12");
            System.out.println("Encrypted text: " + enc);
            System.out.println("Decrypted text: " + DecryptString(kulcs, enc));
        } catch (Exception e) {
            System.out.println("Message too long for this key! Try with blockEncrypt method!");
        }

        ArrayList<BigInteger> encText = blockEncrypt(kulcs, "Phase01 Phase02 Phase03 Phase04 Phase05 Phase06 Phase07 Phase08 Phase09  Phase10 Phase11 Phase12");
        System.out.println("Encrypted text: " + encText);
        System.out.println("Decrypted text: ");
        for (BigInteger e : encText) {
            System.out.print(DecryptString(kulcs, e) + "|");
        }
        System.out.println();

    }

}
