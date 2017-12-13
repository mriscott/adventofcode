import java.util.*;

public class Day13{

    Layer[] levels;
    
    public static void main(String [] args){
	System.out.println("Testing example");
	Day13 example=new Day13(true);
	int severity = example.cross(0,false);
	if(severity!=24){
	    System.out.println("Severity should be 24 - found "+severity);
	    System.exit(1);
	}


	int delay = example.getDelay();
	
	if(delay!=10){
	    System.out.println("Delay should be 10 - found "+delay);
	    System.exit(1);
	}
	Day13 real=new Day13(false);
        System.out.println("Real severity:"+real.cross(0,false));
	
        System.out.println("Real delay:"+real.getDelay());
    }

    public Day13(boolean test){
	if(test) setUpExample();
	else setUpData();
    }

    public int getDelay(){
	int delay=0;
	while(cross(delay,true)!=-1) {
	    //System.out.println("Caught after "+delay+" ps wait");
	    delay++;
	}
	    
	return delay;
    }
    

    public void setUpExample(){
	levels=new Layer[7];
	levels[0] = new Layer(3);
	levels[1] = new Layer(2);
	levels[4] = new Layer(4);
	levels[6] = new Layer(4);
    }

    public void setUpData(){
	levels = new Layer[89];	    
	levels[0] = new Layer( 3);
	levels[1] = new Layer( 2);
	levels[2] = new Layer( 4);
	levels[4] = new Layer( 4);
	levels[6] = new Layer( 5);
	levels[8] = new Layer( 8);
	levels[10] = new Layer( 6);
	levels[12] = new Layer( 6);
	levels[14] = new Layer( 6);
	levels[16] = new Layer( 6);
	levels[18] = new Layer( 8);
	levels[20] = new Layer( 8);
	levels[22] = new Layer( 12);
	levels[24] = new Layer( 10);
	levels[26] = new Layer( 9);
	levels[28] = new Layer( 8);
	levels[30] = new Layer( 8);
	levels[32] = new Layer( 12);
	levels[34] = new Layer( 12);
	levels[36] = new Layer( 12);
	levels[38] = new Layer( 12);
	levels[40] = new Layer( 8);
	levels[42] = new Layer( 12);
	levels[44] = new Layer( 14);
	levels[46] = new Layer( 14);
	levels[48] = new Layer( 10);
	levels[50] = new Layer( 12);
	levels[52] = new Layer( 12);
	levels[54] = new Layer( 14);
	levels[56] = new Layer( 14);
	levels[58] = new Layer( 14);
	levels[62] = new Layer( 12);
	levels[64] = new Layer( 14);
	levels[66] = new Layer( 14);
	levels[68] = new Layer( 14);
	levels[70] = new Layer( 12);
	levels[74] = new Layer( 14);
	levels[76] = new Layer( 14);
	levels[78] = new Layer( 14);
	levels[80] = new Layer( 18);
	    levels[82] = new Layer( 17);
	    levels[84] = new Layer( 30);
		levels[88] = new Layer( 14);
    }

    int cross(int wait, boolean shortcut){
	//	System.out.println("\n\nDelay:"+wait);
	//if(wait%1000==0) System.out.println(wait);
	reset();
	moveOn(wait);
	//for(int x=0;x<wait;x++) moveAll();
	int severity=0;
	boolean caught=false;
	for(int x=0;x<levels.length;x++){
	    //printAll(x);
	    if(shortcut){
		if(isCaught(x)){
		    caught=true;
		    break;
		}
	    }
	    if(isCaught(x)){
		severity+=calcSeverity(x);
		caught=true;
	    }
	    moveAll();	    
	}
	if(caught==false) return -1;
	return severity;
    }

    void printAll(int x){
	System.out.println("After "+x+":");
	for(int y=0;y<levels.length;y++){
	    System.out.print(y+")");
	    if(levels[y]!=null) levels[y].debugPrint(x==y?0:-1);
	    else if(y==x) System.out.print("x");
	    System.out.println("");
			     
	}
    }

    void moveOn(int wait){
	for(int x=0;x<levels.length;x++){
	    if(levels[x]!=null) {
		levels[x].moveOn(wait);
	    }	    
	}
    }
    
    void moveAll(){
	for(int x=0;x<levels.length;x++){
	    if(levels[x]!=null) {
		levels[x].move();
	    }	    
	}
    }

    void reset(){
	for(int x=0;x<levels.length;x++){
	    if(levels[x]!=null) {
		levels[x].reset();
	    }
	}
    }

    int calcSeverity(int x){
	if(!isCaught(x)) return 0;
	return x*levels[x].getDepth();
    }

    boolean isCaught(int x){
	Layer level=levels[x];
	if(level==null) return false;
	if(level.getPos()!=0) return false;
	//System.out.println("Caught in level "+x);
	return true;
    }
	

    class Layer{
	int depth;
	int pos;
	int direction;
       
	
	public Layer(int size){
	    depth=size;
	    reset();
	}

	public void reset(){
	    pos=0;
	    direction=+1;
	}

	public int getDepth(){
	    return depth;
	}

	public int getPos(){
	    return pos;
	}

	public void move(){
	    pos+=direction;
	    if(pos==0) direction=1;
	    if(pos==(depth-1)) direction=-1;
	}

	public void moveOn(int x){
	    if(pos!=0) throw new IllegalStateException("Cant run moveon when not at start");
	    int steps = x%((depth*2)-2);
	    for(int y=0;y<steps;y++) move();
	}

	

	public void debugPrint(int x){	    
	    for(int y=0;y<depth;y++){
		if(y==pos && y==x) System.out.print("*");
		else if(y==pos) System.out.print("s");
		else System.out.print((x==0&&y==0)?"x":".");
	    }
	}
	    
    }
}
    
