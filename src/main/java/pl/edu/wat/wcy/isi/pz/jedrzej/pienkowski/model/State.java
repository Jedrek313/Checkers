package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model;

import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker.Board;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker.BoardField;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker.Checker;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker.CheckerColor;

public abstract class State {

    protected Board board;

    public State(Board board){
        this.board=board;
    }

    public State clickBoardField(BoardField bf) {
        State state = new FrozenState(board);
        switch(bf.getStatus()){
            case NORMAL:
                state = normalBoardField(bf);
                break;
            case MOVE:
                state = moveBoardField(bf);
                break;
            case ATTACK:
                state = attackBoardField(bf);
                break;
        }
        return state;
    }

    public abstract State clickChecker(Checker c);


    protected abstract State normalBoardField(BoardField bf);
    protected abstract State moveBoardField(BoardField bf);
    protected abstract State attackBoardField(BoardField bf);
}