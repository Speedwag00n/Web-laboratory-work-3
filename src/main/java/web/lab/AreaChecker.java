package web.lab;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * AreaChecker contains state of point form. Also it contains methods to save points and methods to check point hits area or not.
 *
 * @author Nemankov Ilia
 * @version 1.0.0
 * @since 1.0.0
 */
@ManagedBean
@SessionScoped
public class AreaChecker implements Serializable {

    private static final long serialVersionUID = -3982895170546874882L;

    private Double x;
    private Double y;
    private Double r = 1d;

    private Double canvasX;
    private Double canvasY;

    private LinkedList<Point> points = new LinkedList<Point>();

    private EntityManagerFactory entityManagerFactory;

    public AreaChecker() {
        entityManagerFactory = HibernateUtils.getInstance();
    }

    public AreaChecker(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Checks new point hit which data was specified via form and save.
     */
    public void checkForm() {
        check(x, y);
    }

    /**
     * Checks new point hit which data was specified via click on canvas and save.
     */
    public void checkCanvas() {
        check(canvasX, canvasY);
    }

    private void check(Double x, Double y) {
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setHit(isHit(x, y));
        if (entityManagerFactory != null) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            try {
                entityManager.persist(point);
                entityManager.getTransaction().commit();
                points.addFirst(point);
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
            entityManager.close();
        }
    }

    /**
     * Checks is point hits area or not.
     *
     * @param point which hit is checking.
     * @return true if point hits area else returns false.
     */
    public boolean isHit(Point point) {
        return isHit(point.getX(), point.getY());
    }

    private boolean isHit(Double x, Double y) {
        double ax = r / 7.0;
        double ay = r / 6.0;

        boolean ellipse = (Math.pow(x, 2)) / (49 * Math.pow(ax, 2)) + (Math.pow(y, 2)) / (9 * Math.pow(ay, 2)) - 1.0 <= 0.0001;
        boolean ellipseBottomX = (Math.abs(x / ax)) >= 4;
        boolean ellipseBottomY = (y / ay >= -3 * Math.sqrt(33) / 7.0) && (y / ay <= 0);
        boolean ellipseTopX = (Math.abs(x / ax)) >= 3;
        boolean ellipseTopY = y >= 0;
        if (ellipse && ellipseBottomX && ellipseBottomY) {
            return true;
        }
        if (ellipse && ellipseTopX && ellipseTopY) {
            return true;
        }

        boolean tail = (((3 * Math.sqrt(33) - 7) * Math.pow(x, 2)) / (-112.0 * Math.pow(ax, 2))
                + Math.abs(x / ax) / 2
                + Math.sqrt(1 - Math.pow(Math.abs((Math.abs(x / ax)) - 2) - 1, 2)) - y / ay - 3) <= 0;
        boolean tailX = (x / ax <= 4) && (x / ax >= -4);
        boolean tailY = (y / ay >= -3) && (y / ay <= 0);
        if (tail && tailX && tailY) {
            return true;
        }

        boolean ear1 = -8 * Math.abs(x / ax) - y / ay + 9 >= 0;
        boolean earsY = y >= 0;
        boolean ear1X = Math.abs(x / ax) <= 1 && Math.abs(x / ax) >= 0.75;
        if (ear1 && earsY && ear1X) {
            return true;
        }

        boolean ear2 = 3 * Math.abs(x / ax) - y / ay + 0.75 >= 0;
        boolean ears2X = Math.abs(x / ax) <= 0.75 && Math.abs(x / ax) >= 0.5;
        if (ear2 && earsY && ears2X) {
            return true;
        }

        boolean head = 9.0 / 4.0 - y / ay >= 0;
        boolean headX = Math.abs(x / ax) <= 0.5;
        boolean headY = y >= 0;
        if (head && headX && headY) {
            return true;
        }

        boolean wings = -Math.abs(x / ax) / 2 - (3.0 / 7.0) * Math.sqrt(10) * Math.sqrt(4 - Math.pow(Math.abs(x / ax) - 1, 2)) - y / ay + (6 * Math.sqrt(10)) / 7.0 + 1.5 >= 0;
        boolean wingsX = Math.abs(x / ax) <= 3 && Math.abs(x / ax) >= 1;
        boolean wingsY = y >= 0;
        if (wings && wingsX && wingsY) {
            return true;
        }

        return false;
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

    /**
     * Gets R value without fraction if it's digit.
     */
    public String getProcessedR() {
        if (r % 1 != 0) {
            return r + "";
        } else {
            return ((int)r.doubleValue()) + "";
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Point> points) {
        this.points = points;
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
