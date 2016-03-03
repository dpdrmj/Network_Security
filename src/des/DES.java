package des;



public class DES {

	final static int ip[] =
		     { 58, 50, 42, 34, 26, 18, 10, 2,
			   60, 52, 44, 36, 28, 20, 12, 4,

			   62, 54, 46, 38, 30, 22, 14, 6,
			   64, 56, 48, 40, 32, 24, 16, 8,
			   57, 49, 41, 33, 25, 17, 9,  1,
			   59, 51, 43, 35, 27, 19, 11, 3,
			   61, 53, 45, 37, 29, 21, 13, 5,
			   63, 55, 47, 39, 31, 23, 15, 7 };

	final static int inverse_ip[] =
		  { 40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41,  9, 49, 17, 57, 25 };

	final static int expansion_permutation[] =
			{  32,  1,  2,  3,  4,  5,
		       4,  5,  6,  7,  8,  9,
		       8,  9, 10, 11, 12, 13,
		       12, 13, 14, 15, 16, 17,
		       16, 17, 18, 19, 20, 21,
		       20, 21, 22, 23, 24, 25,
		       24, 25, 26, 27, 28, 29,
		       28, 29, 30, 31, 32,  1 };

	final static int permutation_function[] =
		  { 16,  7, 20, 21, 29, 12, 28, 17,
            1, 15, 23, 26,  5, 18, 31, 10,
            2,  8, 24, 14,  32, 27,  3,  9,
           19, 13, 30,  6,  22, 11,  4, 25 };

	final static int pc1[] =
		  { 57, 49, 41, 33, 25, 17,  9,
		    1,  58, 50, 42, 34, 26, 18,
		    10, 2,  59, 51, 43, 35, 27,
		    19, 11, 3,  60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7,  62, 54, 46, 38, 30, 22,
            14, 6,  61, 53, 45, 37, 29,
            21, 13, 5,  28, 20, 12,  4 };

	final static int pc2[] =
		  { 14, 17, 11, 24,  1,  5,
            3, 28, 15,  6, 21, 10,
           23, 19, 12,  4, 26,  8,
           16,  7, 27, 20, 13,  2,
           41, 52, 31, 37, 47, 55,
           30, 40, 51, 45, 33, 48,
           44, 49, 39, 56, 34, 53,
           46, 42, 50, 36, 29, 32 };

	final static int sbox[][] = {
			 { 14, 0, 4, 15, 13, 7, 1, 4, 2, 14, 15, 2, 11, 13, 8, 1,
			    3, 10, 10, 6, 6, 12, 12, 11, 5, 9, 9, 5, 0, 3, 7, 8,
			    4, 15, 1, 12, 14, 8, 8, 2, 13, 4, 6, 9, 2, 1, 11, 7,
			   15, 5, 12, 11, 9, 3, 7, 14, 3, 10, 10, 0, 5, 6, 0, 13 },
			 { 15, 3, 1, 13, 8, 4, 14, 7, 6, 15, 11, 2, 3, 8, 4, 14,
			   9, 12, 7, 0, 2, 1, 13, 10, 12, 6, 0, 9, 5, 11, 10, 5,
			   0, 13, 14, 8, 7, 10, 11, 1, 10, 3, 4, 15, 13, 4, 1, 2,
			   5, 11, 8, 6, 12, 7, 6, 12, 9, 0, 3, 5, 2, 14, 15, 9 },
			 { 10, 13, 0, 7, 9, 0, 14, 9, 6, 3, 3, 4, 15, 6, 5, 10,
			   1, 2, 13, 8, 12, 5, 7, 14, 11, 12, 4, 11, 2, 15, 8, 1,
			  13, 1, 6, 10, 4, 13, 9, 0, 8, 6, 15, 9, 3, 8, 0, 7,
			  11, 4, 1, 15, 2, 14, 12, 3, 5, 11, 10, 5, 14, 2, 7, 12 },
			 { 7, 13, 13, 8, 14, 11, 3, 5, 0, 6, 6, 15, 9, 0, 10, 3,
			   1, 4, 2, 7, 8, 2, 5, 12, 11, 1, 12, 10, 4, 14, 15, 9,
			  10, 3, 6, 15, 9, 0, 0, 6, 12, 10, 11, 1, 7, 13, 13, 8,
			  15, 9, 1, 4, 3, 5, 14, 11, 5, 12, 2, 7, 8, 2, 4, 14 },
			 { 2, 14, 12, 11, 4, 2, 1, 12, 7, 4, 10, 7, 11, 13, 6, 1,
			   8, 5, 5, 0, 3, 15, 15, 10, 13, 3, 0, 9, 14, 8, 9, 6,
			   4, 11, 2, 8, 1, 12, 11, 7, 10, 1, 13, 14, 7, 2, 8, 13,
			  15, 6, 9, 15, 12, 0, 5, 9, 6, 10, 3, 4, 0, 5, 14, 3 },
			 { 12, 10, 1, 15, 10, 4, 15, 2, 9, 7, 2, 12, 6, 9, 8, 5,
			   0, 6, 13, 1, 3, 13, 4, 14, 14, 0, 7, 11, 5, 3, 11, 8,
			   9, 4, 14, 3, 15, 2, 5, 12, 2, 9, 8, 5, 12, 15, 3, 10,
			   7, 11, 0, 14, 4, 1, 10, 7, 1, 6, 13, 0, 11, 8, 6, 13 },
			 { 4, 13, 11, 0, 2, 11, 14, 7, 15, 4, 0, 9, 8, 1, 13, 10,
			   3, 14, 12, 3, 9, 5, 7, 12, 5, 2, 10, 15, 6, 8, 1, 6,
			   1, 6, 4, 11, 11, 13, 13, 8, 12, 1, 3, 4, 7, 10, 14, 7,
			   10, 9, 15, 5, 6, 0, 8, 15, 0, 14, 5, 2, 9, 3, 2, 12 },
			 { 13, 1, 2, 15, 8, 13, 4, 8, 6, 10, 15, 3, 11, 7, 1, 4,
			  10, 12, 9, 5, 3, 6, 14, 11, 5, 0, 0, 14, 12, 9, 7, 2,
			   7, 2, 11, 1, 4, 14, 1, 7, 9, 4, 12, 10, 14, 8, 2, 13,
			   0, 15, 6, 12, 10, 9, 13, 0, 15, 3, 3, 5, 5, 6, 8, 11 }
			};

