package web.lab;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class SliderView implements Serializable {

    private float value = 1;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}