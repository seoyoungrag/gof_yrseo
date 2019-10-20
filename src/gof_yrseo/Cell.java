package gof_yrseo;

public class Cell {
    private boolean state = false; //셀이 살아있는 상태
    private boolean newState; //한 주기마다 셀은 이웃셀들과 비교해야하기 때문에 주기로 인해 상태가 바뀌기 전에 자신의 값을 임시로 저장해두어야 한다.

    public Cell() {

    }

    public Cell(boolean state) {
        this.state = state;
    }

    public void setNewState(boolean state) {
        newState = state;
    }

    public void updateState() {
        state = newState;
    }

    public boolean getState() {
        return state;
    }
}
