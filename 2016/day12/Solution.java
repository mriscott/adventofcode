import java.util.*;

public class Solution extends ReadFile{

	Hashtable reg;
	List instructions;

	
	public static void main(String [] args){
		Solution test=new Solution();
		test.runTest();
		Solution part1=new Solution();
		part1.safeRead("input.txt");
		part1.run();
		System.out.println("Part1:"+part1.getReg("a"));
	}

	public void runTest(){
		process("cpy 41 a");
		process("inc a");
		process("inc a");
		process("dec a");
		process("jnz a 2");
		process("dec a");
		run();
		int a=getReg("a");
		if(a!=42){
			throw new IllegalArgumentException("Reg a ("+a+") should be 42");
		}
	}

	void process(String line){
		instructions.add(line);
	}

	public Solution(){
		reg=new Hashtable(4);
		reg.put("a",0);
		reg.put("b",0);
		reg.put("c",0);
		reg.put("d",0);
		instructions=new ArrayList();
	}


	void run(){
		int idx=0;
		while(idx<instructions.size()){
			String instruction=(String)instructions.get(idx);
			String [] splt=instruction.split(" ");
			int x=getReg(splt[1]);

			if(splt[0].equals("cpy")){
				reg.put(splt[2],x);
			}
			if (splt[0].equals("inc")){
				x++;
				reg.put(splt[1],x);
			}
			if (splt[0].equals("dec")){
				x--;
				reg.put(splt[1],x);
			}
			if (splt[0].equals("jnz")){
				if(x!=0){
					int jmp=getReg(splt[2]);
					idx--;
					idx+=jmp;
				}
			}
			idx++;
		}

	}

	int getReg(String r){
		int x;
		try{
			x=Integer.parseInt(r);
		}catch (NumberFormatException e){
			// must be a register
			x=(Integer)reg.get(r);
		}
		return x;
	}

	
}
	

	
		
		

