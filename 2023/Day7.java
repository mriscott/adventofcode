import java.util.*;

public class Day7 extends Utils{
	static String[] SCORENAMES=new String[]{"High Card","One Pair","Two pair","Three of a kind","Full house","Four of a kind","Five of a kind"};	
	int part=1;
	List <Hand> hands=new ArrayList<Hand>();
	public static void main(String [] args){
				 setDebug(false);
				 Day7 test = new Day7("day7/test");
				test.printHands();
				 Utils.test(6440,test.getWinnings());
				test.part=2;
				test.sort();
				test.printHands();
				 Utils.test(5905,test.getWinnings());
				 Day7 real=new Day7("day7/input");
				 System.out.println("Part 1: "+real.getWinnings());
				 

	}
	public Day7(String file){
		this(file,1);
	}
	public Day7 (String file, int part){
		this.part=part;
		safeRead(file);
		//printHands();
		debug("Sorting");
		sort();

	}
	void sort(){
		for(Hand x:hands)x.calcScore();
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
			calcScore();
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
				if(part==2 && x=='J') v=0;
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

			int retval=0;

			// look for full hice
			for (int x=0;x<count.length;x++){
				if(count[x]==5) five=true;
				if(count[x]==4) four=true;
				if(count[x]==3) three=true;
				if(count[x]==2) pair++;
			}
			
			if(pair==1) retval=1;
			if(pair==2) retval=2;
			if(three){
				if(pair==1) retval=4;
				else retval=3;
			}
			
			if(four) retval=5;
			if(five) retval=6;
			if(part==2){
			    if(count[0]==1){
				switch(retval){
				case 0: retval=1; // now a pair
				    break;
				case 1: retval=3; // pair ->3
				    break;
				case 2: retval=4; //2 pairs -> full house
				    break;
				case 3: retval=5; // 3 -> 4
				    break;
				case 4: // not possible
				    throw new RuntimeException("Prog error");
				case 5: retval=6;
					break;
				case 6: // no more possibles
				default:
				    throw new RuntimeException("Prog error");
				}

				
				 
			    if(count[0]==2){
				switch(retval){
				case 0: retval=1;
				    throw new RuntimeException("Prog error");
				case 1: retval=3; // pair ->3
				    break;
				case 2: retval=5; //2 pairs -> 4
				    break;
				case 3: retval=6; // not possible
				    throw new RuntimeException("Prog error");
				case 4: retval=6; //Fh -> 6
				    break;
				case 5: retval=6;
					// no more possibles
				default:
				    throw new RuntimeException("Prog error");
				}

				
				 
		  
			    }
			    if(count[0]==3){
				switch(retval){
				case 0: retval=1;
				case 1: retval=3; 
				case 2: retval=5; //2 pairs -> 4
				    throw new RuntimeException("Prog error");
				case 3: retval=6; // not possible
				    throw new RuntimeException("Prog error");
				case 4: 

				    break;
				case 5: retval=6;
					// no more possibles
				default:
				    throw new RuntimeException("Prog error");
				}

				
				 
		  
			    }
				      
			    
			}
			}
			score=retval;
		    return retval;
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