	final static int bitsRotated[] = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	int subkey[][];
	int plainT[];

	public DES(String plainText,String keye)
	{

        StringBuilder tempPlain = new StringBuilder();

		for(int i=0;i<plainText.length();i++)
		{
			char c = plainText.charAt(i);
			if(c>='A'&&c<='Z')
			{
				c = (char)(c-32);
			}
			if(c>='a'&&c<='z')
			{
				tempPlain.append(plainText.charAt(i));
			}

		}

		plainT= new int[tempPlain.length()];

		for(int i=0;i<tempPlain.length();i++)
		{
			plainT[i] = (int)(tempPlain.charAt(i));
		}

		int key[] = permuted_choice1(keye);
		subkey = subkeys(key);
	}

	public int[] permuted_choice1(String key)
	{
		int keye[] = new int[8];

		for(int j=0;j<7;j++)
		{
			int x = 0;
			int t = 1;
			for(int k=0;k<8;k++)
			{
				int toBit = pc1[8*j+k]-1;
				int onPos = toBit%8;
				int toPos = k;

				if(onPos>toPos)
				{
					x = (x|((key.charAt(toBit/8)&(t<<(7-toBit%8)))<<(onPos-toPos)));
				}
				else
				{
					x = (x|((key.charAt(toBit/8)&(t<<(7-toBit%8)))>>(toPos-onPos)));
				}

			}
			 keye[j] = x;
		}
		return keye;
	}


	public int[][] subkeys(int tkey[])
	{
		int key[][] = new int[16][7];
		int rkey[][] = new int[16][6];
		for(int round = 1;round<=16;round++)
		{
			int temp1,temp2;

			if(bitsRotated[round-1]==1)
			{
				temp1 = tkey[0];
				key[round-1][0] = ((tkey[0]<<1)&255)|(tkey[1]>>7);
				key[round-1][1] = ((tkey[1]<<1)&255)|(tkey[2]>>7);
				key[round-1][2] = ((tkey[2]<<1)&255)|(tkey[3]>>7);
				temp2 = tkey[4];
				key[round-1][4] = ((tkey[4]<<1)&255)|(tkey[5]>>7);
			    key[round-1][5] = ((tkey[5]<<1)&255)|(tkey[6]>>7);
			    key[round-1][6] = ((tkey[6]<<1)&255)|((tkey[3]&8)>>3);
			    key[round-1][3] = (((tkey[3]&247)<<1)&255)|((temp1&128)>>3)|(temp2>>7);
			}
			else
			{
				temp1 = tkey[0];
				key[round-1][0] = ((tkey[0]<<2)&255)|(tkey[1]>>6);
				key[round-1][1] = ((tkey[1]<<2)&255)|(tkey[2]>>6);
				key[round-1][2] = ((tkey[2]<<2)&255)|(tkey[3]>>6);
				temp2 = tkey[4];
				key[round-1][4] = ((tkey[4]<<2)&255)|(tkey[5]>>6);
			    key[round-1][5] = ((tkey[5]<<2)&255)|(tkey[6]>>6);
			    key[round-1][6] = ((tkey[6]<<2)&255)|((tkey[3]&12)>>2);
			    key[round-1][3] = (((tkey[3]&243)<<1)&255)|((temp1&192)>>2)|(temp2>>6);
			}

			/*permuted choice 2*/
			for(int j=0;j<6;j++)
			{
				int x = 0;
				int t = 1;
				for(int k=0;k<8;k++)
				{
					int toBit = pc2[8*j+k]-1;
					int onPos = toBit%8;
					int toPos = k;

					if(onPos>toPos)
					{
						x = (x|((key[round-1][toBit/8]&(t<<(7-toBit%8)))<<(onPos-toPos)));
					}
					else
					{
						x = (x|((key[round-1][toBit/8]&(t<<(7-toBit%8)))>>(toPos-onPos)));
					}

				}
				 //key[round-1][j] = x;
				 rkey[round-1][j] = x;
			}

		}
		return rkey;

	}


