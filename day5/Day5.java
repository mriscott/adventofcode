import java.io.*;
import java.util.*;

public class Day5{
	String filename;
	List list;
	long [] instructions;

	public static void main(String[] args){
		if(args.length<1) {
			System.out.println("No filename");
			System.exit(1);
		}
		Day5 me = new Day5(args[0]);
		try {
		    me.readFile();
		    me.process();
		}catch (Exception e){
		    e.printStackTrace();
		}
		
	}

	public Day5(String file){
		filename=file;
		list = new ArrayList();
	}

    public void process(){
	int pos=0;
	int moves=0;
	while (pos>=0 && pos <instructions.length){	   
	    print(pos);
	    moves++;
	    int change=(int)instructions[pos];
	    if(change>=3) instructions[pos]--;
	    else instructions[pos]++;
	    pos=pos+change;
	}
	System.out.println("Finished in "+moves);
    }

	public void print(int y){
	    if(instructions.length>20){
		//System.out.println("Pos:"+y);
	    }else{
		for(int x=0;x<instructions.length;x++){		    
		    System.out.print(" "+((y==x)?"(":"")+instructions[x]+((y==x)?")":""));
		}
	    
		System.out.println("");
	    }
	}

	public void readFile() throws IOException{
		LineNumberReader r = new LineNumberReader(new FileReader(filename));
		while (true){
			String line = r.readLine();
			if (line==null) break;
			list.add(line);
		}

		instructions = new long[list.size()];
		for (int x=0;x<list.size();x++){
		    instructions[x]=Long.parseLong((String)list.get(x));
		}
	}
	
}
