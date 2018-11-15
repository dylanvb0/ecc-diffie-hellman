package diffie_hellman_elliptic_curves;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("┌───────────────────────────────────────────");
		// Generating Public Info
		EllipticCurve curve = new EllipticCurve();
		System.out.println("│Shared curve is: " + curve);
		EllipticCurvePoint g = new EllipticCurvePoint(14, 11);
		System.out.println("│Generator is: " + g);
//		System.out.println();
		
		System.out.println("├───────────────────────────────────────────");
		// Choosing Private Keys
		Scanner s = new Scanner(System.in);
		System.out.print("│Charlie, choose a private key >> ");
		long c = s.nextLong();
		System.out.print("│Dave, choose a private key >> ");
		long d = s.nextLong();
//		System.out.println();

		System.out.println("├───────────────────────────────────────────");
		// Calculating Resulting Public Keys
		EllipticCurvePoint cg = curve.addNTimes(g, g, c-1);
		EllipticCurvePoint dg = curve.addNTimes(g, g, d-1);
		System.out.println("│Charlie Public Key: " + cg);
		System.out.println("│Dave Public Key: " + dg);
//		System.out.println();

		System.out.println("├───────────────────────────────────────────");
		// Calculating Shared Private Key from other public key and own private key
		EllipticCurvePoint dcg = curve.addNTimes(cg, g, d);
		EllipticCurvePoint cdg = curve.addNTimes(dg, g, c);
		System.out.println("│Dave's shared key: " + dcg);
		System.out.println("│Charlie's shared key: " + cdg);
		System.out.println("└───────────────────────────────────────────");
	}
}
