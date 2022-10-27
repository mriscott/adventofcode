import java.io.*;
import java.util.*;

public class Day6{

	Hashtable [] counts;
	int maxchars;

 	public static void main(String [] args){
		try{
			Day6 test=new Day6(6);
			System.out.println("Test:"+test.testRead());
			
			Day6 real=new Day6(8);
			System.out.println("Answer:"+real.read(args.length>0?args[0]:"input"));


		}catch(IOException e){
			e.printStackTrace();
		}
	

	}


	

	public boolean testRead() throws IOException{
		String message=read("test");
		return(message.equals("easter"));
	}

	public String read(String filename) throws IOException {
		System.out.println("Reading "+filename);
		LineNumberReader fr=new LineNumberReader(new FileReader(new File(filename)));
		String line=fr.readLine();
		while(line!=null){
			processLine(line);
			line=fr.readLine();
		}
		fr.close();
		return getMessage();
	}

	

	public Day6(int i)	{
		maxchars=i;
		counts=new Hashtable[i];
		for (int x=0;x<maxchars;x++){
			counts[x]=new Hashtable();
		}
	}


	public void  processLine(String line){
		for(int i=0;i<line.length();i++){
			char c=line.charAt(i);
			Hashtable table=counts[i];
			int cnt=0;
			Object val=table.get(c);
			if(val!=null) cnt=((Integer)val).intValue();
			cnt++;
			table.put(c,cnt);
		}
	}

	String getMessage(){
		String msg="";
		for(int x=0;x<maxchars;x++){
			char maxc='?';
			int max=0;
			for(char c='a';c<='z';c++){
				Object o=counts[x].get(c);
				System.out.println(c+" - "+o);
				if(o!=null){
					int cnt=((Integer)o).intValue();
					if (cnt>max){
						max=cnt;
						maxc=c;
					}
				}
				
			}
			msg+=maxc;
		}
		return msg;
	}

}
