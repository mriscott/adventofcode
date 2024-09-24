import java.io.*;
public abstract class Utils
{
    static boolean debug=false;

    public static void debug (String str){
	if(debug) System.out.println(str);
    }

    public static void setDebug(boolean d){
	debug=d;
    }
    public static void test(long x, long y){
	if (x!=y) throw new RuntimeException("Test failed: Expected "+x+", got "+y);
    }

    public static void test(int x, int y){
	if (x!=y) throw new RuntimeException("Test failed: Expected "+x+", got "+y);
    }
    public void read(String filename) throws IOException {
	debug("Reading "+filename);
	LineNumberReader fr=new LineNumberReader(new FileReader(new File(filename)));
	String line=fr.readLine();
	while(line!=null){
	    process(line);
	    line=fr.readLine();
	}
	fr.close();
    }
    
    public void safeRead(String filename){
		try{
		    read(filename);
		}catch(IOException e){
		    e.printStackTrace();
		    throw new IllegalArgumentException("Problem reading "+filename);
		}
    }
    
    abstract void process(String line);
    

}
