package prng;

import java.math.BigInteger;

public class LCG {
	BigInteger seed,a,c,p;
	public LCG(BigInteger seed,BigInteger a,BigInteger c,BigInteger p)
	{
		this.seed = seed;
		this.a = a;
		this.c = c;
		this.p = p;
	}
	
	void generate(int N)
	{
		BigInteger x = seed;
		int cnt = 0;
		for(int i=0;i<N;i++)
		{
			x = (a.multiply(x).add(c)).mod(p);
			if(x.equals(BigInteger.valueOf(2)))
				break;
			cnt++;
			System.out.println(x);
		}
		System.out.println("break at = "+cnt);
	}
	
	public static void main(String[] args)
	{
		BigInteger seed = BigInteger.valueOf(2);
		BigInteger a = BigInteger.valueOf(50);
		BigInteger c = BigInteger.valueOf(35);
        BigInteger p = BigInteger.valueOf(1000000007);
        //int t = 2;
		int N = 122332121;
		LCG generator = new LCG(seed, a, c, p);
		
		generator.generate(N);
		

	}

}
