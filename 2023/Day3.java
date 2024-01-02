import java.util.*;

public class Day3 extends Utils{
	List sch=new ArrayList();
	char [][] grid;
	int part=1;
	int rowlen=0;
	int partsum=0;
	public static void main(String [] args){
		setDebug(false);
		Day3 test=new Day3("day3/test");
		test(4361,test.partsum);
		Day3 inp=new Day3("day3/input.3");
		System.out.println("Part 1: "+inp.partsum);
		
	}
	public Day3(String file){
		this(file,1);
	}
	public Day3 (String file, int part){
		this.part=part;
		safeRead(file);
		processSchematic();
	}

	boolean isDigit(int y,int x){
		if(x>=grid[y].length) return false;
		char ch = grid[y][x];
		return (ch>='0' && ch <='9');
	}

	boolean isSymbol(int y,int x){
		if(y>=sch.size() || y<0) return false;	
		if(x>=grid[y].length || x<0) return false;
		if(isDigit(y,x)) return false;
		char ch = grid[y][x];
		return (ch!='.');
	}

	void processSchematic(){
		grid=new char[sch.size()][];
		for (int x=0;x<sch.size();x++){
			char [] row=((String)sch.get(x)).toCharArray();
			grid[x]=row;
		}
		for(int y=0;y<sch.size();y++){
			for(int x=0;x<grid[y].length;x++){
				if(isDigit(y,x)){
						int numstart=x;
						String num="";
						num+=grid[y][x];
						while(isDigit(y,x+1)){
							x++;
							num+=grid[y][x];
						}
						int numend=x;
						boolean ispart=false;
						for(int r=y-1;r<=y+1;r++){
							for(int i=numstart-1;i<=numend+1;i++){
								if(isSymbol(r,i)){
									ispart=true;
									break;
								}
							}
						}
						debug (num+" - "+ispart);
						if(ispart){
							partsum+=Integer.parseInt(num);
						}
						
				}
			}
		}
	}
	
  
	void process (String line){
		sch.add(line);
		if(rowlen<line.length()) rowlen=line.length();
	}


}
