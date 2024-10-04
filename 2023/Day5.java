import java.util.*;
public class Day5 extends Utils{

	
	int part=1;
	long []seeds;
	Hashtable mappers;
	Mapper mapper;
	public static void main(String [] args){
				 setDebug(false);
				 Day5 test=new Day5("day5/test");
				 test(35L,test.findLowestLocation());
				 test(46,test.findLowestLocation2());
				 System.out.println("Tests passed");
				 Day5 input=new Day5("day5/input");
				 test( 240320250,input.findLowestLocation());
				 System.out.println("Part 1: "+input.findLowestLocation());
				 System.out.println("Part 2: "+input.findLowestLocation2());
				 System.out.println("Part 2: 28580590 too high");
				 System.out.println("Part 2: 50952572 also too high");
				 

	}
	public Day5(String file){
		this(file,1);
	}
	public Day5 (String file, int part){
		this.part=part;
		mappers = new Hashtable();
		safeRead(file);
	}
	long diff=0;
	long tillChange=0;

	long seedToLoc(long num){
		String from="seed";
		while(true){
			Mapper m =(Mapper)mappers.get(from);
			String to=m.to;
			long newnum=m.convert(num);
			if(newnum!=num){
				if(tillChange!=-1 && tillChange<m.tillChange){
					tillChange=m.tillChange;
				}
			}
			debug (from+" "+num+" -> "+to+" "+newnum);
			num=newnum;
			if(to.equals("location")){
				break;
			}
			from=to;
		}
		return num;
	}


	long findLowestLocation(){
		long min=Long.MAX_VALUE;
		for(int x=0;x<seeds.length;x++){
			long num=seedToLoc(seeds[x]);
			debug("---");
			if(min>num) min=num;

		}	
		return min;
	}

	long findLowestLocation2(){
		long start=new java.util.Date().getTime();
		long min=Long.MAX_VALUE;
		CalcThread [] threads=new CalcThread[seeds.length/2];
		int tnum=0;
			for(int x=0;x<seeds.length;x++){
				long lo=seeds[x];
				x++;
				long range=seeds[x];
				System.out.println("Seed:"+lo+" range:"+range);
				long hi=lo+range;			
				CalcThread thr=new CalcThread(lo,hi,this,tnum);
				thr.start();
				threads[tnum]=thr;
				tnum++;
			}
			for(int x=0;x<tnum;x++){
				try{
					System.out.println("Waiting for thread "+x);
					threads[x].join();
					if(threads[x].min<min){
						min=threads[x].min;
					}
				}catch(InterruptedException e){
					// bother
					throw new RuntimeException("It's all gone Pete Tong");
				}
			}
			long end=new java.util.Date().getTime();
			long timetook=(long)((end-start)/1000);
			System.out.println("Took "+timetook+"s");

			return min;
		}

		class CalcThread extends Thread     {
			long min=Long.MAX_VALUE;
			long start,end;
			Day5 parent;
			int tnum;

			public CalcThread(long start, long end,Day5 parent,int tnum){
				this.start=start;
				this.end=end;
				this.parent=parent;
				this.tnum=tnum;
			}
		
			public void run(){
				long time=new java.util.Date().getTime();
				System.out.println("Thread "+tnum+ " started");
				for (long n=start;n<end;n++){
					synchronized(parent){
						long num=parent.seedToLoc(n);
						debug("---");
						if(min>num) min=num;
						if(parent.tillChange>0){
							n+=tillChange;
							tillChange=-1;
						}
					}
				}
				long took=new java.util.Date().getTime()-time;
				took=(long)took/1000;
				System.out.println("Thread "+tnum+ " ended after "+took+"s");
				System.out.println("Thread "+tnum+" min:"+min);

			}

		
		}
	
  
	void process (String line){
		try {
			if (line.startsWith("seeds:")){
				String seedLine=line.substring(7);
				seeds=strToInts(seedLine);
			}
			if(line.contains("-to-")){
				line=line.replaceAll(" map:","");
				mapper=new Mapper(line);
				mappers.put(mapper.from,mapper);
			}
			if(line.matches("[0-9].*")){
				long [] range=strToInts(line);
				mapper.addRange(range[0],range[1],range[2]);
			}		
		} catch(NumberFormatException e){
			e.printStackTrace();
			throw new RuntimeException("Bad line "+line);
		}
		
	}

	long [] strToInts(String str) throws NumberFormatException{
		str=str.trim();
		String [] nums=str.split(" ");
		long[] ints=new long[nums.length];
		for(int x=0;x<nums.length;x++){
			ints[x]=Long.parseLong(nums[x]);
		}
		return ints;
	}

	class Mapper{
		String from;
		String to;
		List ranges;
		long tillChange;
		public Mapper(String name){
			String [] spl=name.split("-");
			if((spl.length!=3) || !spl[1].equals("to")) throw new IllegalArgumentException("Bad map "+name);
			from=spl[0];
			to=spl[2];
			ranges = new ArrayList();
		}
		public void addRange(long a, long b, long c){
			Range r=new Range(a,b,c);
			ranges.add(r);
		}

		long convert(long in){
			for(Object r:ranges){
				Range rr=(Range)r;
				long out=rr.convert(in);
				if(out!=in) {
					tillChange=rr.tillChange;
					return out;
				}

			}
			return in;
		}
		class Range{
			long tillChange;
			long destStart,sourceStart,sourceEnd,offset,length;
			public Range(long x,long y, long z){
				destStart=x;
				sourceStart=y;
				length=z;
				offset=destStart-sourceStart;
				sourceEnd=sourceStart+z;
			}

			public long convert(long in){
				if(in>=sourceStart && in<=(sourceStart+length)){
					tillChange=sourceStart+length-in;
					in+=(destStart-sourceStart);
					return in;
				}
				return in;
			}
		}
	}
}
