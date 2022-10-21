import java.io.*;

public class Day2Part2{
	char [][] arr={{'0','0','1','0','0'},{'0','2','3','4','0'},{'5','6','7','8','9'},{'0','A','B','C','0'},{'0','0','D','0','0'}};
	int x=0;
	int y=2;

	public static void main(String [] args){
		try{
			Day2Part2 test=new Day2Part2();
			System.out.println("Test:"+test.test());
			Day2Part2 test2=new Day2Part2();
			System.out.println("Test2:"+test.testRead());
			Day2Part2 real=new Day2Part2();
			System.out.println("Answer:"+real.read(args.length>0?args[0]:"input"));
		}catch(IOException e){
			e.printStackTrace();
		}
	

	}
	
	public boolean test()
	{
		String code="";
		processLine("ULL");
		code+=(getNum());
		processLine("RRDDD");
		code+=(getNum());
		processLine("LURDL");
		code+=(getNum());
		processLine("UUUUD");
		code+=(getNum());
		return(code.equals("5DB3")); 
		
	}

	public boolean testRead() throws IOException{
		String answer=read("test");
		System.out.println(answer);
		return(answer.equals("5DB3"));
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

	

	public Day2Part2(){
	}

	public char getNum(){
		return arr[y][x];
	}

	void move(char c){
		switch (c){
			case 'U':
				y--;
				if (y<0 || getNum()=='0')  y++;
				break;
			case 'D':
				y++;
				if (y>4 || getNum()=='0') y--;
				break;
			case 'L':
				x--;
				if(x<0 || getNum()=='0') x++;
				break;
			case 'R':
				x++;
				if(x>4 ||getNum()=='0') x--;
				break;
		}
	}

	public void processLine(String line){
		for(int i=0;i<line.length();i++){
			move(line.charAt(i));
		}
	}

}
