import java.util.Arrays;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		//	4*x1 + 7*x2 + 10*x3 + 7*x4 = 29
		long[] a = {4, 7, 10, 7};
		long c = 29;
		long[] k = {35, 265, 100};
		long[] x = Solver.solve(a, c, k);
		System.out.println("Equation's solution: " +Arrays.toString(x));
		
		int bits = 20;
		System.out.println("\nPossible prime: " + Primes.generatePrime(bits));
		
		long n = 8171;
		System.out.println("\nIs it a prime? " + (Primes.isPrime(n, 100) ? "yes (probably)" : "no"));
		
		n = 3*5*5*7;
		System.out.print("\nThe prime factors of " + n + " are (probably): ");
		Map<Long, Integer> factors = Primes.factorize(n);
		for(Long f : factors.keySet()) {
			System.out.print(f+"^"+factors.get(f)+"  ");
		}
	}

}
