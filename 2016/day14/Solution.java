import java.security.*;
import java.util.*;

public class Solution
{
	List <Key>poss;
	List <Key>keys;
	String salt;
	int KEYS=65;
	

	public class Key{
		int id;
		String hash;
		String triples;
		String pents;
		MessageDigest md;

		public Key(int id){
			this.id=id;
			hash=getDigest(""+salt+id);
			findRuns();
		}

		void findRuns(){
			char lastch=' ';
			char ch=' ';
			int run=0;
			for (int i=0;i<hash.length();i++){
				ch=hash.charAt(i);
				if(ch!=lastch) run=0;
				else run++;
				lastch=ch;
				if(run==2) addTriple(ch);
				if(run==4) addPent(ch);
			}
		}

		void addTriple(char ch){
			if(triples==null) triples="";
			triples=triples+ch;
		}

		void addPent(char ch){
			if(pents==null) pents="";
			pents=pents+ch;
		}

		public boolean hasTriple(){
			return triples!=null;
		}

		public boolean hasPent(){
			return pents!=null;
		}

		public boolean hasPent(char ch){
			return pents!=null && pents.indexOf(ch)!=-1;
		}		

		public String getDigest(String input) {
			try{
				if(md==null) md = MessageDigest.getInstance("MD5");
				byte[] bytesOfMessage = input.getBytes("UTF-8");
				byte[] theMD5digest = md.digest(bytesOfMessage);
				return bytesToHex(theMD5digest);
			}catch (Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
		
		public String bytesToHex(byte[] bytes) {
			char[] hexArray = "0123456789abcdef".toCharArray();
			char[] hexChars = new char[bytes.length * 2];
			for ( int j = 0; j < bytes.length; j++ ) {
				int v = bytes[j] & 0xFF;
				hexChars[j * 2] = hexArray[v >>> 4];
				hexChars[j * 2 + 1] = hexArray[v & 0x0F];
			}
			return new String(hexChars);
		}
		
	}


	public static void main(String [] args){
		Solution test=new Solution("abc");
		test.runTests();
		Solution real=new Solution("cuanljph");
		System.out.println("Part1: "+real.keys.get(63).id);
	}
		
	public Solution(String thesalt){
		salt=thesalt;
		poss=new ArrayList<Key>();
		keys=new ArrayList<Key>();
		findKeys();
	}

	void addPossible(int x){
		Key key=new Key(x);
		if(key.hasTriple()){
			poss.add(key);
		}
	}
	
	void findKeys(){
		// populate first 1000
		for(int x=0;x<=1000;x++){
			addPossible(x);
		}
		// now loop through looking for keys
		Key nextposs=poss.get(0);
		poss.remove(0);
		keys:for(int x=0;keys.size()<KEYS;x++){
			addPossible(x+1000);
			if(x==nextposs.id){

					char ch=nextposs.triples.charAt(0);
					for (Key key:poss){
						if(key.hasPent(ch)){
							keys.add(nextposs);
							System.out.println(" - "+keys.size()+" "+nextposs.id+"("+ch+") "+nextposs.hash+" <- "+key.id+" "+key.hash);
							if(key.id<=nextposs.id) throw new RuntimeException("error in "+nextposs.id);
							if(key.id-nextposs.id>999) throw new RuntimeException("error in "+nextposs.id);
							nextposs=poss.get(0);
							poss.remove(0);
							
							continue keys;
						}
					}
					
					nextposs=poss.get(0);
					poss.remove(0);
				}
			
			if(x>300000) throw new RuntimeException("out of numbers");
		}
	}

	void runTests(){
		System.out.print("Check 18 is a triple...");
		Key key18=new Key(18);
		if(key18.hasTriple()==false) throw new IllegalArgumentException ("Key 18 should have a triple");
		System.out.println(key18.hash+" OK");

		Key firstKey=keys.get(0);
		if(firstKey.id!=39) throw new RuntimeException("First key should be 39");
		Key second=keys.get(1);
		if(second.id!=92) throw new RuntimeException("First key should be 92");
		Key sixtyFour=keys.get(63);
		if(sixtyFour.id!=22728) throw new RuntimeException("Last key wrong: "+sixtyFour.id);
	}

	
	

}

