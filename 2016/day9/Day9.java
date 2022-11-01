import java.io.*;
public class Day9
{
	int len=0;
	int len2=0;
	public Day9(){
	}

	public String decompress(String input){
		StringBuffer output=new StringBuffer();
		for(int x=0;x<input.length();x++){
			char ch=input.charAt(x);
			if(ch=='('){
				int sep=input.indexOf("x",x);
				int range=Integer.parseInt(input.substring(x+1,sep));
				int paren=input.indexOf(")",sep);
				int count=Integer.parseInt(input.substring(sep+1,paren));
				String rep=input.substring(paren+1,paren+1+range);
				x=paren+range;
				for(int i=0;i<count;i++) output.append(rep);
				len+=count*range;
						
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
		return calcLen(decompress2(input));
	}

	int calcLen(String input){
		int l=0;
		for(int x=0;x<input.length();x++){
			if(!Character.isWhitespace(input.charAt(x))) l++;
		}
		return l;
	}

	public String decompress2(String input){
		String output=input;
		while(output.indexOf("(")!=-1){
			output=decompress(output);
		}
		return output;
	}


	public void read(String filename,int part) throws IOException {
		System.out.println("Reading "+filename);
		LineNumberReader fr=new LineNumberReader(new FileReader(new File(filename)));
		String line=fr.readLine();
		while(line!=null){
			if(part==1) decompress(line);
			if(part==2) decompress2(line);
			line=fr.readLine();
		}
		fr.close();
	}

	public int getDecompressedLength(){
		return len;
	}

	public static void main(String [] args){
		Day9 test = new Day9();
		test.testDecompress("ADVENT","ADVENT");
		test.testDecompress("A(1x5)BC","ABBBBBC");
		test.testDecompress("(3x3)XYZ","XYZXYZXYZ");
		test.testDecompress("A(2x2)BCD(2x2)EFG","ABCBCDEFEFG");
		test.testDecompress("(6x1)(1x3)A","(1x3)A");
		test.testDecompress("X(8x2)(3x3)ABCY","X(3x3)ABC(3x3)ABCY");
		test.testDecompressedLength(6+7+9+11+6+18);
		System.out.println("Part 1 tests passed");
		Day9 part1=new Day9();
		try{
			part1.read("input.txt",1);
			System.out.println("Decompressed length : "+part1.getDecompressedLength());
		}catch(IOException e){
			e.printStackTrace();
			return;
		}
		Day9 test2= new Day9();
		test2.testDecompress2Len("(3x3)XYZ",9);
		test2.testDecompress("(3x3)XYZ","XYZXYZXYZ",2);
		test2.testDecompress("X(8x2)(3x3)ABCY","XABCABCABCABCABCABCY",2);
		test2.testDecompress2Len("(27x12)(20x12)(13x14)(7x10)(1x12)A",241920);
		test2.testDecompress2Len("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN",445);
		try{
			Day9 part2=new Day9();
			part2.read("input.txt",2);
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
		if(part==1) real=decompress(input);
		else real=decompress2(input);
		if(real.equals(output)){
			System.out.println("Test: "+input+" -> "+output+" :PASS");
			System.out.println("len:"+len);
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
