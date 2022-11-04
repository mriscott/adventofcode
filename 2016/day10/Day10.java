public class Day10{
	class Bot{
		Integer chip1;
		Integer chip2;
		int botno;


		Bot(int no){
			botno=no;
		}
		
		void addChip(int chip){
			if(chip1==null){
				chip1=chip;
			}else{
				if(chip2!=null){
					throw new IllegalArgumentException(toString()+" is full");
				}
				if(chip<chip1){
					chip2=chip1;
					chip1=chip;
				}else{
					chip2=chip;
				}
				if(chip1==2 && chip2==5) System.out.println(this);
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
	int MAXOUTPUT=10;

	public static void main(String []args){
		Day10 me = new Day10();
		me.test();
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
			if(words[5].equals("bot")){
				bots[dest].addChip(bots[bot].poplow());
			}

			if(words[5].equals("output")){
				outputs[dest]=bots[bot].poplow();
			}
			if(!words[7].equals("and") || !words[8].equals("high")||!words[9].equals("to")){
				throw new IllegalArgumentException("Invalid line:"+line);
			}
			dest=Integer.parseInt(words[11]);
			if(words[10].equals("bot")){
				bots[dest].addChip(bots[bot].pophigh());
			}

			if(words[10].equals("output")){
				outputs[dest]=bots[bot].pophigh();
			}
		}


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

	}
}
