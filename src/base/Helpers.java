package base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Helpers 
{
	public static int getGreatest(int one, int two)
	{
		return one > two ? one : two;
	}
	public static int getSmallest(int one, int two)
	{
		return one > two ? two : one;
	}
	
    public static void readFileToString(String path, StringBuilder output) throws FileNotFoundException, IOException {
    	if(output == null)
    	{
    		output = new StringBuilder();
    	}
    	FileReader fileReader = new FileReader(path);
		int readByte = fileReader.read();
		while(readByte != -1) {
			output.append((char)readByte);
			readByte = fileReader.read();
		}
		fileReader.close();
    }
    
	public static void writeDataToFile(String filename, String data) throws IOException
	{
    	try(FileWriter fileWriter = new FileWriter(filename))
    	{
    		fileWriter.write(data);
    	}
	}

}
