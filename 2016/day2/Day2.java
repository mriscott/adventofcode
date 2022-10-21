import java.io.*;

public class Day2{
	int [][] arr={{1,2,3},{4,5,6},{7,8,9}};
	int x=1;
	int y=1;

	public static void main(String [] args){
		try{
			Day2 test=new Day2();
			System.out.println("Test:"+test.test());
			Day2 test2=new Day2();
			System.out.println("Test2:"+test.testRead());
			Day2 real=new Day2();
			System.out.println("Answer:"+real.read(args.length>0?args[0]:"input"));
		}catch(IOException e){
			e.printStackTrace();
		}
	

	}
	
	public boolean test()
	{
		int code=0;
		processLine("ULL");
		code+=(getNum()*1000);
		processLine("RRDDD");
		code+=(getNum()*100);
		processLine("LURDL");
		code+=(getNum()*10);
		processLine("UUUUD");
		code+=(getNum());
		return(code==1985); 
		
	}

	public boolean testRead() throws IOException{
		String answer=read("test");
		return(answer.equals("1985"));
	}

	public String read(String filename) throws IOException {
		System.out.println("Reading "+filename);
		LineNumberReader fr=new LineNumberReader(new FileReader(new File(filename)));
		String line=fr.readLine();
		String output="";
		while(line!=null){
			processLine(line);
			output+=getNum();
			line=fr.readLine();
		}
		fr.close();
		return output;
	}

	

	public Day2(){
	}

	public int getNum(){
		return arr[y][x];
	}

	void move(char c){
		switch (c){
			case 'U':
				y--;
				if (y<0)  y=0;
				break;
			case 'D':
				y++;
				if (y>2) y=2;
				break;
			case 'L':
				x--;
				if(x<0) x=0;
				break;
			case 'R':
				x++;
				if(x>2) x=2;
				break;
		}
	}

	public void processLine(String line){
		for(int i=0;i<line.length();i++){
			move(line.charAt(i));
		}
	}

}
