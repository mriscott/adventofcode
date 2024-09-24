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
		setDebug(false);
		test(467835,test.findGears());
		System.out.println("Part 2: "+inp.findGears());
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
		if(y<0) return false;
		if(y>=grid.length) return false;
		if(x<0) return false;
		if(x>=grid[y].length) return false;
		char ch = grid[y][x];
		return (ch>='0' && ch <='9');
	}
	 int numAt(int y,int x){
		 String strNum="";
		 while(isDigit(y,x-1)) x--;
		 while (isDigit(y,x)) {
			 strNum=strNum+grid[y][x];
			 x++;
		 }
		 int iNum=-1;
		 try{
			 iNum=Integer.parseInt(strNum);
		 }
		 catch(NumberFormatException e){
			 e.printStackTrace();
			 throw new RuntimeException (e);
		 }
		 debug("Num at "+y+","+x+"="+iNum);
		 return iNum;
			 
		 
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

	int findGears(){
		int gearTot=0;
		int num1=0;
		int num2=0;
		for(int y=0;y<sch.size();y++){
			GRID:for(int x=0;x<grid[y].length;x++){
				if(grid[y][x]=='*'){
					debug("Checking gear "+y+","+x);
					num1=-1;
					num2=-1;
					for(int i=-1;i<2;i++){
						for(int j=-1;j<2;j++){
							if(isDigit(y+i,x+j)){
								int num=numAt(y+i,x+j);
								debug("Adjacent num :"+num);
								if(num1==-1) num1=num;
								else{
									if(num2!=-1){
										debug("Too many adjacent numbers:"+num);
										continue GRID;
									}	
									num2=num;
								}
								while(isDigit(y+i,x+j)) j++;
							}
						}
					}
					if(num2!=-1){
						debug("Gear:"+num1+"*"+num2);
						gearTot+=(num1*num2);
					}
				}
			}
				
		}
		return gearTot;
			
	}
	
  
	void process (String line){
		sch.add(line);
		if(rowlen<line.length()) rowlen=line.length();
	}


}
