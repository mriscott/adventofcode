import java.util.*;

public class Solution{
	int fav;
	int tx;
	int ty;
	 
	int dist;
	int max;
	Set everVisited;

	public static void main(String [] args){
		Solution test=new Solution(10);
		test.runTests();

		int max=50;
		Solution part1=new Solution(1362);
		part1.setTarget(31,39,max);
		System.out.println("Part1: "+part1.dist);

		Solution part2=new Solution(1362);

		System.out.println("Part2: "+part2.findAllTargets(48));


	}

	void runTests(){
			//		printGrid();
		int max=10;
		assertDist(1,1,max,0);
		assertDist(0,1,max,1);
		assertDist(0,0,max,2);
		assertDist(2,2,max,2);
		assertDist(3,1,max,4);
		assertDist(7,4,max,11);
		printGrid();
		assertTargets(0,1);
		assertTargets(1,3);
		assertTargets(2,7);
		assertTargets(3,8);
		assertTargets(4,11);
	}

	void assertTargets(int max, int exp){
		int got=findAllTargets(max);
		if(got!=exp){
			for(Object o:everVisited){
				System.out.println(o.toString());
			}
			throw new IllegalArgumentException("Max:"+max+" expected "+exp+", got "+got);
		}
		System.out.println("OK: "+max+" => "+exp);
		
	}

	void assertDist(int x,int y,int m, int e){
		setTarget(x,y,m);
		if(dist!=e){
			throw new IllegalArgumentException("Expected "+e+" to ("+x+","+y+") but got "+dist);
		}
		System.out.println("OK : (1,1) -> ("+x+","+y+") = "+e);
			
	}

	int findAllTargets(int m){
		tx=-1;
		ty=-1;
		max=m;
		dist=0000;
		everVisited=new HashSet();
		findRoute();
		return everVisited.size();
		
	}

	void setTarget(int x, int y,int m){
		tx=x;
		ty=y;
		max=m;
		dist=99999;
		everVisited=new HashSet();
		if(!isWall(x,y))		findRoute();
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

	void findRoute(){
		findRoute(0,1,1,new StringBuffer());
	}

	
	// recursive
	boolean findRoute(int steps, int x, int y,StringBuffer route){
		if(visited(x,y,route)) return true;
		if(isWall(x,y)) return true;
		if(x<0 || y<0) return true;
		if(max>2 && (x>max || y>max)) return true;
		route.append("("+x+","+y+"),");
		//System.out.println("Checking "+route);
		if(x==tx && y==ty){
			//steps--;
			if(steps<dist) dist=steps;
			route.append("="+steps);
			//System.out.println(route);
			return true;
		}
			 
		// if we get here, we must be a valid (non-terminal) route
		everVisited.add("("+x+","+y+")");

		// for finding all locations
		if(tx==-1 && ty==-1 && steps>=max) {
				//System.out.println(route.toString()+" - "+steps);
				return true;
		}
		steps++;
	 
		
		
		findRoute(steps,x,y-1,new StringBuffer(route));
		findRoute(steps,x-1,y,new StringBuffer(route));
		findRoute(steps,x,y+1,new StringBuffer(route));
		findRoute(steps,x+1,y,new StringBuffer(route));

		return false;
	}


	boolean visited(int x,int y,StringBuffer route){
		if(route==null) return false;
		return (route.toString().indexOf("("+x+","+y+")")!=-1);
	}
}
			
			
