import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Euler35CircularPrimes {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		//Iterates through integers less than one million and uses the methods below
		//to determine if each integer is a circular prime. It begins at 2-digit numbers
		//and counts the 4 single-digit circular primes beforehand.
		
		int n = 11;
		int total = 4;
		while (n < 1000000)
		{
			if (isCircular(n))
			{
				total++;
			}
			n = getNextPrime(n);
		}
		
		System.out.println("Total of circular primes below one million: " + total);
		
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("\nExecution time is " + formatter.format((end - start) / 1000d) + " seconds.");
	}
	
		//Determines if integer n is prime. 
		public static boolean isPrime(int n)
		{
			if (n < 2) 
			    return false;
			if (n == 2 || n == 3) 
			    return true;
			if (n % 2 == 0 || n % 3 == 0) 
			    return false;
			long sqrtN = (long)Math.sqrt(n) + 1;
			for (long i = 6L; i <= sqrtN; i += 6) 
			{
			    if (n % (i - 1) == 0 || n % (i + 1) == 0) 
			        return false;
			}
			return true;
		}
		
		//Returns next prime number after integer n. 
		public static int getNextPrime(int n)
		{
			boolean prime = false;
			while (!prime)
			{
				n += 2;
				prime = isPrime(n);
			}
			return n;
		}
		
		//Arithmetically rotates digits of integer n from back to front. 
		public static int rotate(int n, int length)
		{
			int rightDigit = n % 10;
			int temp = (n - rightDigit) / 10;
			return (rightDigit * (int)(Math.pow(10, length - 1))) + temp;	
		}
		
		//Determines if integer n is a circular prime.
		public static boolean isCircular(int n)
		{
			boolean result = true;
			int length = Integer.toString(n).length();
			for (int i = 0; i < length - 1; i++)
			{
				int temp = rotate(n, length);
				n = temp;
				if (isPrime(n) == false)
				{
					result = false;
					break;
				}
			}
			return result;
		}
}
