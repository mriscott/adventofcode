public class Day9
{
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
						
			}
			else output.append(ch);
		}
		return output.toString();
	}

	public static void main(String [] args){
		Day9 test = new Day9();
		test.testDecompress("ADVENT","ADVENT");
		test.testDecompress("A(1x5)BC","ABBBBBC");
		test.testDecompress("(3x3)XYZ","XYZXYZXYZ");
		test.testDecompress("A(2x2)BCD(2x2)EFG","ABCBCDEFEFG");
		test.testDecompress("(6x1)(1x3)A","(1x3)A");
		test.testDecompress("X(8x2)(3x3)ABCY","X(3x3)ABC(3x3)ABCY");
		System.out.println("All tests passed");
	}

	public void testDecompress(String input,String output){
		String real=decompress(input);
		if(real.equals(output)){
			System.out.println("Test: "+input+" -> "+output+" :PASS");
		}
		else{

			throw new RuntimeException("TEST FAIL:"+input+" -> "+real+", not "+output);

		}

	}


}
