import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Primes {
	/**
	 * Generates a random prime with k bits using Miller Rabin algorithm.
	 * @param k Number of bits.
	 */
	public static long generatePrime(int k) {
		long low = 1 << (k-1);
		long high = 2*low;
		long n;
		do{
			n = ThreadLocalRandom.current().nextLong(low+1, high);
		}while(!isPrime(n, 100));
		return n;
	}

	/**
	 * Calculates all possible prime factors of a number using Pollard's ñ algorithm.
	 * @return Prime factors in the form of: prime -> exponent. 
	 * <br>For example with input 75 = 3*5^2 returns the map
	 * <br> 3 -> 1
	 * <br> 5 -> 2
	 */
	public static Map<Long, Integer> factorize(long n) {
		Map<Long, Integer> factors = new HashMap<>();
		while(n % 2 == 0) {
			Integer old = factors.get(2L);
			factors.put(2L, old == null ? 1 : old+1);
			n >>>=1;
		}
		//twra o n einai perittos
		if(n != 1) factorize(factors, n);
		return factors;
	}
	
	private static void factorize(Map<Long, Integer> factors, long n) {
		// find a,b : n = a*b
		long a = getDivisor(n);
		long b = n / a;
		if(a == n) { //n katalh3e prwtos
			Integer old = factors.get(n);
			factors.put(n, old == null ? 1 : old+1);
			return;
		}
		factorize(factors, a);
		factorize(factors, b);
	}

	private static long getDivisor(long n) {
		if(isPrime(n, 100)) return n;
		long x = ThreadLocalRandom.current().nextLong(1, n); /// [1 , n-1]
		long y = x;
		long d = 1;
		int count = 0;
		
		while(d == 1) {
			x = f(x, n);
			y = f(f(y, n), n);
			d = Math.abs(GcdUtils.gcd(x - y, n));
			
			//epile3e kalytero shmeio ekinhshs ka8e toso
			if(count++ % 500 == 0) {
				y = x = ThreadLocalRandom.current().nextLong(1, n);
			}
		}
		return d;
	}
	
	//synarthsh metavashs
	private static long f(long x, long n) {
		return (x*x + 1) % n;
	}
	
	/**
	 * 	Tests if the number is a prime using Miller Rabin algorithm.
	 * 	A result of false guarantee that the number is non prime.
	 * 	A result of true indicates a high possibility that the number is prime.
	 * 	Fake prime probability is (1/4)^t
	 * @param t The numbers of tests.
	 */
	public static boolean isPrime(long n, int t) {
		if (n == 2) return true;
		if (n % 2 == 0) return false;

		// h, m : n-1=2^h *m
		int h = 0;
		long m = n - 1;
		while (m % 2 == 0) {
			h++;
			m >>>= 1;
		}

		for (int i = 1; i <= t; i++) {
			long a = ThreadLocalRandom.current().nextLong(2, n); // [2 , n-1]
			if (GcdUtils.gcd(a, n) > 1) return false;
			long b = modPow(a, m, n);
			if (b == 1) continue;
			for (int j = 1; j <= h; j++) {
				if (b == n - 1) break;
				if (b == 1) return false;
				b = (b * b) % n;
			}
		}
		return true;
	}

	public static long modPow(long x, long k, long n) {
		long a = 1;
		while(k != 0) {
			if(k % 2 == 1) {
				a = (a*x)%n;
			}
			x = (x*x)%n;
			k >>>= 1;
		}
		return a;
	}

}
