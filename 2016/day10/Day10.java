import java.io.*;

public class Day10 extends ReadFile{
	class Bot{
		Integer chip1;
		Integer chip2;
		int low=-1;
		int high=-1;
		int botno;
		boolean inuse;
		Target lowTarget;
		Target highTarget;

		Bot(int no){
			botno=no;
			inuse=false;
		}

		class Target{
			boolean isOutput;
			int num;
			public Target(boolean o, int n){
				num=n;
				isOutput=o;
			}
		}

		void setLowTarget(boolean o, int n){
			lowTarget=new Target(o,n);
		}

		void setHighTarget(boolean o, int n){
			highTarget=new Target(o,n);
		}

		boolean isFull(){
			return chip1!=null && chip2!=null;
		}
		
		
		void addChip(int chip){
			inuse=true;
			if(chip1==null){
				chip1=chip;
				low=chip;
				high=chip;
			}else{
				if(chip2!=null){
					if(chip!=chip2)
					throw new IllegalArgumentException(toString()+" is full");
				}
				if(chip<chip1){
					chip2=chip1;
					chip1=chip;
				}else{
					chip2=chip;
				}
				low=chip1;
				high=chip2;
			}
		 
		}

					
		int poplow(){
			int ret;
			if(chip1==null) {
				ret=chip2;
				chip2=null;
			}else{
				ret=chip1;
				chip1=chip2;
				chip2=null;
			}
			return ret;
		}

		int getlow(){
			return low;
		}

		int gethigh(){
			return high;
		}

		int pophigh(){
			int ret;
			if(chip2==null) {
				ret=chip1;
				chip1=null;
			}else{
				ret=chip2;
				chip2=null;
			}
			return ret;
		}	
		public String toString(){
			return "Bot"+botno+" ("+chip1+","+chip2+")";
		}

					

	}

	Bot[] bots;
	Integer[] outputs;
	int MAXBOT=210;
	int MAXOUTPUT=30;
	boolean values;

	public static void main(String []args){
		Day10 me = new Day10();
		me.test();
		try{
			Day10 testread=new Day10();
			testread.testread();
			Day10 part1=new Day10();
			part1.readFile("input.txt");
			System.out.println("Part1: "+part1.getBot(17,61));
			System.out.println("Part2: "+part1.outputs[0]*part1.outputs[1]*part1.outputs[2]);
			
			


			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	void parseLine(String line) throws NumberFormatException{
		String [] words=line.split(" ");
		if(words[0].equals("value")){
			if(words.length!=6)
				throw new IllegalArgumentException("Invalid line:"+line);
			int val=Integer.parseInt(words[1]);
			if(!words[2].equals("goes") || !words[3].equals("to")||!words[4].equals("bot")){
				throw new IllegalArgumentException("Invalid line:"+line);
			}
			int bot=Integer.parseInt(words[5]);
			bots[bot].addChip(val);
		}
		if(words[0].equals("bot")){
			int bot=Integer.parseInt(words[1]);
			if(!words[2].equals("gives")||!words[3].equals("low")||!words[4]
					.equals("to")){
				throw new IllegalArgumentException("Invalid line:"+line);
			}
			int dest=Integer.parseInt(words[6]);
			bots[bot].setLowTarget(words[5].equals("output"),dest);
			if(!words[7].equals("and") || !words[8].equals("high")||!words[9].equals("to")){
				throw new IllegalArgumentException("Invalid line:"+line);
			}
			dest=Integer.parseInt(words[11]);
			bots[bot].setHighTarget(words[10].equals("output"),dest);
			deliverChips(bot);
		}


	}

	void deliverChips(int bot){
		Bot srcBot=bots[bot];
		if (srcBot.isFull()==false) return;
		if(srcBot.lowTarget==null || srcBot.highTarget==null) return;
		if(srcBot.lowTarget.isOutput){
			outputs[srcBot.lowTarget.num]=srcBot.poplow();
		}else{
			bots[srcBot.lowTarget.num].addChip(srcBot.poplow());
			deliverChips(srcBot.lowTarget.num);
		}
		srcBot.lowTarget=null;
		
		if(srcBot.highTarget.isOutput){
			outputs[srcBot.highTarget.num]=srcBot.pophigh();
		}else{
			bots[srcBot.highTarget.num].addChip(srcBot.pophigh());
			deliverChips(srcBot.highTarget.num);
		}
		srcBot.highTarget=null;
	}

		int getBot(int low,int high){
			for(int x=0;x<MAXBOT;x++){
				if(bots[x].getlow()==low && bots[x].gethigh()==high){
					return x;
				}
			}
			return -1;
		}


	
	public Day10(){
		bots=new Bot[MAXBOT];
		for (int x=0;x<MAXBOT;x++){
			bots[x]=new Bot(x);
		}
		outputs=new Integer[MAXOUTPUT];

	}


	
	public void test(){
		parseLine("value 5 goes to bot 2");
		parseLine("value 3 goes to bot 1");
		parseLine("value 2 goes to bot 2");
		parseLine("bot 2 gives low to bot 1 and high to bot 0");
		parseLine("bot 1 gives low to output 1 and high to bot 0");
		parseLine("bot 0 gives low to output 2 and high to output 0");
		if(getBot(2,5)!=2){
			throw new RuntimeException("Bot 2 should compare 2 and 5");
		}
		System.out.println("Direct tests passed");

	}

	public void testread() throws IOException{
		readFile("test.txt");
		if(getBot(2,5)!=2){
			throw new RuntimeException("Bot 2 should compare 2 and 5");
		}
		System.out.println("Read tests passed");
		
	}

	void readFile(String filename) throws IOException{
		// first read for values
		values=true;
		read(filename);
		// then for output
		values=false;
		read(filename);
	}
	void printAllBots(){
		for(int x=0;x<MAXBOT;x++){
			if(bots[x].inuse) System.out.println(bots[x]);
		}
		for(int x=0;x<MAXOUTPUT;x++){
			if(outputs[x]!=null) System.out.println("Output bin "+x+" - "+outputs[x]);
		}
	}

	void process(String line){
		if(values && line.startsWith("value") || !values && line.startsWith("bot"))
			parseLine(line);
	}

	
}
