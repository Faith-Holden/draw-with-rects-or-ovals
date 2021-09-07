package solution;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MousePaint extends Application{

    Canvas canvas;
    private double prevX, prevY;
    private boolean dragging;


    public static void main (String[] Args){
        launch(Args);
    }

    public void start(Stage stage) throws Exception {
        canvas = new Canvas(400,400);
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        draw();

        stage.show();

        canvas.setOnMousePressed(mouseEvent -> {
            if(mouseEvent.isPrimaryButtonDown()){
                startDrawing(mouseEvent);
            }else{
                draw();
            }
        });
        canvas.setOnMouseReleased(this::stopDrawing);
        canvas.setOnMouseDragged(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown()) {
                keepDrawing(mouseEvent);
            }
        });
    }

    public void draw() {

        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.BISQUE);
        g.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

    }

    public void startDrawing(MouseEvent evt){
        GraphicsContext g = canvas.getGraphicsContext2D();
        if(dragging){
            return;
        }else{
            dragging = true;
        }

        if((prevX-evt.getX()>5||prevX-evt.getX()<-5)&& (prevY-evt.getY()>5||prevY - evt.getY()<-5)){
            if ( evt.isShiftDown() ) {
                g.setFill( Color.MAROON );
                g.setStroke(Color.BLACK);
                g.fillOval( evt.getX() - 30, evt.getY() - 15, 60, 30 );
                g.strokeOval(evt.getX() - 30, evt.getY() - 15, 60, 30 );
            }
            else {
                g.setFill( Color.ROSYBROWN );
                g.setStroke(Color.BLACK);
                g.fillRect( evt.getX() - 30, evt.getY() - 15, 60, 30 );
                g.strokeRect( evt.getX() - 30, evt.getY() - 15, 60, 30 );
            }

            prevX = evt.getX();
            prevY = evt.getY();
        }
    }
    public void stopDrawing(MouseEvent evt){
        dragging = false;
    }

    public void keepDrawing(MouseEvent evt) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        if (!dragging)
            return;
        else if((prevX-evt.getX()>5||prevX-evt.getX()<-5)&& (prevY-evt.getY()>5||prevY - evt.getY()<-5)){
            if ( evt.isShiftDown() ) {
                g.setFill( Color.MAROON );
                g.setStroke(Color.BLACK);
                g.fillOval( evt.getX() - 30, evt.getY() - 15, 60, 30 );
                g.strokeOval(evt.getX() - 30, evt.getY() - 15, 60, 30 );
            }
            else {
                g.setFill( Color.ROSYBROWN );
                g.setStroke(Color.BLACK);
                g.fillRect( evt.getX() - 30, evt.getY() - 15, 60, 30 );
                g.strokeRect( evt.getX() - 30, evt.getY() - 15, 60, 30 );
            }
            prevX = evt.getX();
            prevY = evt.getY();
        }
    }
}
