package Algorithms;

import java.math.BigInteger;

import static Algorithms.Algorithms.*;
import static Algorithms.Generate.generatePrime;

public class Key {

    private BigInteger[] publicKey;
    private BigInteger[] secretKey;

    public Key() {

    }

    public BigInteger[] getPublicKey() {
        return this.publicKey;
    }

    public BigInteger[] getSecretKey() {
        return this.secretKey;
    }

    public void generateKeys(int _bitLength) {
        BigInteger prime1 = generatePrime(_bitLength);
        BigInteger prime2 = generatePrime(_bitLength);

        while (prime1.equals(prime2)) {
            prime2 = generatePrime(_bitLength);
        }

        BigInteger n = prime1.multiply(prime2);
        BigInteger phi_n = prime1.subtract(BigInteger.ONE).multiply(prime2.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.ZERO;
        for (BigInteger i = BigInteger.valueOf(3); !(phi_n.subtract(i).equals(BigInteger.ZERO)); i=i.add(BigInteger.valueOf(2))) {
            if (isRelativePrime(phi_n, i)) {
                e = i;
                System.out.println("found: " + e.toString());
                break;
            }
        }

        BigInteger e_inverz = BigInteger.ZERO;
        e_inverz = inverseCalc(phi_n, e);
        System.out.println(e_inverz.toString());

        System.out.println(prime1.toString() + " | " + prime2.toString() + " | " + phi_n.toString() + " | " + e.toString());

        this.publicKey = new BigInteger[] {n, e};
        this.secretKey = new BigInteger[] {n, e_inverz};
    }

    public void showYourself() {
        System.out.println("Public key: " + this.publicKey[0] + " | " + this.publicKey[1]);
        System.out.println("Secret key: " + this.secretKey[0] + " | " + this.secretKey[1]);
    }

}
