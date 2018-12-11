public class Rack{
    int serial;
    Point [][] points;
    int size=300;
    
    public static void main(String [] args){
	//runTests();
	Rack me = new Rack(1955);
	//Rack me = new Rack(42);
	int max=0;
	int maxgrid=0;
	for (int s=1;s<300;s++){
	    int maxs=me.printMaxPower(s);
	    if(maxs>max) {
		max=maxs;
		maxgrid=s;
	    }
	    if(maxs==-999) break;
	}
	System.out.println("\n\nMax Power "+max+" with grid of "+maxgrid);
	me.printMaxPower(maxgrid);

    }
    
    static void runTests(){
	test(8,3,5,4);
	test(57,122,79,-5);
	test(39,217,196,0);
	test(71,101,153,4);
	testMaxPower(42,3,30,21,61);
	testMaxPower(18,3,29,33,45);
    }

    public static void testMaxPower(int serial, int gridsize, int power, int x, int y){
	Rack t = new Rack(serial);
	System.out.println("Checking :"+power+" at "+x+","+y);
	int p=t.printMaxPower(gridsize);
	if(p!=power){
	    throw new RuntimeException("Max power should be "+power+" not "+p);
	}

    }

    public int printMaxPower(int gridsize){
	int max=-999;
	int xx=-999;
	int yy=-999;
	for (int y=0;y<(size-gridsize);y++){
	    for (int x=0;x<(size-gridsize);x++){
		int gridPower=getGridPower(x,y,gridsize);
		if(gridPower>max){
		    max=gridPower;
		    xx=x;
		    yy=y;
		}
	    }
	}
	System.out.println("Max Power for "+gridsize+":"+max+" at "+xx+","+yy);
	return max;
    }

    int getGridPower(int xx, int yy, int gridsize){
	int pow=0;
	for (int y=yy;y<yy+gridsize;y++){
	    for (int x=xx;x<xx+gridsize;x++){
		pow+=getPower(x,y);
	    }
	}
	return pow;
    }

    public static boolean test(int s,int x, int y, int pow){
	Rack t = new Rack(s);
	int p =t.getPower(x,y);
	if(p!=pow){
	    throw new RuntimeException("Serial "+s+" - "+x+","+y+" should be "+pow+" not "+p);
	}
	System.out.println("Test passed");
	return true;
    }

    public Rack(int s){
	points = new Point[size][size];
	serial=s;
    }

    public int getPower(int x, int y){
	if (points[x][y]==null){
	    points[x][y]=new Point(serial,x,y);
	}
	return points[x][y].getPower();
    }
}

class Point{
    int x,y;
    int power;
    boolean powercalc=false;
    int serial;

    public Point(int s, int xx, int yy){
	serial=s;
	x=xx;
	y=yy;
    }

    public int getPower(){
	if (!powercalc){
	    power=calcPower();
	    powercalc=true;
	}
	return power;
    }

    int calcPower(){
	int rackid=x+10;
	int power=rackid*y;
	power+=serial;
	power*=rackid;
	int hundreds=(power/100)%10;
	power=hundreds-5;
	return power;

    }
}
