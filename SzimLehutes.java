import java.util.Random;

public class SzimLehutes {
	public static double Rosenbrock(int n, double[] x) {
		double S = 0;
		for (int i = 0; i < n-1; i++) {
			S = S + (100 * (x[i+1] - x[i] * x[i]) * (x[i+1] - x[i] * x[i]) + (1 - x[i]) * (1 - x[i]));
		}
		return S;
	}
	
	public static double Csokkent(int n, double t, int k) {
		double alpha = 0.8;
		switch(n) {
			case 1: //exponencialis
				return t * Math.pow(alpha, k); 
			case 2: //linearis
				return t / (1 + alpha * k);
			default: //kvadratikus
				return t / (1 + alpha * k * k);
		}
	}

	public static void main(String[] args) {
		Random rand = new Random();
		int k = 0;
		double t0 = 1000000000;
		double t = t0;
		double S;
		int n = 2;
		double[] x = new double[n];
		double[] xLegjobb = new double[n];
		double[] xW = new double[n];
		for (int i = 0; i < n; i++) {
			x[i] = rand.nextDouble() * 100;
		}
		S = Rosenbrock(n, x);
		double Legjobb = S;
		while (t > 1) {
			for (int i = 0; i < n; i++) {
				xW[i] = x[i] + rand.nextDouble() * 2 - 1;
			}
			k++;
			double W = Rosenbrock(n, xW);
			double r = rand.nextDouble();
			if (W < S || r < Math.exp((S - W) / t)) {
				S = W;
				for (int i = 0; i < n; i++) {
					x[i] = xW[i];
				}
			}
			t = Csokkent(2, t0, k);
			if (S < Legjobb) {
				Legjobb = S;
				for (int i = 0; i < n; i++) {
					xLegjobb[i] = x[i];
				}
			}
		}
		System.out.println("Legjobb = " + Legjobb); 
		for (int i = 0; i < n; i++) {
			System.out.print(xLegjobb[i] + " ");
		}
		System.out.println();
	}

}
