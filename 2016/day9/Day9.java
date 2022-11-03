import java.io.*;
public class Day9
{
	int len=0;
	int part;
	public Day9(int p){
		part=p;
	}

	class Chunk{
		String chunk;
		String rep;
		int count;
		int range;
		int explen=0;

		Chunk(int x, String input){
			int sep=input.indexOf("x",x);
			range=Integer.parseInt(input.substring(x+1,sep));
			int paren=input.indexOf(")",sep);
			count=Integer.parseInt(input.substring(sep+1,paren));
			rep=input.substring(paren+1,paren+1+range);
			chunk=input.substring(x,paren+1);
		}
		public String toString(){
			return "Chunk:"+chunk+" ["+range+","+count+"]";
		}

		int expand(boolean recurse){
			int mylen=0;
			StringBuffer expandedrep=new StringBuffer();
			for(int x=0;x<rep.length();x++){
				if(recurse && rep.charAt(x)=='('){
					Chunk sub=new Chunk(x,rep);
					x=sub.movePastChunk(x);
					sub.expand(true);
					expandedrep.append(sub.rep);
				}
				else{
					expandedrep.append(rep.charAt(x));
				}
			}
			mylen=expandedrep.length()*count;
			StringBuffer exp=new StringBuffer();
			for(int i=0;i<count;i++) {
				exp.append(expandedrep);
			}
			rep=exp.toString();
			return mylen;

		}

		int movePastChunk(int x){
			x+=chunk.length();
			x+=range;
			x--;
			return x;
		}
	}


	int decompressChunk(int x,String input,StringBuffer output){
		Chunk chunk=new Chunk(x,input);
		x=chunk.movePastChunk(x);	
		int len2=chunk.expand(part==2);
		output.append(chunk.rep);
		if(part==1) len+=chunk.count*chunk.range;
		else len+=len2;
		return x;
	}

	public String decompress(String input){
		StringBuffer output=new StringBuffer();
		for(int x=0;x<input.length();x++){
			char ch=input.charAt(x);
			if(ch=='('){
				x=decompressChunk(x,input,output);
			}
			else {
				output.append(ch);
				if(!Character.isWhitespace(ch)){
					len++;
				}
			}
		}
		return output.toString();
	}

	public int decompress2Len(String input){
		decompress(input);
		//return calcLen(decompress(input));
		return len;
	}

	int calcLen(String input){
		int l=0;
		for(int x=0;x<input.length();x++){
			if(!Character.isWhitespace(input.charAt(x))) l++;
		}
		return l;
	}



	public void read(String filename) throws IOException {
		System.out.println("Reading "+filename);
		LineNumberReader fr=new LineNumberReader(new FileReader(new File(filename)));
		String line=fr.readLine();
		while(line!=null){
			decompress(line);
			line=fr.readLine();
		}
		fr.close();
	}

	public int getDecompressedLength(){
		return len;
	}

	public static void main(String [] args){
		Day9 test = new Day9(1);
		test.testDecompress("ADVENT","ADVENT");
		test.testDecompress("A(1x5)BC","ABBBBBC");
		test.testDecompress("(3x3)XYZ","XYZXYZXYZ");
		test.testDecompress("A(2x2)BCD(2x2)EFG","ABCBCDEFEFG");
		test.testDecompress("(6x1)(1x3)A","(1x3)A");
		test.testDecompress("X(8x2)(3x3)ABCY","X(3x3)ABC(3x3)ABCY");
		test.testDecompressedLength(6+7+9+11+6+18);
		System.out.println("Part 1 tests passed");
		Day9 part1=new Day9(1);
		try{
			part1.read("input.txt");
			System.out.println("Decompressed length : "+part1.getDecompressedLength());
		}catch(IOException e){
			e.printStackTrace();
			return;
		}
		Day9 test2= new Day9(2);
		test2.testDecompress2Len("(3x3)XYZ",9);
		test2.testDecompress("(3x3)XYZ","XYZXYZXYZ",2);
		test2.testDecompress("X(8x2)(3x3)ABCY","XABCABCABCABCABCABCY",2);
		test2=new Day9(2);
		test2.testDecompress2Len("(27x12)(20x12)(13x14)(7x10)(1x12)A",241920);
		test2=new Day9(2);
		test2.testDecompress2Len("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN",445);
		System.out.println("Part 2 tests passed");
		try{
			Day9 part2=new Day9(2);
			part2.read("input.txt");
			System.out.println("Decompressed length : "+part2.getDecompressedLength());
		}catch(IOException e){
			e.printStackTrace();
			return;
		}
	}

	public void testDecompress2Len(String input,int len){
		int real=decompress2Len(input);
		if(real==len){
			System.out.println("Test: "+input+" -> "+len+" :PASS");
		}
		else{

			throw new RuntimeException("TEST FAIL:"+input+" -> "+real+", not "+len);

		}

	}

	public void testDecompress(String input,String output){
		testDecompress(input,output,1);
	}

	public void testDecompress(String input,String output,int part){
		String real=null;
		real=decompress(input);
		if(real.equals(output)){
			System.out.println("Test: "+input+" -> "+output+" :PASS");
		}
		else{

			throw new RuntimeException("TEST FAIL:"+input+" -> "+real+", not "+output);

		}

	}

	public void testDecompressedLength(int expected){
		if(expected==getDecompressedLength()){
			System.out.println("Length test passed");
		}
		else{
			throw new RuntimeException("Length is "+getDecompressedLength()+" - expected "+expected);
		}
	}


}
