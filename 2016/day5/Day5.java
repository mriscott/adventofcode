import java.security.*;

public class Day5{

	public static boolean PART1=false;
	public static boolean PART2=true;
	public static boolean SKIPTEST=true;

	MessageDigest md;
	String doorid;
	public static void main(String [] args){
		try{
			if (!SKIPTEST){
				Day5 test=new Day5("abc");
				if (PART1){
					if("18f47a30".equals(test.getPassword())){
						System.out.println("Test passed");
					}else{
						System.out.println("TEST FAILED");
						System.exit(1);
					}
				}
				if (PART2){

					if("05ace8e3".equals(test.getPassword2())){
						System.out.println("Test passed");
					}else{
						System.out.println("TEST FAILED");
						System.exit(1);
					}
				}
				
			}
			Day5 real=new Day5("cxdnnyjw");
			if (PART1) System.out.println("Answer:"+real.getPassword());
			if (PART2) System.out.println("Answer:"+real.getPassword2());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Day5(String id){
		doorid=id;
	}

	public String getPassword() throws Exception{
		String password="";
		long i=0;
		while(password.length()<8){
		//	if (i%1000000==0) System.out.println(" ... "+i+" ...");
			String digest=getDigest(doorid+i);

			if(digest.startsWith("00000")){
				System.out.println(doorid+i+" => "+digest);
				password+=digest.charAt(5);
				System.out.println(password);
			}
			i++;
		}
		return password;
	}

	public String getPassword2() throws Exception{
		String password="--------";
		char [] cpassword=password.toCharArray();
		long i=0;
		while(password.indexOf("-")!=-1){
		//	if (i%1000000==0) System.out.println(" ... "+i+" ...");
			String digest=getDigest(doorid+i);

			if(digest.startsWith("00000")){
				System.out.println(doorid+i+" => "+digest);
				char index=digest.charAt(5);
				char ch=digest.charAt(6);
				if (index>='0' && index <'8'){
					int in=index-'0';
					if (cpassword[in]=='-') cpassword[in]=ch;
					password=new String(cpassword);
				}
				System.out.println(password);
			}
			i++;
		}
		return password;
	}

	public String getDigest(String input) throws Exception{
		if(md==null) md = MessageDigest.getInstance("MD5");
		byte[] bytesOfMessage = input.getBytes("UTF-8");
		byte[] theMD5digest = md.digest(bytesOfMessage);
		return bytesToHex(theMD5digest);
			
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

