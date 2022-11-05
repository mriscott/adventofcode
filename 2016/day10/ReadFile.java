import java.io.*;
public abstract class ReadFile
{
	public void read(String filename) throws IOException {
		System.out.println("Reading "+filename);
		LineNumberReader fr=new LineNumberReader(new FileReader(new File(filename)));
		String line=fr.readLine();
		while(line!=null){
	    process(line);
	    line=fr.readLine();
		}
		fr.close();
	}

	abstract void process(String line);


}
