package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.vecmath.Vector2d;
import java.util.Random;

/**
 * Created by Yauheni on 11/30/2015.
 */
class Ball extends Circle {

    private Vector2d vectorDirection;

    public void setVectorDirection(Vector2d vectorDirection) {
        this.vectorDirection = vectorDirection;
    }

    public Vector2d getVectorDirection(){
        return vectorDirection;
    }

    Ball(double x, double y, double radius, Color color) {
        super(x, y, radius);
        vectorDirection = new Vector2d();
        vectorDirection.setX(2 * Math.random() - 1);
        vectorDirection.setY(Math.sqrt(1 - Math.pow(vectorDirection.getX(),2)));
        setFill(color); // Set ball color
    }

    public double getDirectionX() {
        return vectorDirection.getX();
    }

    public double getDirectionY()
    {
        return vectorDirection.getY();
    }

    public void setDirectionX(double value){
        vectorDirection.setX(value);
    }

    public void setDirectionY(double value){
        vectorDirection.setY(value);
    }


    public boolean isCollide(Ball ball)
    {

        double xd = getCenterX() - ball.getCenterX();
        double yd = getCenterY() - ball.getCenterY();

        double sumRadius = getRadius() + ball.getRadius();
        double sqrRadius = sumRadius * sumRadius;

        double distSqr = (xd * xd) + (yd * yd);

        if (distSqr <= sqrRadius)
        {
            return true;
        }

        return false;
    }

    public void resolveCollision(Ball ball)
    {
        double xd = getCenterX() - ball.getCenterX();
        double yd = getCenterY() - ball.getCenterY();


        Vector2d delta = new Vector2d(xd, yd);

        double distance = delta.length();

        Vector2d normDelta = new Vector2d(delta);
        normDelta.normalize();

        double aci = getVectorDirection().dot(normDelta);
        double bci = ball.getVectorDirection().dot(normDelta);

        double acf = bci;
        double bcf = aci;

        Vector2d v1 = new Vector2d(normDelta);
        v1.setX((acf - aci)*v1.getX());
        v1.setY((acf - aci)*v1.getY());

        Vector2d v2 = new Vector2d(normDelta);
        v2.setX((bcf - bci)*v2.getX());
        v2.setY((bcf - bci)*v2.getY());

        v1.add(getVectorDirection());
        v2.add(ball.getVectorDirection());

        setVectorDirection(v1);
        ball.setVectorDirection(v2);

    }

}
