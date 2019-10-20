package gof_yrseo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameOfLife {

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("random state board start");
		}else if(args.length == 1) {
	        try{
	        	FileReader filereader = new FileReader(args[0]);
	            BufferedReader bufReader = new BufferedReader(filereader);
	            String line = "";
	            while((line = bufReader.readLine()) != null){
	                System.out.println(line);
	            }
	            bufReader.close();
	        }catch (FileNotFoundException e) {
	            System.out.println(args[0]+ "file not found");
	        }catch(IOException e){
	            System.out.println("error occur when file reading: "+args[0]);
	        }
		}else {
			System.out.println("argument error");
		}

	}

}
