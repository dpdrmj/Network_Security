package classicalCiphers;


class Hill implements CipherAlgorithm {
	
	int K[][] = {{17,17,5},{21,18,21},{2,2,19}};
	int SIZE = 3;
	
	Hill()
	{
		
	}
	
	Hill(String key,int n)
	{
		this.SIZE = n;
		K = new int[n][n];
		StringBuilder key1 = new StringBuilder(key);
		while(key.length()%n!=0)
			key1.append("X");
		key = key1.toString();
		////System.out.println(key);
		int i1=0,j1=0;
		
		////System.out.println("det"+t);
		for(int i=0;i<key.length();i++)
		{
			////System.out.println(i);
			K[i1][j1] = key.charAt(i)-'A';
			if(j1==n-1)
			{
				i1++;j1=0;
			}
			else
			{
				j1++;
			}
			
		}
		
	/*	for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				this.K[i][j] = key.charAt(i*n+j)-'A';
			}
		}*/
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				//System.out.print(K[i][j]+"  ");
			}
			//System.out.println();
		}
		
	}
	
	
	@Override
	public String encrypt(String plainText) {
		
		
		plainText = plainText.toUpperCase().replace(" ", "");
		StringBuilder plainText1 = new StringBuilder();
		StringBuilder cipherText = new StringBuilder();
		for(int i=0;i<plainText.length();i++)
		{
			char c = plainText.charAt(i);
			
			if(c>='A'&& c<='Z')
			{
				plainText1.append(c);
			}
		}
		
		if(plainText1.length()%SIZE!=0)
		{
			while(plainText1.length()%SIZE!=0)
			{
				plainText1.append('X');
			}
		}
		
	
		
		for(int i=0;i<plainText1.length();i=i+SIZE)
		{
			int b[] = new int[SIZE];
			
			for(int j=0;j<SIZE;j++)
			{
				b[j] = plainText1.charAt(i+j)-'A';
				
			}
				
			
			for(int j=0;j<SIZE;j++)
			{
				int sum = 0;
				for(int k=0;k<SIZE;k++)
				{
					sum = (sum+b[k]*K[k][j]);
				}
				
				sum = sum%26;
				
				cipherText.append((char)(sum+'A'));
			}
			
		}
		
	    return cipherText.toString();
	}

	@Override
	public String decrypt(String cipherText) 
	{
		int invK[][] = inverse(K, SIZE);
		//System.out.println("inverse");
		for(int i=0;i<SIZE;i++)
		{
			for(int j=0;j<SIZE;j++)
			{
				//System.out.print(invK[i][j]+" ");
			}
			//System.out.println();
		}
		
		StringBuilder plainText = new StringBuilder();
		StringBuilder cipherText1 = new StringBuilder(cipherText);
		
		if(cipherText1.length()%SIZE!=0)
		{
			while(cipherText1.length()%SIZE!=0)
			{
				cipherText1.append('X');
			}
			cipherText = cipherText1.toString();
		}
		
		
		for(int i=0;i<cipherText.length();i=i+SIZE)
		{
			int b[] = new int[SIZE];
			
			for(int j=0;j<SIZE;j++)
			{
				b[j] = cipherText.charAt(i+j)-'A';
				
			}
				
			
			for(int j=0;j<SIZE;j++)
			{
				int sum = 0;
				for(int k=0;k<SIZE;k++)
				{
					sum = (sum+b[k]*invK[k][j]);
				}
				
				sum = sum%26;
				
				plainText.append((char)(sum+'A'));
			}
			
		}
		
		return plainText.toString().toLowerCase();
	}
	
	public int det(int A[][],int n)
	{
		int ans = 0;
		
		if(n==1)
			return A[0][0];
		else if(n==2)
		{
			return A[0][0]*A[1][1]-A[0][1]*A[1][0];
		}
		
		else
		{
			for(int i=0;i<n;i++)
			{
				int b[][] = new int[n-1][n-1];
				for(int j=1;j<n;j++)
				{
					int k1=0;
					for(int k=0;k<n;k++)
					{
						if(k==i)
							continue;
						b[j-1][k1++] = A[j][k];
					}
				}
				
				ans = (int) (ans + Math.pow(-1, i)*A[0][i]*det(b,n-1));
				
			}
		}
		//System.out.println("determinant = "+ans);
		return ans;
		
	}
	
	public int[][] inverse(int A[][],int n)
	{
		int b[][] = new int[n][n];
		
		int det1 = getInverse(getMod(det(A, n)));
		if(det1==-1)
		{
			//System.out.println("not possible");
		}
		//System.out.println("getit = "+getInverse(12));
		//System.out.println("det1 = "+det1);
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				int i1=0,j1=0;
				int c[][] = new int[n-1][n-1];
				for(int x=0;x<n;x++)
				{
					for(int y=0;y<n;y++)
					{
						if(x!=i&&y!=j)
						{
							c[i1][j1] = A[x][y];
							////System.out.print(c[i1][j1]+" "); 
							j1++;
							if(i1==n-1)
								j1=0;
							if(j1==n-1)
							{
								i1++;j1=0;
								////System.out.println();
							}
						}
					}
				}
				////System.out.println((int)(Math.pow(-1, i+j)*det(c, n-1)));
				b[j][i] = getMod(det1*getMod((int)(Math.pow(-1, i+j)*det(c, n-1))));
				
			}
			
		}
		
		return b;
	}
	
	public int getInverse(int a)
	{
		
		for(int i=1;i<=25;i++)
		{
			if(i*a%26==1)
				return i; 
		}
		
		return -1;
	}
	
	public int getMod(int a)
	{
		int b = a%26;
		
		return b<0?b+26:b;

	}

}
