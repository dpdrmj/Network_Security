package prng;

import java.math.BigInteger;

public class BBS {

	BigInteger s,n;
	BBS(BigInteger p,BigInteger q,BigInteger seed)
	{
		n = p.multiply(q);
		this.s = seed;
	}
	
	void generate(int N)
	{
		BigInteger x = (s.multiply(s)).mod(n);
		BigInteger t = s; 
		int cnt = 0;
		for(int i=1;i<=N;i++)
		{
			x = x.multiply(x).mod(n);
			if(x.equals(t))
				break;
			cnt++;
			System.out.println(x.mod(BigInteger.valueOf(2)));
		}
		System.out.println("break at: "+cnt);
	}
	
	public static void main(String[] args)
	{
		BigInteger p = BigInteger.valueOf(100000007);
		BigInteger q = BigInteger.valueOf(100000009);
		BigInteger seed = BigInteger.valueOf(101355);
		
		BBS generator = new BBS(p, q, seed);
		generator.generate(32321);
	}

}
