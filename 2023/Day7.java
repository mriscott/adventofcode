import java.util.*;

public class Day7 extends Utils{
	static String[] SCORENAMES=new String[]{"High Card","One Pair","Two pair","Three of a kind","Full house","Four of a kind","Five of a kind"};	
	int part=1;
	List <Hand> hands=new ArrayList<Hand>();
	public static void main(String [] args){
				 setDebug(false);
				 Day7 test = new Day7("day7/test");
				 Utils.test(6440,test.getWinnings());
				 Day7 real=new Day7("day7/input");
				 System.out.println("Part 1: "+real.getWinnings());
				 

	}
	public Day7(String file){
		this(file,1);
	}
	public Day7 (String file, int part){
		this.part=part;
		safeRead(file);
		printHands();
		debug("Sorting");
		Collections.sort(hands,Collections.reverseOrder());
	}

	void printHands(){
		for(Hand x:hands){
			System.out.println(x);
		}
	}

	int getWinnings(){
		int r=1;
		int w=0;
		for(Hand x:hands){
			w+=x.bid*r;
			r++;
		}
		return w;
	}
	
  
	void process (String line){
		try{
			hands.add(new Hand(line));
		}catch(NumberFormatException e){
			throw new RuntimeException(e);
		}
	}


	class Hand  implements Comparable<Hand>{
		int score;
		int bid;
		String cards;

		public Hand(String str) throws NumberFormatException{
			String [] splt=str.split(" ");
			cards=splt[0];
			bid=Integer.parseInt(splt[1]);
			score=calcScore();
		}

		int cardToVal(char x){
				int v=0;
				switch(x) { 
					case 'A': v++;
					case 'K': v++;
					case 'Q': v++;
					case 'J': v++;
					case 'T': v++;
					case '9': v++;
					case '8': v++;
					case '7': v++;
					case '6': v++;
					case '5': v++;
					case '4': v++;
					case '3': v++;
					case '2': v++;
						  break;
					default:
						  throw new IllegalArgumentException("Unknown card :"+x);
				}
				debug(""+x+"->"+v);
				return v;
		}

		int calcScore(){
			int [] count=new int [14]; 
			for (char x : cards.toCharArray()){
			
				count[cardToVal(x)]++;	


			}
			boolean five=false;	
			boolean four=false;
			boolean three=false;
			int pair=0;
			

			// look for full hice
			for (int x=0;x<count.length;x++){
				if(count[x]==5) five=true;
				if(count[x]==4) four=true;
				if(count[x]==3) three=true;
				if(count[x]==2) pair++;
			}
			if(five) return 6;
			if(four) return 5;
			if(three){
				if(pair==1) return 4;
				else return 3;
			}
			if(pair==2) return 2;
			if(pair==1) return 1;
			return 0;

		}
		String getScore(){
			return SCORENAMES[score];
		}

		public String toString(){
			return cards+"("+getScore()+")";
		}

		char cardAt(int x){
			return cards.charAt(x);
		}

		public int compareTo(Hand p){
			if(p.score!=score){
				return p.score-score;
			}
			else{
				int x=0;
				while(x<cards.length()){
					if(x<p.cards.length() && cardAt(x)!=p.cardAt(x)){
						return cardToVal(p.cardAt(x))-cardToVal(cardAt(x));
					}
					x++;
				}
			}
			return 0;
		}
	}
}
