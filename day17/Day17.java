import java.util.*;

public class Day17 {

    LinkedList list=new LinkedList();
    int pos=0;

    int increment;

    public Day17(int i){
	increment=i;
    }

    int forward(int i, int listsize){
	pos+=i;
	while(pos<0) pos+=listsize;
	if(listsize!=0) pos= pos % listsize;
	else pos=0;
	return pos;
    }
    int forward(int i){
	return forward(i,list.size());
    }
    
    public static void main(String [] args){
	Day17 t = new Day17(382);
	t.go();
    }


    void go(){
	int tot=0;
	long afterzero=0;
	int listsize=1;
	long zeropos=0;
	boolean longway=false;
	int it=50000000;
	if(longway){
	    //list.add(new Long(0));			 
	    for(long x=0;x<it;x++){
		forward(increment);
		long val=x==0?-1:((Long)list.get(pos)).longValue();
		
		if((pos+1)==list.size()){
		    list.add(new Long(x));
		    pos++;
		}else {
		    forward(1);
		    list.add(pos,new Long(x));
		}

	    }
	}else{
	    listsize=1;
	    zeropos=0;
	    for(long x=1;x<it;x++){
		forward(increment,listsize);
		listsize++;
		if(pos==zeropos) {
		    afterzero=x;
		}
		forward(1,listsize);
		if(pos<=zeropos) zeropos++;
		/*
		for(int y=0;y<listsize;y++){
		    if(y==zeropos) System.out.print("0");
		    else if(y==pos) System.out.print(x);
		    if(y!=zeropos && y!=pos) System.out.print(".");
		}
		
		System.out.println("");
		*/
		//forward(1,listsize);
	    }
	}

	forward(1);
	if(!longway) System.out.println("After zero:"+afterzero);
	if(longway) System.out.println("After zero:"+getAfterZero());
	
    }

    long getAfterZero(){
	for(int x=0;x<list.size();x++){
	    if(((Long)list.get(x)).longValue()==0){
		x++;
		if(x==list.size()) x=0;
		return ((Long)list.get(x)).longValue();
	    }
	}
	return -1;
    }

    public String toString(){
	StringBuffer buf=new StringBuffer();
	for(int x=0;x<list.size();x++){
	    if(x==pos){
		buf.append("(");
		buf.append(list.get(x).toString());
		buf.append(")");
	    }else{
		buf.append(list.get(x).toString());
	    }
	    buf.append(",");
		
	}
	return buf.toString();
    }

}
