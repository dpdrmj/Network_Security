package rng;

import java.util.Random;

/*take n groups of 5 consecutive integers and put them in seven categories*/
/*i'm taking 96 group of 5 integers*/
public class PokerTest {

	/*
	 Seven categories
	 All different: abcde
	 One pair: aabcd
	 two pairs: aabbc
	 three of a kind: aaabc
	 full house: aaabb
	 four of a kind: aaaab
	 five of a kind: aaaaa 
	 * */
	static int y[] = new int[7];
	static int numbers[] = new int[10000];
	static int n = 9996; //number of observations	
	static double p = 1.0/n; // uniform probability
	static double np = n*p;
	public static void main(String[] args)
	{
		Random rand = new Random();
		
		for(int i=0;i<100;i++)
		{
			numbers[i] = rand.nextInt(100)+1;
			System.out.println(i + " "+ numbers[i]);
		}
		
		for(int i=0;i<7;i++)
		{
			y[i] = 0;
		}
		
		for(int i=0;i<n;i++)
		{
			int count[] = new int[5];
			
			for(int j=0;j<5;j++)
			{   count[j]=1;
				for(int k=0;k<5;k++)
				{
					if(j!=k)
					{
						if(numbers[i+j]==numbers[i+k])
						{
							count[j]++;
						}
					}
				}
			}
			
			if(count[0]==5)
			{
				y[6]++;
			}
			
			else if(count[0]==4||count[1]==4)
			{
				y[5]++;
			}
			
			else if(count[0]==3||count[1]==3||count[2]==3)
			{
				int t=0;
				for(int j=0;j<5;j++)
				{
					if(count[j]==2)
					{
						t =1;
						y[4]++;break;
					}
				}
				if(t==0)
				{
					y[3]++;
				}
				
			}
			
			else if(count[0]==1&&count[1]==1&&count[2]==1&&count[4]==1)
			{
				y[0]++;
			}
			else
			{
				int k=0;
				for(int j=0;j<5;j++)
				{
					if(count[j]==2)
						k++;
				}
				if(k==4)
				{
					y[2]++;
				}
				if(k==2)
				{
					y[1]++;
					System.out.println("i = "+i);
				}
				
			}
	
		}
		
		double v = 0;
		
		for(int i=0;i<7;i++)
		{
			System.out.println("y["+i+"] = "+y[i]);
			v = v + (y[i]-np)*(y[i]-np)/(np);
			System.out.println("v = "+ v);
		}
		System.out.println(v);
		
		

	}

}
