package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Checker extends StackPane {

    CheckerColor color;

    public Checker(CheckerColor color){
        this.color = color;
        Ellipse ellipse = new Ellipse(500/8*0.25,500/8*0.25);
        if(color==CheckerColor.WHITE){
            ellipse.setFill(Color.WHITE);
        }else{
            ellipse.setFill(Color.BLACK);
        }
        getChildren().add(ellipse);
    }

    public CheckerColor getColor() {
        return color;
    }
}
