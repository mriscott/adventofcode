import java.io.*;
import java.io.*;
import java.util.*;

public class Track{

    public static int MAX=200;
    
    Entry[][] grid;
    int maxx,maxy;
    List carts;

    public static void main (String [] args){
	if (args.length!=1){
	    System.out.println("Usage Track <input>");
	    System.exit (1);
	}
	Track me=new Track();
	try {
	    me.init(args[0]);	    
	    //System.out.println(me);
	    boolean crash=false;
	    int time=0;
	    while (!crash){
		//System.out.println("Tick:"+time);
		crash=!me.move();
		time++;
		//System.out.println(me);

	    }
	    
	    System.out.println("Time of crash:"+time);
	    //System.out.println(me);

	}catch (Exception e){
	    e.printStackTrace();
	}
    }

    public Track(){
	grid = new Entry[MAX][MAX];
	carts=new ArrayList();
    }

    public void init(String file) throws IOException {
	maxx=0;
	LineNumberReader r = new LineNumberReader(new FileReader(file));
	int y=0;
	String line=r.readLine();
	y=0;
	while (line!=null) {
	    char[] ch=line.toCharArray();
	    int x=0;
	    for (x=0;x<ch.length;x++){
		Entry e=new Entry(ch[x],this,x,y);
		grid[x][y]=e;
		if (e.getCart()!=null){
		    carts.add(e.getCart());
		}
		   
		
	    }
	    if (x>maxx) maxx=x;
	    y++;
	    line=r.readLine();
	}
	maxy=y;
	r.close();

    }

    public String toString(){
	StringBuffer buf=new StringBuffer();
	if(grid==null) return null;
	for (int y=0;y<=maxy;y++){
	    for (int x=0;x<=maxx;x++){
		Entry here=get(x,y);
		if(here==null){
		    break;
		}
		buf.append(here.getChar());
	    }
	    buf.append("\n");
	}
	return buf.toString();
    }


    public boolean move(){
	boolean crash=false;
	Iterator it = carts.iterator();
	while (it.hasNext()){
	    Cart cart=(Cart)it.next();
	    cart.setMoved(false);
	}
	int moves=0;
	if(grid==null) throw new RuntimeException("Grid not initialized");
	for (int y=0;y<=maxy;y++){
	    for (int x=0;x<=maxx;x++){		
		Entry here=get(x,y);
		if(here==null) break;
		Cart cart=here.getCart();
		if(cart!=null && !cart.hasMoved()){
		    //System.out.println("Moving cart "+cart.cartno);
		    crash=cart.move();
		    //System.out.println(this);
		    if(!crash) {
			System.out.println("Crash "+cart.cartno+" at "+cart.getX()+","+cart.getY());
			return false;
		    }
		    cart.setMoved(true);
		    moves++;
		}

	    }	    
	}
	if (moves != carts.size()){
	    throw new RuntimeException("Not all carts moved");
	}
	return crash;
    }
    
    public Entry get(int x, int y){
	return grid[x][y];
    }
}

class Entry{
    char ch;
    Cart cart;
    boolean moved=false;
    Track grid;
    int x,y;
    boolean crash=false;
    
    public Entry(char thechar, Track g,int xx,int yy){
	grid=g;
	y=yy;
	x=xx;
	this.ch=thechar;
	switch (thechar){
	case '>':
	case '<':
	    cart=new Cart(this,thechar);
	    ch='-';
	    break;
	case '^':
	case 'v':
	    cart=new Cart(this,thechar);
	    ch='|';
	    break;
	default:
	    cart=null;
	}
	
    }
    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public String toString(){
	return ""+getChar();
    }
    public char getChar(){
	if (crash) return 'X';
	if(cart!=null){
	    return cart.getChar();
	}
	return ch;
    }

    public Track getGrid(){
	return grid;
    }

    public void setCart(Cart c){
	cart=c;
    }

    public Cart getCart(){
	return cart;
    }

    public boolean isCart(){
	return cart!=null;
    }

    public void setCrash(boolean b){
	crash=b;
    }

   
}

class Cart
{
    Entry location;
    int direction;
    Track grid;
    boolean moved;
    int cartno;
    int turn;
    
    static final int UP=0;
    static final int LEFT=1;
    static final int DOWN=2;
    static final int RIGHT=3;
    static final int UNKNOWN=4;
    static final int TURN_LEFT=0;
    static final int TURN_STRAIGHT=1;
    static final int TURN_RIGHT=2;
    

    static int count=0;
    
    public Cart(Entry loc, char thechar){
	cartno=count;
	count++;
	location=loc;
	direction=charToDirection(thechar);
	grid=loc.getGrid();
	turn=0;
    }

    void turn(int turn){
	if(turn==TURN_LEFT){
	    direction++;
	    if(direction>RIGHT) direction=UP;
	}
	if(turn==TURN_RIGHT){
	    direction--;
	    if(direction<UP) direction=RIGHT;
	}
    }

    public int charToDirection(char ch){
	switch (ch){
	case '>':
	    return RIGHT;
	case '<':
	    return LEFT;
	case '^':
	    return UP;
	case 'v':
	    return DOWN;
	}
	return UNKNOWN;

    }

    public void setMoved(boolean b){
	moved=b;
    }

    public boolean hasMoved(){
	return moved;
    }

    public boolean move(){
	int x=location.getX();
	int y=location.getY();
	location.setCart(null);
	switch (direction){
	case RIGHT:
	    x++;
	    break;
	case LEFT:
	    x--;
	    break;
	case UP:
	    y--;
	    break;
	case DOWN:
	    y++;
	    break;
	}
	location=grid.get(x,y);
	if(location.isCart()){
	    location.setCrash(true);
	    return false;
	}
	if(location.getChar()=='\\'){
	    switch(direction){
	    case DOWN:
	    case UP:
		turn(TURN_LEFT);
		break;
	    case LEFT:
	    case RIGHT:
		turn(TURN_RIGHT);
		break;
	    default:
		throw new IllegalArgumentException("Cart going "+direction+" cannot turn \\");
	    }
	}

	if(location.getChar()=='/'){
	    switch(direction){
	    case DOWN:
	    case UP:
		turn(TURN_RIGHT);
		break;
	    case LEFT:
	    case RIGHT:
		turn(TURN_LEFT);
		break;

	    default:
		throw new IllegalArgumentException("Cart going "+direction+" cannot turn /");
	    }
	}

	if(location.getChar()=='+'){
	    turn(turn);
	    turn++;
	    if(turn==3) turn=0;

	}
	 
	location.setCart(this);
	
	return true;

    }

    public int getX(){
	return location.getX();
    }
    public int getY(){
	return location.getY();
    }

	

    public char getChar(){
	return directionToChar(direction);
    }

    public char directionToChar(int direction){
	switch (direction){
	case RIGHT:
	    return '>';
	case LEFT:
	    return '<'; 
	case UP:
	    return '^'; 
	case DOWN:
	    return 'v'; 
	}
	return ' ';
    }    
}



