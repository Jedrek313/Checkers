package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Jedrzej Pienkowski on 20.03.2018.
 */
public class BoardField extends Rectangle {

    BoardFieldStatus status;
    Checker attackedChecker;
    boolean enabled;


    public BoardField(double height) {
        setHeight(height);
        setWidth(height);
        status = BoardFieldStatus.NORMAL;
        enabled = false;
    }

    public void changeColor(){
        if(enabled){
            switch(status){
                case NORMAL:
                    setFill(Color.BROWN);
                    break;

                case MOVE:
                    setFill(Color.BLUE);
                    break;

                case ATTACK:
                    setFill(Color.RED);
                    break;

            }
        }else{
            setFill(Color.BEIGE);
        }
    }

    public BoardFieldStatus getStatus() {
        return status;
    }

    public void setStatus(BoardFieldStatus status) {
        this.status = status;
        changeColor();
    }

    public Checker getAttackedChecker() {
        return attackedChecker;
    }

    public void setAttackedChecker(Checker attackedChecker) {
        this.attackedChecker = attackedChecker;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
