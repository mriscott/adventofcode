
public class Day15{

    Generator gena;
    Generator genb;

    public Day15(long starta, long startb){
	gena=new Generator(starta,16807,4);
	genb=new Generator(startb,48271,8);
    }

    public int iterate(int num){
	if(num==0) num=5000000;
	int match=0;
	boolean debug=false;
	if(debug) System.out.println("--Gen, A--\t--Gen. B--");
	for(int x=0;x<num;x++){
	    if(x%1000==0) System.out.print("\rIteration "+x+"/"+num);
	    long aval=gena.generateValue() & 0xffff;
	    long bval=genb.generateValue() & 0xffff;
	    if(debug) System.out.println(gena.getValue()+"\t"+genb.getValue());
	    if(aval==bval) match++;
	}
	System.out.println("");
	return match;
    }
    public static boolean test(String name,long a,long b,int it, long val){
	Day15 tester=new Day15(a,b);
	System.out.println("Testing ....");
	int matches = tester.iterate(it);
	if(matches!=val){
	    System.out.println("Test "+name+" FAILED");
	    System.out.println("Got "+matches+", expected "+val);
	    return false;
	}
	System.out.println("Test "+name+" passed");
	return true;
    }

    
    public static void  main(String [] args){
	if(!test("short run",65,8921,5,0)) return;
	if(!test("long run", 65,8921,1055,0)) return;
	if(!test("first match", 65,8921,1056,1)) return;
	if(!test("full run", 65,8921,0,309)) return;
	Day15 real = new Day15(783,325);
	int matches=real.iterate(0);
	System.out.println("Matches:"+matches);
    }
}

class Generator{
    long value;
    long factor;
    static final long divisor=2147483647;
    int verify;
    
    public Generator(long val, long fac, int v){
	value=val;
	factor=fac;
	verify=v;
    }

    public long getValue(){
	return value;
    }
    
    public long generateValue(){
	long newval=value*factor;
	value=newval % divisor;
	if(value % verify!=0) value=generateValue();
	return value;
    }

    
    
}
