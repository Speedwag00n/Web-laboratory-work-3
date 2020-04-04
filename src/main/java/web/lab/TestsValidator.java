package web.lab;

import org.junit.Before;
import org.junit.Test;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import static org.junit.Assert.assertEquals;

public class TestsValidator {

    private ParameterYValidator validator;
    private FacesContext facesContext;
    private UIComponent uiComponent;

    @Before
    public void initTest() {
        validator = new ParameterYValidator();
    }

    @Test
    public void test_validator_correct(){
        validator.validate(facesContext, uiComponent, 1);
    }

    @Test(expected = ValidatorException.class)
    public void test_validator_no_in_limits(){
        String requiredMessage = "Y должен входить в диапазон (-3 ... 5)";
        try {
            validator.validate(facesContext, uiComponent, 10);
        } catch (ValidatorException e) {
            assertEquals(requiredMessage, e.getMessage());
            throw e;
        }
    }

    @Test(expected = ValidatorException.class)
    public void test_validator_not_a_number(){
        String requiredMessage = "Y должен быть числом";
        try {
            validator.validate(facesContext, uiComponent, "a");
        } catch (ValidatorException e) {
            assertEquals(requiredMessage, e.getMessage());
            throw e;
        }
    }

}