	public int[] fiestel(int[] plain,int keyx[])
	{
		/* plain[0],plain[1],plain[2],plain[3] */
		/*bit0 bit1 bit2 bit3 bit4 bit5 bit6 bit7*/

		int plain1[] = {plain[0],plain[1],plain[2],plain[3],0,0};
		int tempPlain1[] = new int[plain1.length];
		/*expansion permutation*/
		for(int j=0;j<6;j++)
		{
			int x = 0;
			int t = 1;
			for(int k=0;k<8;k++)
			{
				int toBit = expansion_permutation[8*j+k]-1;
				int onPos = toBit%8;
				int toPos = k;

				if(onPos>toPos)
				{
					x = (x|((plain1[toBit/8]&(t<<(7-toBit%8)))<<(onPos-toPos)));
				}
				else
				{
					x = (x|((plain1[toBit/8]&(t<<(7-toBit%8)))>>(toPos-onPos)));
				}

			}
			 tempPlain1[j] = x;
		}

		plain1 = tempPlain1;


		for(int i=0;i<6;i++)
		{
			plain1[i] = plain1[i]^keyx[i];
		}


		/*Substitution choice s-box*/
		int r,c;

		r = ((plain1[0]&128)>>6)|((plain1[0]&4)>>2);
		c = (plain1[0]&120)>>3;
		plain[0] = sbox[0][r*16+c];

		r =  (plain1[0]&2)|((plain1[1]&16)>>4);
		c = ((plain1[0]&1)<<3)|((plain1[1]&224)>>5);
		plain[0] = (plain[0]<<4)|(sbox[1][r*16+c]);

		r = ((plain1[1]&8)>>3)|((plain1[2]&64)>>6);
		c = ((plain1[1]&7)<<1)|((plain1[2]&128)>>7);
		plain[1] = sbox[2][r*16+c];

		r = ((plain1[2]&32)>>4)|((plain1[2]&1));
		c = ((plain1[2]&30)>>1);
		plain[1] = (plain[1]<<4)|(sbox[3][r*16+c]);

		r = ((plain1[3]&128)>>6)|((plain1[3]&4)>>2);
		c = (plain1[3]&120)>>3;
		plain[2] = sbox[4][r*16+c];

		r =  (plain1[3]&2)|((plain1[4]&16)>>4);
		c = ((plain1[3]&1)<<3)|((plain1[4]&224)>>5);
		plain[2] = (plain[2]<<4)|(sbox[5][r*16+c]);

		r = ((plain1[4]&8)>>2)|((plain1[5]&64)>>6);
		c = ((plain1[4]&7)<<1)|((plain1[5]&128)>>7);
		plain[3] = sbox[6][r*16+c];

		r = ((plain1[5]&32)>>4)|((plain1[5]&1));
		c = ((plain1[5]&30)>>1);
		plain[3] = (plain[3]<<4)|(sbox[7][r*16+c]);

		/*plain[0] plain[1] plain[2] plain[3]  ----> 32 bits*/


		/*permutation p*/
		tempPlain1 = new int[plain.length];
		for(int j=0;j<4;j++)
		{
			int x = 0;
			int t = 1;
			for(int k=0;k<8;k++)
			{
				int toBit = permutation_function[8*j+k]-1;
				int onPos = toBit%8;
				int toPos = k;

				if(onPos>toPos)
				{
					x = (x|((plain[toBit/8]&(t<<(7-toBit%8)))<<(onPos-toPos)));
				}
				else
				{
					x = (x|((plain[toBit/8]&(t<<(7-toBit%8)))>>(toPos-onPos)));
				}

			}
			 tempPlain1[j] = x;
		}
		plain = tempPlain1;
		return plain;

	}

