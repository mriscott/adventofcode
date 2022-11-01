import java.io.*;
public class Day9
{
	int len=0;
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
		Day9 test = new Day9();
		test.testDecompress("ADVENT","ADVENT");
		test.testDecompress("A(1x5)BC","ABBBBBC");
		test.testDecompress("(3x3)XYZ","XYZXYZXYZ");
		test.testDecompress("A(2x2)BCD(2x2)EFG","ABCBCDEFEFG");
		test.testDecompress("(6x1)(1x3)A","(1x3)A");
		test.testDecompress("X(8x2)(3x3)ABCY","X(3x3)ABC(3x3)ABCY");
		test.testDecompressedLength(6+7+9+11+6+18);
		System.out.println("All tests passed");
		Day9 part1=new Day9();
		try{
			part1.read("input.txt");
			System.out.println("Decompressed length : "+part1.getDecompressedLength());
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void testDecompress(String input,String output){
		String real=decompress(input);
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
