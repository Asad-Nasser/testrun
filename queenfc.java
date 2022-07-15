package queenfc;
import java.util.*;

public class queenfc {
	static boolean safe(int[][] chess, int r, int c)
	{
		int i, j;
		for(i=0;i<r;i++) //checking columns
		{
			if(chess[i][c]==1)
			{
				return false;
			}
		}
		for(i=r,j=c;i>=0 && j>=0;i--,j--) //diagonal towards (0,0)
		{
			if(chess[i][j]==1)
			{
				return false;
			}
		}
		for(i=r,j=c;i>=0 && j<8;i--,j++) //diagonal towards (0,8)
		{
			if(chess[i][j]==1)
			{
				return false;
			}
		}
		return true;
	}
	static void solve(int[][] chess, int r)
	{
		if(r==chess.length)
		{
			display(chess);
		}
		int i;
		for(i=0;i<chess.length;i++)
		{
			if(safe(chess,r,i))
			{
				chess[r][i]=1;
				solve(chess, r+1);
				chess[r][i]=0;
			}
		}
	}
	static void display(int chess[][])
	{
		for (int[] row : chess)
			System.out.println(Arrays.toString(row));
		System.out.println("   ");
		System.out.println("--x--x--x--");
		int n=1;
		System.out.println(n); n++;
	}
	public static void main(String args [])
	{
		int i,j;
		int[][] chess=new int[8][8];
		for(i=0;i<8;i++)
		{
			for(j=0;j<8;j++)
			{
				if(i!=0 && j!=0)
					chess[i][j]=0;
			}
		}
		solve(chess,0);
	}
	
}
