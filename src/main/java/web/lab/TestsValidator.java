package web.lab;

import junit.framework.TestCase;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class TestsValidator extends TestCase {

    private ParameterYValidator validator;
    private FacesContext facesContext;
    private UIComponent uiComponent;

    @Override
    public void setUp() {
        validator = new ParameterYValidator();
    }

    public void test_validator_correct(){
        validator.validate(facesContext, uiComponent, 1);
    }

    public void test_validator_no_in_limits(){
        String requiredMessage = "Y должен входить в диапазон (-3 ... 5)";
        try {
            validator.validate(facesContext, uiComponent, 10);
        } catch (ValidatorException e) {
            assertEquals(requiredMessage, e.getMessage());
        }
    }

    public void test_validator_not_a_number(){
        String requiredMessage = "Y должен быть числом";
        try {
            validator.validate(facesContext, uiComponent, "a");
        } catch (ValidatorException e) {
            assertEquals(requiredMessage, e.getMessage());
        }
    }

}
