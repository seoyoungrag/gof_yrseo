package gof_yrseo;

import java.text.DecimalFormat;

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

    public Cell[][] displayBoard() {
        String border = String.format("  +%0" + grid[0].length*2 + "d+", 0).replace("0","-"); //위아래 border를 그린다.
        DecimalFormat formatter = new DecimalFormat("00");
        StringBuffer topNumbers = new StringBuffer("   ");
        for (int i = 1 ; i <= grid[0].length ; i ++) {
            String aFormatted = formatter.format(i);
            topNumbers.append(aFormatted);
        }
        System.out.println(topNumbers.toString());
        System.out.println(border);
        int cntr=1;
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
    	//TODO 아래 규칙대로 검증한다.
    	//이웃셀들을 가져온다. neighboursCountAt
    	//이웃에 살아있는 이웃이 2개 미만이면 underpopulation, exposure
    	//이웃에 살아있는 이웃이 3개 초과하면 overpopulation, overcrowding
    	//이웃에 살아있는 셀이 3개면 세포가 살아난다.
    	//이웃의 2~3개의 세포가 살아있으면 세포는 유지된다.
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
    	//TODO 이웃에 생존한 셀이 몇개인지 계산해서 리턴한다.
    	int sum = 0;
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
