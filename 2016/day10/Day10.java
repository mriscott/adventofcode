public class Day10{
	class Bot{
		Integer chip1;
		Integer chip2;
		int botno;


		Bot(int no){
			botno=no;
		}
		
		void addChip(int chip){
			if(chip1==null){
				chip1=chip;
			}else{
				if(chip2!=null){
					throw new IllegalArgumentException(toString()+" is full");
				}
				if(chip<chip1){
					chip2=chip1;
					chip1=chip;
				}else{
					chip2=chip;
				}
			}
		}

					
		int poplow(){
			int ret;
			if(chip1==null) {
				ret=chip2;
				chip2=null;
			}else{
				ret=chip1;
				chip1=chip2;
				chip2=null;
			}
			return ret;
		}

		int pophigh(){
			int ret;
			if(chip2==null) {
				ret=chip1;
				chip1=null;
			}else{
				ret=chip2;
				chip2=null;
			}
			return ret;
		}	
		public String toString(){
			return "Bot"+botno+" ("+chip1+","+chip2+")";
		}

					

	}

	public static void main(String []args){
		new Day10().test();
	}
	
	public void test(){
		Bot bot1=new Bot(1);
		System.out.println(bot1);
		bot1.addChip(2);
		bot1.addChip(2);
		System.out.println(bot1);
		System.out.println("High:"+bot1.pophigh());
		System.out.println(bot1);


	}
}
