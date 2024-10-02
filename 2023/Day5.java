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
				 Day5 input=new Day5("day5/input");
				 test( 240320250,input.findLowestLocation());
				 System.out.println("Part 1: "+input.findLowestLocation());
				 System.out.println("Part 2: "+input.findLowestLocation2());
				 
				 

	}
	public Day5(String file){
		this(file,1);
	}
	public Day5 (String file, int part){
		this.part=part;
		mappers = new Hashtable();
		safeRead(file);
	}

	long seedToLoc(long num){
		String from="seed";
		while(true){
			Mapper m =(Mapper)mappers.get(from);
			String to=m.to;
			long newnum=m.convert(num);
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
		long min=Long.MAX_VALUE;
		for(int x=0;x<seeds.length;x++){
			long lo=seeds[x];
			x++;
			long range=seeds[x];
			System.out.println("Seed:"+lo+" range:"+range);
			long hi=lo+range;
			for (long n=lo;n<=hi;n++){
				long num=seedToLoc(n);
				debug("---");
				if(min>num) min=num;
			}

		}	
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
				if(in>=rr.sourceStart && in<=(rr.sourceStart+rr.length)){
					in+=(rr.destStart-rr.sourceStart);
					return in;
				}
			}
			return in;
		}
		class Range{
			long destStart,sourceStart,length;
			public Range(long x,long y, long z){
				destStart=x;
				sourceStart=y;
				length=z;
			}
		}
	}
}
