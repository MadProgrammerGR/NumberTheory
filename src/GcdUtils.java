
public class GcdUtils {

	public static long gcd(long... a) {
		if(a.length == 1) return a[0];
		long res = gcd(a[0], a[1]);
		for (int i = 2; i < a.length; i++) {
			res = gcd(res, a[i]);
		}
		return res;
	}

	private static long gcd(long a, long b) {
		long r0 = Math.max(a, b);
		long r1 = Math.min(a, b);
		while (r1 != 0) {
			long r2 = r0 % r1;
			r0 = r1;
			r1 = r2;
		}
		return r0;
	}

	/**
	 * @return  3-element array [ s , t, d] such that: sa + tb = d = gcd(a,b) 
	 */ 
	public static long[] extendedEuclidean(long a, long b) {
		long s0, s1, s2, t0, t1, t2;
		s1 = t0 = s2 = 0;
		s0 = t1 = t2 = 1;
		// s2 = 0 kai t2 = 1 gia th periptwsh pou ta a=kb dld den ekteleitai to prwto loop
		
		long r0 = Math.max(a, b);
		long r1 = Math.min(a, b);
		long r2;
		while ((r2 = r0 % r1) != 0) { //stamataei ena loop prin to gcd
			s2 = s0 - s1 * (r0 / r1);
			s0 = s1;
			s1 = s2;

			t2 = t0 - t1 * (r0 / r1);
			t0 = t1;
			t1 = t2;

			r0 = r1;
			r1 = r2;
		}
		//allazw th seira ama a < b
		long[] res = new long[3];
		res[0] = (a > b) ? s2 : t2;
		res[1] = (a > b) ? t2 : s2;
		res[2] = r1; //r1 = gcd(a,b)
		return res;
	}
	
}
