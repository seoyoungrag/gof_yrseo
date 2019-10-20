package gof_yrseo;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Board {
    private Cell[][] grid;
    private int height=40; 
    private int width=80;
    
    /**
     * 기본값은 가로: 80, 세로: 40, 확률 0.3
     */
    public Board() {
    	this(80, 40, 0.3);
    }

    /**
     * @param width
     * @param height
     * @param 랜덤시작시 보드의 각 셀이 살아있을 확률
     */
    public Board(int width, int height, double p) {
    	//입력받은 가로, 세로 셀로 그리드를 생성한다.
    	//랜덤시작할 경우 셀들을 임의로 초기화하는 값이 필요하다.
        this.height=height;
        this.width=width;
        grid = new Cell[height][width];
        
        for (int h=0; h<grid.length; h++){
            for (int w=0; w<grid[h].length; w++){
                grid[h][w] = new Cell();
                if (Math.random()<=p){
                    grid[h][w].setNewState(true);
                    grid[h][w].updateState();
                }
            }
        }
    }

    /**
     * @param width
     * @param height
     * @param 랜덤시작시 보드의 각 셀이 살아있을 확률
     */
    public Board(int width, int height, ArrayList<String> indexOfCells) {
    	//입력받은 가로, 세로 셀로 그리드를 생성한다.
    	//랜덤시작할 경우 셀들을 임의로 초기화하는 값이 필요하다.
        this.height=height;
        this.width=width;
        grid = new Cell[height][width];
        
        for (int h=0; h<grid.length; h++){
            for (int w=0; w<grid[h].length; w++){
                grid[h][w] = new Cell();
            }
        }
        int x = 0;
        int y = 0;
        for (String indexOfCell : indexOfCells) {
        	try {
	        	x = Integer.parseInt(indexOfCell.split(",")[0]);
	        	y = Integer.parseInt(indexOfCell.split(",")[1]);
        	}catch(IndexOutOfBoundsException e1) {
        		System.out.println("input file error occur: input cell location has to be int.");
        		System.exit(-9);
        	}catch(NumberFormatException e2) {
        		System.out.println("input file error occur: input cell location has invalidate value.");
        		System.exit(-10);
        	}
        	try {
	            grid[x][y].setNewState(true);
	            grid[x][y].updateState();
        	}catch(IndexOutOfBoundsException e) {
        		System.out.println("input file error occur: "+x+","+y+" cell is not exists in grid.");
        		System.exit(-11);
        	}
        }
    }
    public Cell[][] displayBoard() {
        String border = String.format("  +%0" + grid[0].length*2 + "d+", 0).replace("0","-"); //위아래 border를 그린다.
        DecimalFormat formatter = new DecimalFormat("00");
        /*
        StringBuffer topNumbers = new StringBuffer("   ");
        for (int i = 1 ; i <= grid[0].length ; i ++) {
            String aFormatted = formatter.format(i);
            topNumbers.append(aFormatted);
        }
        System.out.println(topNumbers.toString());
        */
        System.out.println(border);
        int cntr=0;
        for (Cell[] row : grid) {
        	cntr++;
            String aFormatted = formatter.format(cntr);
        	System.out.print(aFormatted);
            String r = "|";
            //int cnt = 0;
            for (Cell c : row) {
                r += c.getState() ? "* " : "  ";
                //cnt++;
            }
            r += "|";
            System.out.println(r);
            //System.out.print(r);
            //System.out.println(cnt);
        }
        
        System.out.println(border);
        return grid;
    }
    
    public int getSize() {
        return width;
    }


    /**
     * GameOfLife 규칙에 따라 셀들에 새로운 상태 부여
     */
    private void checkCellState() {
        for (int h=0; h<grid.length; h++){
            for (int w=0; w<grid[h].length; w++){
                int nr = neighboursCountAt(h,w); //이웃셀들을 가져온다. neighboursCountAt
                if (nr < 2) { //이웃에 살아있는 이웃이 2개 미만이면 underpopulation, exposure 
                	grid[h][w].setNewState(false);
            	}  
                else if (nr > 3) { //이웃에 살아있는 이웃이 3개 초과하면 overpopulation, overcrowding
                	grid[h][w].setNewState(false);
            	} 
                else if (nr == 3) {  //이웃에 살아있는 셀이 3개면 세포가 살아난다.
                	grid[h][w].setNewState(true);
            	} 
                else if (nr == 2) { //이웃의 2~3개의 세포가 살아있으면 세포는 유지된다.
                	grid[h][w].setNewState(grid[h][w].getState());
            	}else {
            		System.out.println("발생할 수 없는 케이스: h: "+h+", w: "+w);
            	}
            }
        }
    }

    /**
     * 셀들의 상태를 업데이트 한다.
     */
    private void commitCellState() {
        for (int h=0; h<grid.length; h++){
            for (int w=0; w<grid[h].length; w++){
                grid[h][w].updateState();
            }
        }
    }
    public int neighboursCountAt(int row, int col) {
    	int sum=0;
        //이웃의 개수는 8개, 현재셀을 5로 가정하고, 좌측하단이 1, 우측하단이 3, 좌측상단이 7, 우측상단이 9 순서대로 체크한다.
        //체크할때 상태값을 확인해서 살아있는 세포의 합계값을 리턴한다.
        //1
        if (row != 0 && col != 0){    
            if(isAlive(row-1,col-1)){
                sum++;
            }
        }
        //2
        if (row != 0){
            if(isAlive(row-1,col)){ 
            sum++;
            }
        }
        //3        
        if (row != 0 && col != width-1){
            if(isAlive(row-1,col+1)){
                sum++;
            }
        }
        //4
        if (col != 0){
            if(isAlive(row,col-1)){ 
            sum++;
            }
        }
        //6
        if (col != width-1){
            if(isAlive(row,col+1)){ 
                sum++;
            }
        }
        //7
        if (row != height-1 && col != 0){
            if(isAlive(row+1,col-1)){ 
                sum++;
            }
        }
    	//8
        if (row != height-1){
            if(isAlive(row+1,col)){ 
            sum++;
            }
        }
        //9
        if (row != height-1 && col != width-1){
            if(isAlive(row+1,col+1)){
                sum++;
            }
        }

        return sum;
    }

    public boolean isAlive(int row, int col) {
        return grid[row][col].getState();
    }

    //셀들의 상태를 전체적으로 체크하고 업데이트를 해야하기 때문에 체크와 업데이트를 따로한다.
    public void changeBoardCells() {
    	//셀들을 체크한다.
    	//셀들의 상태를 커밋하낟.
        checkCellState();
        commitCellState();
    }
    
    
}
