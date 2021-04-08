package Application;

import Algorithms.Key;

import static Algorithms.Algorithms.*;
import static Algorithms.Cypher.Decrypt;
import static Algorithms.Cypher.Encrypt;
import static Algorithms.Generate.generatePrime;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        /*
        EEA(BigInteger.valueOf(212680), BigInteger.valueOf(268));
        EEA(BigInteger.valueOf(60), BigInteger.valueOf(35));
        EEA(BigInteger.valueOf(7), BigInteger.valueOf(4));
        EEA(BigInteger.valueOf(432423), BigInteger.valueOf(789));
        isRelativePrime(BigInteger.valueOf(60), BigInteger.valueOf(77));
        EEA(BigInteger.valueOf(60), BigInteger.valueOf(77));
        */
        //FME(BigInteger.valueOf(234), BigInteger.valueOf(325423), BigInteger.valueOf(42342));
        //FME(new BigInteger("2344"), new BigInteger("32542356577997792178912345"), new BigInteger("4234254779977281234577"));
        //MRPT(BigInteger.valueOf(577),BigInteger.valueOf(13));
        //MRPT(BigInteger.valueOf(438592389),BigInteger.valueOf(23));
        //System.out.println(MRPT(new BigInteger("134533625275571273000912774317612615813"),new BigInteger("1246721745")));
        //System.out.println(MRPT(new BigInteger("1488944457420414343"),new BigInteger("1134411599842300759")));
        //System.out.println(MRPT(new BigInteger("1488944457420414344354353"),new BigInteger("1098452448533733851741861")));
       // System.out.println(".---s-.");
        //System.out.println(MRPT(new BigInteger("202297305847716217022646411153041850427"),new BigInteger("361388020")));
        /*MRPT(new BigInteger("6796796819"), new BigInteger("4324234"));
        MRPT(new BigInteger("1000000007681"), new BigInteger("29267148978"));
        MRPT(new BigInteger("1000000007681"), new BigInteger("895413563130"));
        MRPT(new BigInteger("56456747071"), new BigInteger("19994034285"));
        System.out.println(inverseCalc(BigInteger.valueOf(17), BigInteger.valueOf(8)));
        System.out.println(inverseCalc(BigInteger.valueOf(433), BigInteger.valueOf(4325423)));
        System.out.println(inverseCalc(BigInteger.valueOf(4325423), BigInteger.valueOf(433)));
        System.out.println(inverseCalc(BigInteger.valueOf(7), BigInteger.valueOf(4)));
        System.out.println(inverseCalc(BigInteger.valueOf(4), BigInteger.valueOf(7)));
        FME(BigInteger.valueOf(131), BigInteger.valueOf(14), BigInteger.valueOf(37));
         */
        System.out.println(generatePrime(128));

        Key kulcs = new Key();
        kulcs.generateKeys(10);
        kulcs.showYourself();

        Encrypt(kulcs, BigInteger.valueOf(12345678));
        Decrypt(kulcs, BigInteger.valueOf(88888888));
        kulcs.generateKeys(BigInteger.valueOf(293), BigInteger.valueOf(317), BigInteger.valueOf(1079));
        kulcs.showYourself();

    }

}
