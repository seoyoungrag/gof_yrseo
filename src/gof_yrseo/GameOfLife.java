package gof_yrseo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameOfLife {

	public static void main(String[] args) throws InterruptedException {
		if(args.length == 0) {
			System.out.println("random state board start");
			//일단 pseudo로 가로, 세로, 랜덤을 위한 세포생존임의확률값을 부여한다.
	        Board b = new Board(80, 40, 0.1);
	        b.displayBoard();
	        while(true) {
	        	b.displayBoard();
	            b.changeBoardCells();
	            Thread.sleep(300);
	        }
		}else if(args.length == 1) {
	        try{
	        	FileReader filereader = new FileReader(args[0]);
	            BufferedReader bufReader = new BufferedReader(filereader);
	            String line = "";
	            while((line = bufReader.readLine()) != null){
	            	//TODO 인자값을 입력받아 생성한다.
	            	//첫줄 - 가로 세로
	            	//둘째줄 - 셀 개수
	            	//셋째줄 - 셀의 위치
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
