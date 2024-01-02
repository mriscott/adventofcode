public class Day2 extends Utils{
	int sumpos=0;
	int part=1;
	public static void main(String [] args){
		setDebug(false);
		Day2 test = new Day2("day2/test");
		test(8,test.sumpos);
		System.out.println("Tests passed");
		Day2 inp = new Day2("day2/input.txt");
		System.out.println("Part 1 : "+inp.sumpos);
	}
	public Day2(String file){
		this(file,1);
	}
	public Day2 (String file, int part){
		this.part=part;
		safeRead(file);
	}
	
  
	void process (String line){
		boolean possible=true;
		String [] games=line.split(": ");
		try{
			int gamenum=Integer.parseInt(games[0].substring(5));
			debug("Processing game "+gamenum);
			String [] hands = games[1].split("; ");
			for (int x=0;x<hands.length;x++){
				String [] balls=hands[x].split(", ");
				for (int y=0;y<balls.length;y++){
					String []cols=balls[y].split(" ");
					String colour=cols[1];
					int num=Integer.parseInt(cols[0]);
					debug(colour+ " - "+num);
					if(colour.equals("red") && num>12) possible=false;
					if(colour.equals("green") && num>13) possible=false;
					if(colour.equals("blue") && num>14) possible=false;
				}
			}
			if(!possible){
				debug ("Game "+gamenum+" is impossible");
			}else sumpos+=gamenum;
		
		
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
