package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

/**
 * Created by Jedrzej Pienkowski on 20.03.2018.
 */
public class Board extends GridPane {

    private BoardField[][] fields;
    private Checker[][] checkers;
    private CheckerColor turnColor;
    private Checker movingChecker;

    public Board(){
        fields = new BoardField[8][8];
        checkers = new Checker[8][8];
        turnColor = CheckerColor.WHITE;
        for(int i =0; i<8; i++){
            this.getRowConstraints().add(new RowConstraints());
            this.getRowConstraints().get(i).setPercentHeight(100/8);
            this.getColumnConstraints().add(new ColumnConstraints());
            this.getColumnConstraints().get(i).setPercentWidth(100/8);
        }
        for(int i =0; i<8; i++){
            for(int j =0; j<8; j++){
                checkers[i][j] = null;
                BoardField bf = new BoardField(440/8);
                if((i+j)%2==0){
                    bf.setFill(Color.BEIGE);
                    bf.setEnabled(false);
                    fields[i][j] = bf;
                    add(bf, i, j);
                }else{
                    bf.setFill(Color.BROWN);
                    fields[i][j] = bf;
                    bf.setEnabled(true);
                    add(bf, i, j);
                    if(j<3){
                        Checker c = new Checker(CheckerColor.BLACK);
                        add(c,i,j);
                    }else if(j>4){
                        Checker c = new Checker(CheckerColor.WHITE);
                        add(c,i,j);
                    }
                }
            }
        }
    }

    public Checker getChecker(int i, int j){
        Checker checker = null;
        for (Node node : getChildren()) {
            if(getRowIndex(node) == j && getColumnIndex(node) == i && node.getClass()==Checker.class) {
                checker =(Checker) node;
                break;
            }
        }
        return checker;
    }

    public BoardField getBoardField(int i, int j){
        BoardField boardField = null;
        for (Node node : getChildren()) {
            if(getRowIndex(node) == j && getColumnIndex(node) == i && node.getClass()==BoardField.class) {
                boardField =(BoardField) node;
                break;
            }
        }
        return boardField;
    }

    public CheckerColor getTurnColor() {
        return turnColor;
    }

    public void setTurnColor(CheckerColor turnColor) {
        this.turnColor = turnColor;
    }

    public Checker getMovingChecker() {
        return movingChecker;
    }

    public void setMovingChecker(Checker movingChecker) {
        this.movingChecker = movingChecker;
    }
}
