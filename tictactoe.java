package tictactoe;
import java.util.*;

public class lab2 {
	static class placement
	{
		int r, c;
	}
	static char player='x', ai='o', nil='-';
	
	static boolean anyleft(char ttt[][])
	{
		int i, j;
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				if(ttt[i][j]=='-')
					return true;
			}
		}
		return false;
	}
	static int calc(char t[][])
	{
		int i, j;
		//rows calculation
		for(i=0;i<3;i++)
		{
			if(t[i][0]==t[i][1] && t[i][1]==t[i][2])
			{
				if(t[i][0]==player)
					return +10;
				else if(t[i][0]==ai)
					return -10;
			}
		}
		//col. calculation
		for(j=0;j<3;j++)
		{
			if(t[0][j]==t[1][j] && t[1][j]==t[2][j])
			{
				if(t[0][j]==player)
					return +10;
				else if(t[0][j]==ai)
					return -10;
			}
		}
		//diagonal calc.
		if(t[0][0]==t[1][1] && t[1][1]==t[2][2])
		{
			if(t[0][0]==player)
				return +10;
			else if(t[0][0]==ai)
				return -10;
		}
		//diagonal top right to bot. left
		if(t[0][2]==t[1][1] && t[1][1]==t[2][0])
		{
			if(t[0][2]==player)
				return +10;
			else if(t[0][2]==ai)
				return -10;
		}
		return 0;
	}
	static int minmax(char ttt[][], int d, boolean ismax)
	{
		int v,i,j;
		v = calc(ttt);
		if(v==10)
			return v;
		if(v==-10)
			return v;
		if(anyleft(ttt)==false)
			return 0;
		if(ismax)
		{
			int best = -999;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ttt[i][j]=='-')
					{
						ttt[i][j]=player;
						best= Math.max(best, minmax(ttt, d+1, !ismax));
						ttt[i][j]=nil;
					}
				}
			}
			return best;
		}
		else
		{
			int best = -999;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ttt[i][j]=='-')
					{
						ttt[i][j]=ai;
						best= Math.max(best, minmax(ttt, d+1, !ismax));
						ttt[i][j]=nil;
					}
				}
			}
			return best;
		}
	}
	static placement tbm(char ttt[][])
	{
		int bv=-999;
		placement bm=new placement();
		bm.r=-1;
		bm.c=-1;
		int i, j;
		int best = -999;
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				if(ttt[i][j]=='-')
				{
					ttt[i][j]=player;
					int mv =minmax(ttt,0,false);
					ttt[i][j]='-';
					if(mv>bv)
					{
						bm.r=i;
						bm.c=j;
						bv=mv;
					}
				}
			}
		}
		return bm;
	}
	public static void main(String args [])
	{
		char ttt[][]={{ 'x', 'o', '-' }, //row 1
				      { '-', 'o', '-' },  //2
				      { 'x', '-', '-' }}; //3
		placement bm=tbm(ttt);
		System.out.println("row---"+(bm.r+1)+"   "+"col---"+(bm.c+1));
	}
}
