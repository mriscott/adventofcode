import java.util.*;
import java.io.*;

public class AddNumbers implements Item{
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
		System.out.println("Part 1 tests passed");

		test2("[,1,2,3]",6);						 
		test2("[1,{\"c\":\"red\",\"b\":2},3]",4);
		test2("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}",0);
		test2("[1,\"red\",5]",0);
					
		try{
			AddNumbers real=new AddNumbers(new File("input"));
			real.calc();
			System.out.println("Part1:"+real.sum);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public int getSum(){
		return sum;
	}
	

	public static  void test(String json,int sum){
		AddNumbers me = new AddNumbers(json);
		me.calc();
		if (me.sum!=sum) throw new RuntimeException("Test failed :" +json+"->"+me.sum+" not "+sum);
	}

	public static  void test2(String json,int sum){
		AddNumbers me = new AddNumbers(json);
		me.calc2();
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
		Item curr=this;
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
	public void calc2(){
		sum=0;
		Item cur=this;
		StringBuilder number=new StringBuilder();
		StringBuilder str=null;
		for(int x=0;x<json.length();x++){
			char c = json.charAt(x);
			switch(c){
			case '{':
				// open section
				KeyVal keyval = new KeyVal(cur);
				cur=keyval;
			case '[':
				//open array
				Arr arr=new Arr(cur);
				cur=arr;
				break;

			case ']':
				//close array				
			case '}':
				//close section
			case ':':
				//keyval sep
			case ',':
				//separator
				
			case '"':
				//string
				if(str==null) str=new StringBuilder();
				else {
					processString(str.toString(),cur);
					str=null;
				}
				if(number.length()>0){
					processNumber(number.toString());
					number = new StringBuilder();
				}
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


	public Item getParent(){
		return null;
	}

	

	public void processNumber(String s){
		if(s.length()==0) return;
		sum += Integer.parseInt(s);
	}
	
	public void processString(String s,Item curr){
		if(curr instanceof Arr){
			((Arr)curr).add(s);
		}
		if(curr instanceof KeyVal){
		}
	}


	class Arr extends ArrayList implements Item{
		Item parent;
		int sum=0;

		public Arr(Item parent){
			super();
			this.parent=parent;
		}
		
		public int getSum(){
			return sum;
		}
		public Item getParent(){
			return parent;
		}
	}

	class KeyVal implements Item{
		int sum=0;
		String key;
		Object val;
		boolean redval=false;
		Item parent;

		public KeyVal(Item p){
			parent=p;
		}

		public int getSum(){
			if(redval) return 0;
			return sum;
		}

		public void add(Object o){
			if (key==null){
				if (o instanceof String) key=o.toString();
				else throw new RuntimeException ("Adding val without key");
			}
			else {
				val=o;
				if(o.equals("red")){
					redval=true;
				}
		}
	}

	public Item getParent(){
		return parent;
	}
	}
}


interface Item{
	public Item getParent();
	public int getSum();
}
	
