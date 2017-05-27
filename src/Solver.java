
public class Solver {
	
	public static long[] solve(long[] a, long c) {
		return solve(a, c, new long[a.length-1]);
	}

	/**
	 * 	a0*x0 + a1*x1 + . . . + an*xn = c
	 * @param a	Equation's coefficients.
	 * @param c	Equation's right side constant.
	 * @param k	Free of choice values that give different solutions.
	 * @return The array X of the solution, or null if it doesn't exist.
	 */
	public static long[] solve(long[] a, long c, long[] k) {
		if(c  % GcdUtils.gcd(a) != 0) return null;
		if(a.length == 1) return new long[]{c/a[0]};
		long[] x = new long[a.length];
		long[] tmp = GcdUtils.extendedEuclidean(a[0], a[1]);
		if(a.length == 2) {
			x[0] = tmp[0] * (c / tmp[2]) - k[0]*a[1] / tmp[2];
			x[1] = tmp[1] * (c / tmp[2]) + k[0]*a[0] / tmp[2];
			return x;
		}
		long s = solve(a, x , k, c , 2, tmp[2]);
		x[0] = tmp[0]*s - k[0]*a[1] / tmp[2];
		x[1] = tmp[1]*s + k[0]*a[0] / tmp[2];
		return x;
	}
	
	private static long solve(long[] a, long[] x, long[] k, long c, int i, long d) {
		long[] tmp = GcdUtils.extendedEuclidean(d, a[i]);
		if(i==x.length - 1) {
			x[i] = tmp[1]*(c / tmp[2]) + k[i-1]*d / tmp[2];
			return tmp[0] * (c / tmp[2]) - k[i-1]*a[i] / tmp[2];
		}
		long s = solve(a, x, k, c, i+1, tmp[2]);
		x[i] = tmp[1]*s + k[i-1]*d / tmp[2];
		return tmp[0]*s - k[i-1]*a[i] / tmp[2];
	}
	
}
