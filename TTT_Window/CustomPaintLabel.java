package de.lmu.bio.ifi.TTT_Window;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CustomPaintLabel extends Label {
    Shape s;
    public int x;
    public int y;
    Rectangle r = new Rectangle(1, 1, getWidth()-2, getHeight()-2);

    public CustomPaintLabel(int x, int y){
        this.x=x;
        this.y=y;

        r.setFill(null); r.setStroke(Color.DARKRED); r.setStrokeWidth(3);
        this.getChildren().add(r);
    }

    public void clear(){
        this.getChildren().clear();
        this.getChildren().add(r);
        s = null;
    }

    public boolean paint(boolean p1){
        if(s == null){
            if (p1){
                s = new Line(getWidth()*0.25, getHeight()*0.25, this.getWidth()*0.75, this.getHeight()*0.75);
                s.setStroke(Color.BLUE);
                s.setStrokeWidth(5);
                s.setFill(Color.BLUE);
                Line s2 = new Line(getWidth()*0.75, getWidth()*0.25, getHeight()*0.25, getHeight()*0.75);
                s2.setStroke(Color.BLUE);
                s2.setStrokeWidth(5);
                s2.setFill(Color.BLUE);
                this.getChildren().add(s2);
            }
            else{
                s = new Circle(this.getWidth()/2,this.getHeight()/2,this.getHeight()*0.25);
                s.setStroke(Color.RED);
                s.setStrokeWidth(5);
                s.setFill(null);
            }
            this.getChildren().add(s);
            return true;
        }
        else return false;
    }
}
