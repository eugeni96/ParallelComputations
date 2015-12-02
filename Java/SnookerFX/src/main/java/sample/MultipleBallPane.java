package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;

/**
 * Created by Yauheni on 11/30/2015.
 */
public class MultipleBallPane extends Pane {

    //private Timeline animation;

    private List<Ball> balls = new ArrayList<Ball>();

    public MultipleBallPane() {
        this.setPrefSize(800, 600);
        moveThread.start();
    }

    public void add() {
        Color color = new Color(Math.random(),
                Math.random(), Math.random(), 0.5);
        Ball newBall = new Ball(50, 50, 30, color);
        Circle circle = new Circle(50, 50, 30, color);
        balls.add(newBall);
        getChildren().add(circle);
    }

    public void subtract() {
        if (balls.size() > 0) {
            getChildren().remove(getChildren().size()-1);
            balls.remove(balls.size()-1);
        }
    }

    private Thread moveThread = new Thread(new Runnable() {

        List<Vector2d> nextStepCoords = new ArrayList<>();

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {

                nextStepCoords = moveBall();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < nextStepCoords.size(); i++)
                        {
                            Circle circle = (Circle) getChildren().get(i);
                            circle.setCenterX(nextStepCoords.get(i).getX());
                            circle.setCenterY(nextStepCoords.get(i).getY());
                        }
                    }
                });
                try{
                    Thread.sleep(20);
                } catch(InterruptedException e){
                    break;
                }
            }
        }
    });

    protected List<Vector2d> moveBall() {

        List<Vector2d> result = new ArrayList<>();

        for (int i = 0; i < balls.size(); i++) {
            Node node = balls.get(i);
            Ball ball = (Ball)node;
            // Check boundaries
            if (ball.getCenterX() < ball.getRadius() ||
                    ball.getCenterX() > getWidth() - ball.getRadius()) {
                ball.setDirectionX(ball.getDirectionX()*(-1)); // Change ball move direction
            }
            if (ball.getCenterY() < ball.getRadius() ||
                    ball.getCenterY() > getHeight() - ball.getRadius()) {
                ball.setDirectionY(ball.getDirectionY()*(-1)); // Change ball move direction
            }

            for (int j = i+1; j < balls.size(); j++) {
                Node anotherNode = balls.get(j);
                Ball anotherBall = (Ball) anotherNode;
                if (ball.isCollide(anotherBall))
                {
                    ball.resolveCollision(anotherBall);
                }

            }

            // Adjust ball position
            ball.setCenterX(ball.getDirectionX() + ball.getCenterX());
            ball.setCenterY(ball.getDirectionY() + ball.getCenterY());

            result.add(new Vector2d(ball.getCenterX(),ball.getCenterY()));
        }
        return result;
    }



}
