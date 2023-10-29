import java.util.*;
import java.io.*;

public class AddNumbers{
	String json;
	int sum;
	public static void main(String [] args){
		test("[,1,2,3]",6);						 
		test("{\"a\":2,\"b\":4}",6);
		test("[[[3]]]",3);
		test("{\"a\":{\"b\":4},\"c\":-1}",3);
		test("{\"a\":[-1,1]}",0);
		test("[-1,{\"a\":1}]",0);
		test("[]",0);
		test("{}",0);
		System.out.println("Tests passed");
		try{
			AddNumbers real=new AddNumbers(new File("input"));
			real.calc();
			System.out.println("Part1:"+real.sum);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	

	public static  void test(String json,int sum){
		AddNumbers me = new AddNumbers(json);
		me.calc();
		if (me.sum!=sum) throw new RuntimeException("Test failed :" +json+"->"+me.sum+" not "+sum);
	}

	public AddNumbers(String json){
		this.json=json;
	}

	public AddNumbers(File f) throws IOException{
		LineNumberReader r=new LineNumberReader(new FileReader(f));
		StringBuffer buf=new StringBuffer();
		while(true){
			String s=r.readLine();
			if (s==null) break;
			buf.append(s);
		}
		r.close();
		json=buf.toString();
	}

	public void calc(){
		sum=0;
		StringBuilder number=new StringBuilder();
		for(int x=0;x<json.length();x++){
			char c = json.charAt(x);
			switch(c){
			case '{':
				// open section
			case '[':
				//open array
			case ']':
				//close array
			case '}':
				//close section
			case ',':
				//separator
			case '"':
				//string
				processNumber(number.toString());
				number = new StringBuilder();
				break;

			case '-':
				if(number.length()==0){
					number.append(c);
				}else{
					processNumber(number.toString());
					number = new StringBuilder();
				}
				break;
				
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				number.append(c);
				break;
			

			default:
				processNumber(number.toString());
				number = new StringBuilder();
			}
		}
	}

	public void processNumber(String s){
		if(s.length()==0) return;
		sum += Integer.parseInt(s);
	}
}