	public int[] encrypt()
	{

		int tempPlain[] = new int[plainT.length];
		/* initial permutation */
		for(int i=0;i<plainT.length;i+=8)
		{
			for(int j=0;j<8;j++)
			{
				int x = 0;
				int t = 1;
				for(int k=0;k<8;k++)
				{
					//System.out.print("char at = "+ (i+(ip[8*j+k]-1)/8));
					//System.out.println("shift = "+ (t<<(7-(ip[8*j+k]-1)%8)));

					int toBit = ip[8*j+k]-1;

					int onPos = toBit%8;
					int toPos = k;

					if(onPos>toPos)
					{

						//System.out.println("val = "+(tempPlain.charAt(i+toBit/8)&(t<<(7-toBit%8))));
						//1System.out.println("left = "+((tempPlain.charAt(i+toBit/8)&(t<<(7-toBit%8)))<<(onPos-toPos)));
						x = (x|((plainT[i+toBit/8]&(t<<(7-toBit%8)))<<(onPos-toPos)));

					}
					else
					{
						/*if(j==1)
						System.out.println("right = "+((plainT[i+toBit/8]&(t<<(7-toBit%8)))>>(onPos-toPos)));
						System.out.println("right x = "+(x|((plainT[i+toBit/8]&(t<<(7-toBit%8)))>>(onPos-toPos))));*/
						//int orwith = ;

						x = x|((plainT[i+toBit/8]&(t<<(7-toBit%8)))>>(toPos-onPos));
					}
					//System.out.println("x = "+x);

				}
				 tempPlain[i+j] = x;
				// System.out.println(" = " + x);
			}

			//plain[i] = tempPlain.charAt(ip[i]);
		}

		plainT = tempPlain;

		for(int i=0;i<plainT.length;i+=8)
		{
			int left[] = {plainT[i],plainT[i+1],plainT[i+2],plainT[i+3]};
			int right[] = {plainT[i+4],plainT[i+5],plainT[i+6],plainT[i+7]};

			for(int round = 1;round<=16;round++)
			{
				int temp[] = {right[0],right[1],right[2],right[3]};

				right = fiestel(right, subkey[round-1]);


				for (int k = 0; k < right.length; k++)
				{
					right[k] = right[k]^left[k];
					left[k] = temp[k];
				}

				if(round==16)
				{
					for (int k = 0; k < 8; k++)
					{
						//System.out.println("i = "+i + "   k = "+ k);
						//System.out.println(plain[i+k]);
						if(k<4)
						plainT[i+k] = right[k];

						else
						plainT[i+k] = left[k-4];

					}

					//inverse_ip
					tempPlain = new int[plainT.length];
					for(int j=0;j<8;j++)
					{
						int x = 0;
						int t = 1;
						for(int k=0;k<8;k++)
						{
							int toBit = inverse_ip[8*j+k]-1;
							int onPos = toBit%8;
							int toPos = k;

							if(onPos>toPos)
							{
								x = (x|((plainT[i+toBit/8]&(t<<(7-toBit%8)))<<(onPos-toPos)));
							}
							else
							{
								x = (x|((plainT[i+toBit/8]&(t<<(7-toBit%8)))>>(toPos-onPos)));
							}

						}
						 tempPlain[i+j] = x;
					}
					plainT = tempPlain;

				}

			}

		}

		return plainT;
	}

	public int[] decrypt(int cipherT[])
	{
		//System.out.println("decipher");
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<6;j++)
			{
				int temp = subkey[i][j];
				subkey[i][j] = subkey[15-i][j];
				subkey[15-i][j] = temp;

			}

		}
		plainT = new int[cipherT.length];
		for(int i=0;i<cipherT.length;i++)
		plainT[i] = cipherT[i];

		cipherT = encrypt();




		return cipherT;
	}

	public static void main(String[] args)
	{
		    //char t = 132;
			String plainText = "abcdefgh";
			String key = "nkjiufdr";

			DES x = new DES(plainText,key);
			int cipher[] = x.encrypt();
            char hexa[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
			System.out.print("0x");
			for(int i=0;i<cipher.length;i++)
			{
			    int c1 = cipher[i]%16;
			    int c2 = (cipher[i]/16);
				System.out.print(hexa[c2]+""+hexa[c1]);
			}
			int decipher[] = x.decrypt(cipher);


			System.out.println("\ndecrypt");

			for(int i=0;i<decipher.length;i++)
			{
				System.out.print((char)(decipher[i]));
			}
	}

}
