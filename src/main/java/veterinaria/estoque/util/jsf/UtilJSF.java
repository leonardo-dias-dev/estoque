package veterinaria.estoque.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class UtilJSF {
	
	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}
	
	public static boolean isNotPostback() {
		return !isPostback();
	}
	
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	public static void addFatalMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, message));
	}
	
	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	public static void addWarnMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
	}
	
	public void cleanSubmittedValues(UIComponent uiComponent) {
		if (uiComponent instanceof EditableValueHolder) {
			EditableValueHolder editableValueHolder = (EditableValueHolder) uiComponent;
			editableValueHolder.setSubmittedValue(null);
			editableValueHolder.setValue(null);
			editableValueHolder.setLocalValueSet(false);
			editableValueHolder.setValid(true);
		}
		
		if (uiComponent.getChildCount() > 0) {
			for (UIComponent child : uiComponent.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}
	
}
