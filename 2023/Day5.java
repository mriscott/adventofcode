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
				 //if(true) return;
				 Day5 input=new Day5("day5/input");
				 test( 240320250,input.findLowestLocation());
				 System.out.println("Part 1: "+input.findLowestLocation());
				 System.out.println("Part 2: "+input.findLowestLocation2());
				 System.out.println("Part 2: 28580590 too high");
				 //System.out.println("Part 2: 50952572 also too high");
				 

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
	long tillChange=-1;

	long seedToLoc(long num){
		String from="seed";
		tillChange=-1;
		while(true){
			Mapper m =(Mapper)mappers.get(from);
			String to=m.to;
			long newnum=m.convert(num);
			if(newnum!=num){
				if(tillChange==-1 || tillChange>m.tillChange){
					tillChange=m.tillChange;
				}
			}
			debug (from+" "+num+" -> "+to+" "+newnum+" ("+tillChange+")");
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
		for(int x=0;x<seeds.length;x+=2){
			long lo=seeds[x];
			long range=seeds[x+1];
			System.out.println("Seed:"+lo+" range:"+range);
			for (long n=0;n<range;n++){
				long loc=seedToLoc(n+lo);
				if(min>loc) min=loc;
				if(tillChange>0){
					debug("Skipping "+tillChange);
					n+=tillChange-1;
					tillChange=-1;
					
				}
			}
		}
			
			
		long end=new java.util.Date().getTime();
		long timetook=(long)((end-start)/1000);
		System.out.println("Took "+timetook+"s");
		
		return min;
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
			for(Object x : ranges){
				Range rr=(Range)x;
				//				if(rr.overlaps(r)) throw new IllegalArgumentException("Range "+rr+" and "+ r+" overlap");
			}
			ranges.add(r);
		}


		long convert(long in){
			tillChange=-1;
			long retval=in;
			for(Object r:ranges){
				Range rr=(Range)r;
				long out=rr.convert(in); 
				if(out!=in) {
					tillChange=rr.tillChange;
					if(retval!=in) throw new RuntimeException("Overlapping ranges:"+rr);
					retval=out;
				}

			}
			
			return retval;
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

			public String toString(){
				return(""+destStart+","+sourceStart+","+length);
			}

			public boolean overlaps(Range r){
				if(sourceStart<r.sourceStart){
					if(length<=(r.sourceStart-sourceStart)) {
						System.out.println(""+r+" before "+this);
						return false;
					}else{
						System.out.println(""+r+" overlaps "+this+" from below");
						return true;
					}
				}else{
					if(r.length<=(sourceStart-r.sourceStart)) {
						System.out.println(""+r+" after "+this);
						return false;
					}else{
						System.out.println(""+r+" overlaps "+this+" from above");
						return true;
					}
				}

			}

			public long convert(long in){
				tillChange=-1;
				if(in>=sourceStart && in<(sourceStart+length)){
					tillChange=sourceStart+length-in;
					long oin=in;
					in+=(destStart-sourceStart);
					debug(" - "+oin+"->"+in+"("+tillChange+")");
					return in;
				}
				return in;
			}
		}
	}
}
