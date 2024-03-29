package gof_yrseo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameOfLife {

	public static void main(String[] args) throws InterruptedException {
		if(args.length == 0) {
			System.out.println("random state board start");
			//일단 pseudo로 가로, 세로, 랜덤을 위한 세포생존임의확률값을 부여한다.
	        Random random = new Random();
			int width = random.nextInt(100) + 80;
			int height = random.nextInt(100) + 40;
	        Board b = new Board(width, height, 0.1);
	        b.displayBoard();
	        while(true) {
	        	b.displayBoard();
	            b.changeBoardCells();
	            Thread.sleep(300);
	        }
		}else if(args.length == 1 || args.length == 2) {
			int width = 80;
			int height = 40;
			ArrayList<String> indexOfCells = new ArrayList<String>();
	        try{
	        	FileReader filereader = new FileReader(args[0]);
	            BufferedReader bufReader = new BufferedReader(filereader);
	            String line = "";
				int cellCnt = 0;
				int lineCnt = 0;
				int cellCntChk = 0;
	            while((line = bufReader.readLine()) != null){
	            	//첫번쨰 라인 체크
	            	if(lineCnt == 0 && line != null) {
	            		String[] widthHeight = line.split(" ");
	            		if(widthHeight==null || widthHeight.length !=2) {
	            			System.out.println("argument in file error - first line only has width height.");
	            			System.exit(-1);
	            		}else {
	            			if(isInteger(widthHeight[0])==false || isInteger(widthHeight[1])==false) {
		            			System.out.println("argument in file error - first line,width height has to be int.");
		            			System.exit(-2);
	            			}
	            			if(Integer.parseInt(widthHeight[1]) <80  || Integer.parseInt(widthHeight[0]) <40) {
		            			System.out.println("argument in file error - need to 'width >=80, height >=40'");
		            			System.exit(-3);
	            			}
	            			width = Integer.parseInt(widthHeight[1]);
	            			height = Integer.parseInt(widthHeight[0]);
	            			lineCnt++;
	            		}
	            		continue;
	            	}
            		//두번째 라인 체크
	            	if(lineCnt == 1 && line != null) {
	            		String cellCntStr = line.trim();
	            		if(cellCntStr==null) {
	            			System.out.println("argument in file error - second line only has cell count.");
	            			System.exit(-4);
	            		}else {
	            			if(isInteger(cellCntStr)==false) {
		            			System.out.println("argument in file error - second line,cell count has to be int.");
		            			System.exit(-5);
	            			}
	            			cellCnt = Integer.parseInt(cellCntStr);
	            			lineCnt++;
	            		}
            			continue;
	            	}
            		//세번째 라인 체크
	            	if(lineCnt == 2 && line != null) {
	            		String indexOfCellsStr = line.trim();
	            		if(indexOfCellsStr==null) {
	            			System.out.println("argument in file error - thrid line only has cell's index.");
	            			System.exit(-6);
	            		}else {
	            			if(indexOfCellsStr.length() !=0 ) {
	    	            		cellCntChk++;
		            			indexOfCells.add(indexOfCellsStr);	
	            			}
	            		}
	            	}
	            }
            	if(lineCnt < 2) {
            		System.out.println("input file error occur. file lines under 2.");
        			System.exit(-7);
            	}
            	if(lineCnt == 2 && cellCnt != cellCntChk) {
            		System.out.println("input file error occur. cell count("+cellCnt+") and cell location("+cellCntChk+") are not match");
            		System.out.println();
        			System.exit(-8);
            	}
	            bufReader.close();
	        }catch (FileNotFoundException e) {
	            System.out.println(args[0]+ " file not found.");
                System.exit(-15);
	        }catch(IOException e){
	            System.out.println("error occur when file reading: "+args[0]);
                System.exit(-16);
	        }
        	Board b = new Board(width, height, indexOfCells);
	        int generation = 1;
	        if(args.length == 2) {
	        	if(isInteger(args[1]) == false) {
	    			System.out.println("argument error: second argument has to be int.");
	    			System.exit(-13);
	        	}else {
	        		for(int i = 0 ; i < Integer.parseInt(args[1]); i ++) {
	        			//System.out.println("[GENERATION] :"+generation);
			            //generation++;
			        	//b.displayBoard();
			            b.changeBoardCells();
			            //Thread.sleep(300);
	        		}        
	        		String fileName = "result.txt";
	                File file = new File(fileName);
	                if(file.exists()) {
	                	file.delete();
	                }
	                FileWriter writer = null;
	                try {
	                    // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
	                    writer = new FileWriter(file, true);
	                    writer.write(b.saveBoard());
	                    writer.flush();
	                    System.out.println("success file dump: "+fileName);
	                } catch(IOException e) {
	                    System.out.println("error occur when file save.");
	                    System.exit(-14);
	                } finally {
	                    try {
	                        if(writer != null) writer.close();
	                    } catch(IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	        	}
	        }else {
		        while(true) {
        			System.out.println("[GENERATION] :"+generation);
		            generation++;
		        	b.displayBoard();
		            b.changeBoardCells();
		            Thread.sleep(300);
		        }
	        }
		}else {
			System.out.println("argument error: argument only has one or two.");
			System.exit(-12);
		}

	}
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
}
