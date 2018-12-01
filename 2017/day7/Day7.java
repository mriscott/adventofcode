import java.io.*;
import java.util.*;

public class Day7
{
    String filename; 
    private Hashtable progs=new Hashtable();
    
    public static void main(String [] args){
	if (args.length!=1){
	    System.out.println("No filename");
	}
	try {
	    Day7 me = new Day7(args[0]);
	    me.go();
	} catch(Exception e){
	    e.printStackTrace();
	}
    }

    public Day7(String file) {
	filename=file;
    }

    public void go() throws IOException{
	System.out.println("Reading "+filename);
	readFile();
	System.out.println("\n\nRoot Program:");
	System.out.println(getRoot());
	System.out.println("\n\nStack:");
	Program u = printStack();
	System.out.println("Unbalanced:"+u);
	System.out.println("Balancing:");
	//get("ugml").setWeight("60");
	get("aobgmc").setWeight("333");
	u=printStack();
	if (u==null) System.out.println("SUCCESS");

	   
    }

    public Program getRoot(){
	Enumeration e = progs.elements();
	while(e.hasMoreElements()){
	    Program p = (Program)e.nextElement();
	    if(p.getParent()==null){
		return p;
	    }
	}
	return null;
    }

    public Program printStack(){
	Program unbalanced=null;
	Enumeration e = progs.elements();
	while(e.hasMoreElements()){
	    Program p = (Program)e.nextElement();
	    System.out.println(p);
	    Program[] k = p.getChildren();
	    int balanceweight=-1;
	    for(int x=0;x<k.length;x++){
		int tower = k[x].getTowerWeight();
		if(balanceweight==-1) balanceweight=tower;
		if(tower!=balanceweight) unbalanced=p;
		System.out.println(" - "+tower+" - "+k[x]);
	    }
	}
	return unbalanced;
    }

    void processLine(String line) throws NumberFormatException{
	if(line==null || "".equals(line)) return;
	System.out.println(line);
	int i=line.indexOf(" ");
	String name=line.substring(0,i);
	int bs=line.indexOf("(");
	int be=line.indexOf(")");
	String weight=line.substring(bs+1,be);
	Program prog = get(name);
	prog.setWeight(weight);
	int arrow=line.indexOf("->");
	if (arrow!=-1){
	    String kids=line.substring(arrow+3);
	    String [] children=kids.split(",");
	    for(int x=0;x<children.length;x++){
		Program kid=get(children[x]);
		kid.setParent(prog);
	    }
	}
	
    }
    Program get(String name){
	if(name.startsWith(" ")){
	    name=name.substring(1);
	}
	Program ret=(Program)progs.get(name);
	if(ret==null){
	    ret=new Program(name);
	    progs.put(name,ret);
	}
	return ret;
    }
	


    void readFile() throws IOException,NumberFormatException{
	LineNumberReader r=new LineNumberReader(new FileReader(filename));
	String line="";
	while (line!=null){
	    line=r.readLine();
	    processLine(line);
	}
    }

    class Program{
	String name;
	int weight;
	Program parent;
	List children=new ArrayList();
	

	Program(String thename){
	    name=thename;
	}

	public void setWeight(String theweight) throws NumberFormatException{
	    weight=Integer.parseInt(theweight);
	}

	public String toString(){
	    return (name+"("+weight+")");
	}

	public void addChild(Program p){
	    children.add(p);
	}

	public void setParent(Program p){
	    parent=p;
	    p.addChild(this);
	}

	public Program getParent(){
	    return parent;
	}
	public String getName(){
	    return name;
	}

	public int getWeight(){
	    return weight;
	}

	public int getTowerWeight(){
	    int t=getWeight();
	    for(int x=0;x<children.size();x++){
		Program kid=(Program)children.get(x);
		t+=kid.getTowerWeight();
	    }
	    return t;
	}

	Program[] getChildren(){
	    Program [] ret = new Program[children.size()];
	    for(int x=0;x<children.size();x++){
		ret[x]=(Program)children.get(x);
	    }
	    return ret;
	}
    }
}
