import java.util.*;

public class Solution{
	int fav;
	int tx;
	int ty;
	int dist;
	int max;
	Set visited;

	public static void main(String [] args){
		Solution test=new Solution(10);
		test.setTarget(7,4,10);
		test.findRoute(-1,1,1);
		System.out.println("Test dist:"+test.dist);

		Solution part1=new Solution(1362);
		part1.setTarget(31,39,200);
		part1.findRoute(-1,1,1);
		System.out.println("Part1: "+part1.dist);
	}

	void setTarget(int x, int y,int m){
		tx=x;
		ty=y;
		max=m;
		dist=99999;
		visited=new HashSet();
	}

	boolean isWall(int x, int y){
		long num=x*x+3*x+2*x*y+y+y*y+fav;
		//System.out.print(""+x+","+y+" => "+num+" -> ");
		int bits=0;
		for(int i=1;i<num;i*=2){
			if((i&num)!=0) bits++;
			//System.out.print(i&num);
		}
		
		//System.out.println(" "+bits);
		return ((bits&1)==1);
	}

	public Solution (int i){
		fav=i;
	}

	void printGrid(){
		System.out.println("  0123456789");
		for(int y=0;y<10;y++){
			System.out.print(""+y+" ");
			for(int x=0;x<10;x++){
				System.out.print(isWall(x,y)?"#":".");
			}
			System.out.println("");
		}
	}

	// recursive
	void findRoute(int steps, int x, int y){
		if(visited(x,y)) return;
		if(isWall(x,y)) return;
		if(x==tx && y==ty){
			steps--;
			if(steps<dist) dist=steps;
			return;
		}
		if(x<0 || y<0) return;
		if(x>max || y>max) return;
			 
		// if we get here, we must be a valid route
		steps++;
		visit(x,y);
		findRoute(steps,x,y-1);
		findRoute(steps,x-1,y);
		findRoute(steps,x,y+1);
		findRoute(steps,x+1,y);
	}

	void visit (int x, int y){
		visited.add("("+x+","+y+")");
	}

	boolean visited(int x,int y){
		return (visited.contains("("+x+","+y+")"));
	}
}
			
			
