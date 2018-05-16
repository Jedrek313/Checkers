package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model;

import javafx.scene.Node;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker.*;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.controller.GameController;

public class ActionState extends State {


    public ActionState(Board board) {
        super(board);
    }

    @Override
    public State clickChecker(Checker c) {
        disable();
        return new FrozenState(board);
    }

    @Override
    protected State normalBoardField(BoardField bf) {
        disable();
        return new FrozenState(board);
    }

    @Override
    protected State moveBoardField(BoardField bf) {
        board.getChildren().remove(board.getMovingChecker());
        int x = Board.getColumnIndex(bf);
        int y = Board.getRowIndex(bf);
        board.add(board.getMovingChecker(),x,y);
        disable();
        turnRound();
        return new FrozenState(board);

    }

    @Override
    protected State attackBoardField(BoardField bf) {
        board.getChildren().remove(bf.getAttackedChecker());
        board.getChildren().remove(board.getMovingChecker());
        bf.setAttackedChecker(null);
        int x = Board.getColumnIndex(bf);
        int y = Board.getRowIndex(bf);
        board.add(board.getMovingChecker(),x,y);
        disable();
        turnRound();
        return new FrozenState(board);


    }

    public void disable(){
        board.setMovingChecker(null);
        for(Node node : board.getChildren()){
            if(node.getClass()==BoardField.class && ((BoardField)node).getStatus()!= BoardFieldStatus.NORMAL){
                ((BoardField)node).setStatus(BoardFieldStatus.NORMAL);
                ((BoardField)node).setAttackedChecker(null);
            }
        }
    }

    public void turnRound(){
        if(board.getTurnColor() == CheckerColor.WHITE){
            board.setTurnColor(CheckerColor.BLACK);
        }else{
            board.setTurnColor(CheckerColor.WHITE);
        }
    }
}
