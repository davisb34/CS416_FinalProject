package edu.ccsu.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator(value="imageValidator")
public class ImageValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part image = (Part)value;
        if (!(image.getContentType().contains("image"))){
            FacesMessage facesMessage =  new FacesMessage("Please only upload image files.");
            throw new ValidatorException(facesMessage);
        }
    }
}