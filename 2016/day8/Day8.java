import java.io.*;
public class Day8
{
	int [][] area;
	int maxx,maxy;

	public Day8(int x, int y){
		maxx=x;
		maxy=y;
		area=new int[x][y];
	}

	void printArea(){
		for (int y=0;y<maxy;y++){
			for (int x=0;x<maxx;x++){
				System.out.print(area[x][y]==1?'#':".");
			}
			System.out.println("");
		}
		System.out.println("On:"+countOn());
		System.out.println("");
	}
	public String read(String filename) throws Exception {
		System.out.println("Reading "+filename);
		LineNumberReader fr=new LineNumberReader(new FileReader(new File(filename)));
		String line=fr.readLine();
		String output="";
		while(line!=null){
			op(line);
			line=fr.readLine();
		}
		fr.close();
		return output;
	}

	public static void main(String [] args){
		try{
			Day8 test=new Day8(8,3);
			test.runTest();
			Day8 test2=new Day8(8,3);
			test2.read("test");
			if(test2.countOn()!=6) {
				throw new RuntimeException("Test failed");
			}
			System.out.println("All tests passed");
			Day8 real=new Day8(50,6);
			real.read("input");
			System.out.println("Part 1: "+real.countOn());	
			real.printArea();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	void runTest() throws Exception{
			op("rect 3x2");
			printArea();
			op("rotate column x=1 by 1");
			printArea();
			op("rotate row y=0 by 4");
			printArea();
			op("rotate column x=1 by 1");
			printArea();
	}

	void op(String op) throws Exception{
		boolean ret=false;
		if(op.startsWith("rect")){
			int ix=op.indexOf("x");
			int x=Integer.parseInt(op.substring(5,ix));
			int y=Integer.parseInt(op.substring(ix+1));
			rect(x,y);
			ret=true;
		}
		if(op.startsWith("rotate column")){
			int ie=op.indexOf("x=");
			int iby=op.indexOf(" by ");
			int x=Integer.parseInt(op.substring((ie+2),iby));
			int by=Integer.parseInt(op.substring(iby+4));
			rotateCol(x,by);
			ret=true;
			
		}
		if(op.startsWith("rotate row")){
			int ie=op.indexOf("y=");
			int iby=op.indexOf(" by ");
			int y=Integer.parseInt(op.substring(ie+2,iby));
			int by=Integer.parseInt(op.substring(iby+4));

			rotateRow(y,by);
			ret=true;
		}
		if (!ret){
			throw new RuntimeException("Invalid op:"+op);
		}
			
	}

	void rect(int xx,int yy){
		for (int y=0;y<yy;y++){
			for (int x=0;x<xx;x++){
				area[x][y]=1;
			}
		}
	}
	int  countOn(){
		int c=0;
		for (int y=0;y<maxy;y++){
			for (int x=0;x<maxx;x++){
				c+=area[x][y];
			}
		}
		return c;
	}

	void rotateRow(int y,int by){
		System.out.println("rotate row y="+y+" by "+by);
		int [] old =new int[maxx];
		for(int i=0;i<maxx;i++){
			old[i]=area[i][y];
		}
		for(int i=0;i<maxx;i++){
			int t=i-by;
			while(t<0) t+=maxx;
			t=t%maxx;
			area[i][y]=old[t];
		}
	}
	void rotateCol(int x, int by){
		System.out.println("rotate col x="+x+" by "+by);
		int [] old =new int[maxy];
		for(int i=0;i<maxy;i++){
			old[i]=area[x][i];
		}

		for(int i=0;i<maxy;i++){
			int t=i-by;
			while(t<0) t+=maxy;
			t=t%maxy;
			area[x][i]=old[t];
		}
	}	

}
