package diffie_hellman_elliptic_curves;

public class EllipticCurvePoint {
	long x, y;
	
	public EllipticCurvePoint(EllipticCurve curve) {
		this.x = (long) (Math.random() * curve.p);
		this.y = (long) Math.sqrt(x*x*x+curve.a*x+curve.b);
	}

	public EllipticCurvePoint(long x, long y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(EllipticCurvePoint other) {
		return this.x == other.x && this.y == other.y;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
}
