public class Day1 extends Utils{
    String filename;
    int cal=0;
    int part=1;
    public static void main(String [] args){
	Day1 test = new Day1("day1/test.1");
	test(142,test.getCal());
	System.out.println("Part 1 tests passed");
	Day1 inp = new Day1("day1/input.1.txt");
	System.out.println("Part 1: "+inp.getCal());
	//setDebug(true);

	Day1 test2 = new Day1("day1/test.1.part2",2);
	test(281, test2.getCal());
	setDebug(false);
	Day1 inp2 = new Day1("day1/input.1.txt",2);
	System.out.println("Part 2: "+inp2.getCal());
    }


    public Day1(String file){
	this(file,1);
    }
    public Day1 (String file, int part){
	this.part=part;
	filename=file;
	safeRead(file);
    }

    int getCal(){
	return cal;
	
    }
    
    void process (String line){
	char first='0';
	char last='0';
	if(part==2){
	    line=line.replaceAll("zerone","01");
	    line=line.replaceAll("twone","21");
	    line=line.replaceAll("eightwo","82");
	    line=line.replaceAll("eighthree","83");
	    line=line.replaceAll("oneight","18");
	    line=line.replaceAll("threeight","38");
	    line=line.replaceAll("fiveight","58");
	    line=line.replaceAll("sevenine","79");
	    line=line.replaceAll("nineight","98");
	    line=line.replaceAll("zero","0");
	    line=line.replaceAll("one","1");
	    line=line.replaceAll("two","2");
	    line=line.replaceAll("three","3");
	    line=line.replaceAll("four","4");
	    line=line.replaceAll("five","5");
	    line=line.replaceAll("six","6");
	    line=line.replaceAll("seven","7");
	    line=line.replaceAll("eight","8");
	    line=line.replaceAll("nine","9");
	}
	for (int x=0;x<line.length();x++){
	    first=line.charAt(x);
	    if(first >= '0' && first <='9') break;
	}
	for (int x=line.length()-1;x>=0;x--){
	    last=line.charAt(x);
	    if(last >= '0' && last <='9') break;
	}
	debug(line + "->" +first+last);
	int num = Integer.parseInt(""+first+last);
	cal+=num;	    
    }
}
