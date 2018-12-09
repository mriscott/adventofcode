public class Day9
{
    int playernum;
    int marbles;
    int turn;
    Player [] players;
    Marble start;
    Marble current;
    
    public static void main(String [] args){
	test(9,26,32);
	test (10,1618, 8317);
	test (13 , 7999, 146373);
	test (17 , 1104, 2764);
	test (21 , 6111, 54718);
	test (30 , 5807, 37305);

	Day9 me = new Day9(411,71170);
	me.play();
	System.out.println(me.maxScore());
    }


    public static void test(int players, int marbles, int maxscore){
	Day9 me = new Day9(players,marbles);
	me.play();
	int max=me.maxScore();
	if(max!=maxscore) {
	    throw new RuntimeException("Max score for "+players +" players and "+marbles+" marbles should be "+maxscore+" not "+max);
	}
	
    }
    
    public Day9(int p, int m){
	playernum=p;
	marbles=m;
	start=new Marble(0);
	current=start;
	current.setCurrent(true);
	current.clockwise=current;
	current.anticlockwise=current;

	players=new Player[playernum];
	for (int x=0;x<playernum;x++){
	    players[x]=new Player(x);
	}
	turn=0;
    }

    public int maxScore(){
	int max=0;
	for (int x=0;x<playernum;x++){
	    //System.out.println(players[x]);
	    if(max<players[x].score) max=players[x].score;
	}
	return max;
    }
    
    public void play(){
	
	for (int x=1;x<=marbles;x++){
	    Marble newcurrent = new Marble(x);
	    if (x%23==0){
		players[turn].addMarble(newcurrent);
		Marble toRemove=current;
		for (int i=0;i<7;i++){
		    toRemove=toRemove.anticlockwise;
		}
		players[turn].addMarble(toRemove);
		toRemove.remove();
		current.setCurrent(false);
		current=toRemove.clockwise;
		current.setCurrent(true);
		
	    }
	    else {
		newcurrent.placeClockwise(current.clockwise);
		current.setCurrent(false);
		current=newcurrent;
		current.setCurrent(true);
	    }
	    //printList(start);
	    turn++;
	    if(turn==playernum) turn=0;
	}
	
    }

    public static void printList(Marble start){
	Marble thisone=start;
	do{
	    System.out.print(thisone+" ");
	    thisone=thisone.clockwise;
	}while(thisone!=start);
	System.out.println("");
    }
    
}

class Player{
    int score;
    int number;
    public Player(int no){
	number=no;
	score=0;
    }

    public void addMarble(Marble m){
	score+=m.number;
    }

    public String toString(){
	return "Player "+number+":"+score;
    }

}

class Marble{
    Marble clockwise;
    Marble anticlockwise;
    int number;
    boolean current;

    public Marble(int no){
	number=no;
    }

    public void setCurrent(boolean iscurrent){
	current=iscurrent;
    }

    public void placeClockwise(Marble other){
	anticlockwise=other;
	clockwise=other.clockwise;
	other.clockwise.anticlockwise=this;
	other.clockwise=this;
    }

    public void remove(){
	//System.out.println("Removing "+toString());	
	clockwise.anticlockwise=anticlockwise;
	anticlockwise.clockwise=clockwise;
    }

    public String toString(){
	String ret="";
	if (current){
	    ret="(";
	}
	//ret+=anticlockwise.number+"<-";
	ret+=number;
	//ret+="<-"+clockwise.number;
	if (current){
	    ret+=")";
	}
	return ret;
    }
	
}


