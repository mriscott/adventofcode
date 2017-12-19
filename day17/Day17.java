import java.util.*;

public class Day17 {

    LinkedList list=new LinkedList();
    int pos=0;

    int increment;

    public Day17(int i){
	increment=i;
    }

    int forward(int i){
	pos+=i;
	while(pos<0) pos+=list.size();
	if(list.size()!=0) pos= pos % list.size();
	else pos=0;
	return pos;
    }
    
    public static void main(String [] args){
	Day17 t = new Day17(382);
	t.go();
    }


    void go(){
	for(int x=0;x<2018;x++){
	    forward(increment);
	    if((pos+1)==list.size()){
		list.add(new Integer(x));
		pos++;
	    }else {
		forward(1);
		list.add(pos,new Integer(x));
	    }
	    //System.out.println(this);
	}
	forward(1);
	System.out.println(list.get(pos));
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
