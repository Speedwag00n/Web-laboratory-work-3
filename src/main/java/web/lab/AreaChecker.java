package web.lab;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
@SessionScoped
public class AreaChecker {

    private Double x;
    private Double y;
    private Double r = 1d;

    private Double canvasX;
    private Double canvasY;

    private LinkedList<Point> points = new LinkedList<Point>();
    private LinkedList<Point> reversedPoints = new LinkedList<Point>();

    public void checkForm() {
        check(x, y);
    }

    public void checkCanvas() {
        check(canvasX, canvasY);
    }

    public void check(Double x, Double y) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setHit(isHit(x, y));
        try {
            session.save(point);
            transaction.commit();
            points.add(point);
            reversedPoints.addFirst(point);
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }

    public boolean isHit(Point point) {
        return isHit(point.getX(), point.getY());
    }

    private boolean isHit(Double x, Double y) {
        return ((x >= 0) && (y >= 0) && (r >= x) && (r / 2 >= y))
                || ((x >= 0) && (y <= 0) && (y >= 2 * x - r))
                || ((x <= 0) && (y <= 0) && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2)));
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Point> points) {
        this.points = points;
    }

    public List<Point> getReversedPoints() {
        return reversedPoints;
    }

    public void setReversedPoints(LinkedList<Point> reversedPoints) {
        this.reversedPoints = reversedPoints;
    }

    public Double getCanvasX() {
        return canvasX;
    }

    public void setCanvasX(Double canvasX) {
        this.canvasX = canvasX;
    }

    public Double getCanvasY() {
        return canvasY;
    }

    public void setCanvasY(Double canvasY) {
        this.canvasY = canvasY;
    }

}
