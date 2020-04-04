package web.lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestsChecker {

    private AreaChecker checker;

    @Before
    public void initTest() {
        checker = new AreaChecker(null);
    }

    @Test
    public void test_checker_center_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0d);
        point.setY(0d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_left_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(-1d);
        point.setY(0d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_left_not_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(-1.0001d);
        point.setY(0d);
        assertFalse(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_right_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(1d);
        point.setY(0d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_right_not_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(1.0001d);
        point.setY(0d);
        assertFalse(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_left_top_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(-0.4286d);
        point.setY(0.45174d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_left_top_not_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(-0.4285d);
        point.setY(0.45174d);
        assertFalse(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_right_top_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0.4286d);
        point.setY(0.45174d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_right_top_not_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0.4285d);
        point.setY(0.45174d);
        assertFalse(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_left_bottom_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(-0.5715d);
        point.setY(-0.4103d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_left_bottom_not_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(-0.5714d);
        point.setY(-0.4103d);
        assertFalse(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_right_bottom_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0.5715d);
        point.setY(-0.4103d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ellipse_right_bottom_not_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0.5714d);
        point.setY(-0.4103d);
        assertFalse(checker.isHit(point));
    }

    @Test
    public void test_checker_tail_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0);
        point.setY(-0.5);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_tail_not_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0);
        point.setY(-0.5001);
        assertFalse(checker.isHit(point));
    }

    @Test
    public void test_checker_ear_left_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(-0.1072d);
        point.setY(0.4994d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ear_left_not_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(-0.1072d);
        point.setY(0.4995d);
        assertFalse(checker.isHit(point));
    }

    @Test
    public void test_checker_ear_right_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0.1072d);
        point.setY(0.4994d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ear_right_not_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0.1072d);
        point.setY(0.4995d);
        assertFalse(checker.isHit(point));
    }

    @Test
    public void test_checker_head_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0d);
        point.setY(0.375d);
        assertTrue(checker.isHit(point));
    }

    @Test
    public void test_checker_ear_head_hit(){
        checker.setR(1d);
        Point point = new Point();
        point.setX(0d);
        point.setY(0.4995d);
        assertFalse(checker.isHit(point));
    }

}