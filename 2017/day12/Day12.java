import java.io.*;
import java.util.*;

public class Day12
{
    Hashtable groups=new Hashtable();
    String filename;
    
    public static void main(String [] args){
	Day12 me = new Day12("input.txt");
	try{
	    me.readFile();
	    while(me.reprocess());
	    me.print();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    public Day12(String f){
	filename=f;
    }

    public void readFile() throws IOException{
	LineNumberReader r = new LineNumberReader(new FileReader(filename));
	while(true){
	    String line = r.readLine();
	    if(line==null) break;
	    addLine(line);
	}
	r.close();
	
    }


    void addLine(String line){
	String [] bits=line.split(" <-> ");
	String key = bits[0];
	String [] others=bits[1].split(", ");
	Set group=get(key);
	for(int x=0;x<others.length;x++){	    
	    group.add(others[x]);
	}
    }


    Set get(String key){
	Set gp = (Set) groups.get(key);
	if(gp==null){
	    gp = new TreeSet();
	    groups.put(key,gp);
	}
	gp.add(key);
	return gp;
    }

    boolean reprocess()
    {
	boolean updated=false;
	Enumeration keys = groups.keys();
	while(keys.hasMoreElements()){
	    String key = (String)keys.nextElement();
	    Set gp = get(key);
	    Set newgp = new TreeSet();
	    newgp.addAll(gp);
	    Iterator i = gp.iterator();
	    while(i.hasNext()){
		String childname=(String)i.next();
		if(childname.equals(key)) continue;
		Set child=get(childname);
		if(newgp.addAll(child)){
		    updated=true;
		}
	    }
	    if(updated) gp.addAll(newgp);
	}
	return updated;
    }

    public void printLine(String key){
	System.out.println(getLine(key,false));
    }

    public String getLine(String key, boolean showkids){
	Set gp = (Set) groups.get(key);
	String output = "("+gp.size()+")";
	
	if(showkids && gp!=null){
	    StringBuffer buf=new StringBuffer();
	    Iterator i = gp.iterator();
	    while(i.hasNext()){
		buf.append(i.next());
		buf.append(":");
	    }
	    output+=buf.toString();
	}
	return output;
    }

    public void print(){
	Enumeration keys = groups.keys();
	Set count = new TreeSet();
	while(keys.hasMoreElements()){
	    String line = getLine((String)keys.nextElement(),true);
	    //System.out.println(line);
	    count.add(line);
	}
	System.out.println("Count:"+count.size());
    }
}
