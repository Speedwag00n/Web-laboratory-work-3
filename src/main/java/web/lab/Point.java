package web.lab;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "POINTS")
@DynamicInsert
public class Point implements Serializable {

    private static final long serialVersionUID = 8180254973636155637L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POINTS_SEQ")
    @SequenceGenerator(name = "POINTS_SEQ", sequenceName = "POINTS_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "x")
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "r")
    private double r;

    @Column(name = "hit")
    private boolean hit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
