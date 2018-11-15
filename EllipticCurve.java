package diffie_hellman_elliptic_curves;

public class EllipticCurve {
	// Field is Z_p
	long a, b, p;
	
	public EllipticCurve() {
//		a = (long) (Math.random() * Long.MAX_VALUE);
//		b = (long) (Math.random() * Long.MAX_VALUE);
		a = 13;
		b = 7;
		p = 19;
	}
	
	public EllipticCurvePoint addNTimes(EllipticCurvePoint p1, EllipticCurvePoint p2, long n) {
		EllipticCurvePoint result = p1;
		while(n > 0) {
			result = add(result, p2);
			n--;
		}
		return result;
	}
	
	public EllipticCurvePoint add(EllipticCurvePoint p1, EllipticCurvePoint p2) {
//		System.out.println("Adding " + p1 + " and " + p2);
		if(p1.equals(p2)) {
			return doublePoint(p1);
		}else if(p1.x == p2.x) {
			return new EllipticCurvePoint(0,0); // vertical line
		}else{
			return distinctAdd(p1, p2);
		}
	}
	
	public EllipticCurvePoint doublePoint(EllipticCurvePoint pt) {
		if(pt.y == 0) return pt;
		long top = mod((3*pt.x*pt.x+a));
		long bottom = mod(2*pt.y);
		long s = mod(top * inverse(bottom)); // 0
		long x = mod(s*s-2*pt.x); 
		long y = mod(s*(pt.x-x)-pt.y);
//		System.out.println("s: " + s + " x: " + x + " y: " + y);
		return new EllipticCurvePoint(x,y);
	}

	public EllipticCurvePoint distinctAdd(EllipticCurvePoint p1, EllipticCurvePoint p2) {
		long top = mod(p1.y-p2.y);
		long bottom = mod(p1.x-p2.x);
		long s = mod(top * inverse(bottom));
		long x = mod((s*s-p1.x-p2.x));
		long y = mod((s*(p1.x-x) - p1.y));
//		System.out.println("s: " + s + " x: " + x + " y: " + y);
		return new EllipticCurvePoint(x,y);
	}
	
	private long mod(long num) {
		return Math.floorMod(num, p);
	}
	
	private long inverse(long num) {
		for(int x = 1; x < p; x++) {
			if((num*x) % p == 1) {
				return x;
			}
		}
		return num;
	}
	
	public String toString() {
		return "a: " + a + ", b: " + b + ", p: " + p;
	}
}
