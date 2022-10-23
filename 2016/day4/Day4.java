import java.io.*;
import java.util.*;

public class Day4{

	public static void main(String [] args){
		try{
			Day4 test=new Day4();
			System.out.println("Test:"+test.test());
			Day4 test2=new Day4();
			System.out.println("Test2:"+test.testRead());
			System.out.println("test3:"+test.testDecrypt());
			
			Day4 real=new Day4();
			System.out.println("Answer:"+real.read(args.length>0?args[0]:"input"));


		}catch(IOException e){
			e.printStackTrace();
		}
	

	}

	String decrypt(String room, int sector){
		// first chop off sector
		int l=room.lastIndexOf('-');
		room=room.substring(0,l);
		String str="";
		for (int x=0;x<room.length();x++){
			char c=room.charAt(x);
			if(c=='-') {
				c=' ';
			}else{
				c-=97;
				c+=sector;
				c=(char)(c%26);
				c+=97;
			}
			str+=c;

		}
		System.out.println(str+" "+sector);
		return str;
	}

	public boolean testDecrypt(){
		return(decrypt("qzmt-zixmtkozy-ivhz-343",343).equals("very encrypted name"));
	}
	
	public boolean test()
	{
		boolean success=true;
		if (processLine("aaaaa-bbb-z-y-x-123[abxyz]")!=123) success=false;
		if(processLine("a-b-c-d-e-f-g-h-987[abcde]")!=987) success=false;
		if(processLine("not-a-real-room-404[oarel]")!=404) success=false;
		if(processLine("totally-real-room-200[decoy]")!=0) success=false;;
		return(success); 
		
	}

	public boolean testRead() throws IOException{
		int total=read("test");
		return(total==1514);
	}

	public int read(String filename) throws IOException {
		System.out.println("Reading "+filename);
		LineNumberReader fr=new LineNumberReader(new FileReader(new File(filename)));
		String line=fr.readLine();
		int count=0;
		while(line!=null){
			count+=processLine(line);
			line=fr.readLine();
		}
		fr.close();
		return count;
	}

	

	public Day4(){
	}


	// return 1 if this is a real room, 0 otherwise
	public int  processLine(String line){
		boolean data=true;
		boolean cksum=false;
		String checksum="";
		int max=0;
		String roomno="";
		Hashtable counts=new Hashtable();
		for(int i=0;i<line.length();i++){
			char c=line.charAt(i);
			if (data && c>='a'&& c<='z'){
				int count=0;
				if(counts.get(c)!=null) {
				       	count=Integer.parseInt(counts.get(c).toString());
				}

				count++;
				counts.put(c,""+count);
				if(count>max) max=count;
			}
			if(c>='0' && c<='9'){
				// room number
				data=false;
				roomno+=c;
			}
			if(c=='['){
				cksum=true;
			}
			
			if (cksum && c>='a'&& c<='z'){
				checksum+=c;
			}

		}
		// key on count
		String [] keymap=new String[max+1];
		for(char c='a';c<='z';c++){
			Object o=counts.get(c);
			if(o!=null){
				int cnt=Integer.parseInt(o.toString());
				if(keymap[cnt]==null){
					keymap[cnt]="";
				}
				keymap[cnt]+=c;
			}
			
		}
		int x=max;
		String ret="";
		while(ret.length()<5 && max>=0){
			if(keymap[x]!=null) ret+=keymap[x];
			x--;
		}
		int r=Integer.parseInt(roomno);
		decrypt(line,r);
		if(ret.substring(0,5).equals(checksum)) return r;
		return 0;
	}

}
