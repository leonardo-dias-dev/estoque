package veterinaria.estoque.util.jsf;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;

import org.primefaces.context.ApplicationContext;
import org.primefaces.context.RequestContext;
import org.primefaces.util.AjaxRequestBuilder;
import org.primefaces.util.CSVBuilder;
import org.primefaces.util.StringEncrypter;
import org.primefaces.util.WidgetBuilder;

public class UtilPrimeFaces {
	
	public static void addCallbackParam(String name, Object value) {
		RequestContext.getCurrentInstance().addCallbackParam(name, value);
	}
	
	public static void closeDialog(Object data) {
		RequestContext.getCurrentInstance().closeDialog(data);
	}
	
	public static void execute(String script) {
		RequestContext.getCurrentInstance().execute(script);
	}
	
	public static void abrirDialogPorWidgetVar(String widgetvar) {
		RequestContext.getCurrentInstance().execute(String.format("PF('%s').show()", widgetvar));
	}
	
	public static void fecharDialogPorWidgetVar(String widgetvar) {
		RequestContext.getCurrentInstance().execute(String.format("PF('%s').hide()", widgetvar));
	}
	
	public static void openDialog(String outcome) {
		RequestContext.getCurrentInstance().openDialog(outcome);
	}
	
	public static void  openDialog(String outcome, Map<String,Object> options, Map<String,List<String>> params) {
		RequestContext.getCurrentInstance().openDialog(outcome, options, params);
	}
	
	public static void release() {
		RequestContext.getCurrentInstance().release();
	}
	
	public static void reset(String expressions) {
		RequestContext.getCurrentInstance().reset(expressions);
	}
	
	public static void scrollTo(String clientId) {
		RequestContext.getCurrentInstance().scrollTo(clientId);
	}
	
	public static void showMessageInDialog(FacesMessage message) {
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	public static void update(String name) {
		RequestContext.getCurrentInstance().update(name);
	}
	
	public static void update(Collection<String> collection) {
		RequestContext.getCurrentInstance().update(collection);
	}
	
	public static Map<Object,Object> getAttributes() {
		return RequestContext.getCurrentInstance().getAttributes();
	}
	
	public static Map<String, Object> getCallbackParams() {
		return RequestContext.getCurrentInstance().getCallbackParams();
	}
	
	public static List<String> getScriptsToExecute() {
		return RequestContext.getCurrentInstance().getScriptsToExecute();
	}
	
	public static CSVBuilder getCSVBuilder() {
		return RequestContext.getCurrentInstance().getCSVBuilder();
	}
	
	public static StringEncrypter getEncrypter() {
		return RequestContext.getCurrentInstance().getEncrypter();
	}
	
	public static AjaxRequestBuilder getAjaxRequestBuilder() {
		return RequestContext.getCurrentInstance().getAjaxRequestBuilder();
	}
	
	public static ApplicationContext getApplicationContext() {
		return RequestContext.getCurrentInstance().getApplicationContext();
	}
	
	public static WidgetBuilder getWidgetBuilder() {
		return RequestContext.getCurrentInstance().getWidgetBuilder();
	}
	
	public static boolean isAjaxRequest() {
		return RequestContext.getCurrentInstance().isAjaxRequest();
	}
	
	public static boolean isNotAjaxRequest() {
		return !isAjaxRequest();
	}
	
	public static boolean isIgnoreAutoUpdate() {
		return RequestContext.getCurrentInstance().isIgnoreAutoUpdate();
	}
	
	public static boolean isNotIgnoreAutoUpdate() {
		return !isIgnoreAutoUpdate();
	}
	
	public static boolean isRTL() {
		return RequestContext.getCurrentInstance().isRTL();
	}
	
	public static boolean isNotRTL() {
		return !isRTL();
	}
	
	public static boolean isSecure() {
		return RequestContext.getCurrentInstance().isSecure();
	}
	
	public static boolean isNotSecure() {
		return !isSecure();
	}
	
}
