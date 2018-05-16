package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model;

import javafx.scene.Node;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker.*;

public class FrozenState extends State{

    public FrozenState(Board board) {
        super(board);
    }

    @Override
    public State clickChecker(Checker checker) {
        State state = new FrozenState(board);
        board.setMovingChecker(checker);
        if(checker.getColor() == board.getTurnColor()){
            //queen
            int x = Board.getColumnIndex(checker);
            int y = Board.getRowIndex(checker);
            int moveDir;
            if(checker.getColor() == CheckerColor.WHITE){
                moveDir = 1;
            }else{
                moveDir = -1;
            }
            for(Node n : board.getChildren()) {
                if (n.getClass() == BoardField.class) {
                    int i = Board.getColumnIndex(n);
                    int j = Board.getRowIndex(n);
                    if(board.getChecker(i,j)==null) {
                        if (Math.abs(x - i) == 1 && y - j == moveDir) {
                            ((BoardField) n).setStatus(BoardFieldStatus.MOVE);
                            state = new ActionState(board);
                        } else if (Math.abs(x - i) == 2 && y - j == moveDir * 2) {
                            int x1 = i + (x - i) / 2;
                            int y1 = j + (y - j) / 2;
                            Checker attackedChecker = board.getChecker(x1, y1);
                            if (attackedChecker != null && checker.getColor() != attackedChecker.getColor()) {
                                ((BoardField) n).setStatus(BoardFieldStatus.ATTACK);
                                ((BoardField) n).setAttackedChecker(attackedChecker);
                                state = new ActionState(board);
                            }
                        }
                    }
                }
            }
        }
        return state;
    }

    @Override
    protected State normalBoardField(BoardField bf) {
        //nothing
        return new FrozenState(board);
    }

    @Override
    protected State moveBoardField(BoardField bf) {
        //nothing
        return new FrozenState(board);
    }

    @Override
    protected State attackBoardField(BoardField bf) {
        //nothing
        return new FrozenState(board);
    }
}
