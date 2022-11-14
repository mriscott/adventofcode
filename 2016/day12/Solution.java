import java.util.*;

public class Solution extends ReadFile{

	Hashtable regi;
	List instructions;

	
	public static void main(String [] args){
		Solution test=new Solution();
		test.runTest();
		Solution part1=new Solution();
		part1.safeRead("input.txt");
		part1.run();
		System.out.println("Part1:"+part1.getReg("a"));
		
		Solution part2=new Solution();
		part2.safeRead("input.txt");
		part2.setReg("c",1);
		part2.run();
		System.out.println("Part2:"+part2.getReg("a"));
	}

	public void runTest(){
		process("cpy 41 a");
		process("inc a");
		process("inc a");
		process("dec a");
		process("jnz a 2");
		process("dec a");
		run();
		long a=getReg("a");
		if(a!=42){
			throw new IllegalArgumentException("Reg a ("+a+") should be 42");
		}
	}

	void process(String line){
		instructions.add(line);
	}

	public Solution(){
		regi=new Hashtable(4);
		setReg("a",0);
		setReg("b",0);
		setReg("c",0);
		setReg("d",0);
		instructions=new ArrayList();
	}

	void setReg(String r,long x){
		regi.put(r,""+x);
	}



	void run(){
		int idx=0;
		while(idx<instructions.size()){
			String instruction=(String)instructions.get(idx);
			String [] splt=instruction.split(" ");
			long x=getReg(splt[1]);

			if(splt[0].equals("cpy")){
				setReg(splt[2],x);
			}
			if (splt[0].equals("inc")){
				x++;
				setReg(splt[1],x);
			}
			if (splt[0].equals("dec")){
				x--;
				setReg(splt[1],x);
			}
			if (splt[0].equals("jnz")){
				if(x!=0){
					long jmp=getReg(splt[2]);
									
					idx--;
					idx+=jmp;
				}
			}
			idx++;
			//printStatus(idx);
		}

	}

	void printStatus(int idx){
		System.out.print(" a:"+getReg("a"));
		System.out.print(".b:"+getReg("b"));
		System.out.print(" c:"+getReg("c"));
		System.out.print(" d:"+getReg("d"));
		System.out.println(" ["+idx+"]");
	}

	long getReg(String r){
		long x;
		try{
			x=Long.parseLong(r);
		}catch (NumberFormatException e){
			// must be a register
			x=Long.parseLong(regi.get(r).toString());
		}
		return x;
	}

	
}
	

	
		
		

