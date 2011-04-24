package info.joseluismartin.table;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.util.PropertyFilter;
import info.joseluismartin.model.Filter;
import info.joseluismartin.mvc.mapper.ViewModelMapper;

@SuppressWarnings("unchecked")
public interface TableEditor {

	/**
	 * @return the model Classs
	 */
	Class getModelClass();

	/**
	 * @param clazz the clazz to set
	 */
	void setModelClass(Class clazz);

	/** 
	 * view BaseAction method, mapped to /view path
	 * @throws IllegalAccessException on error
	 * @throws InstantiationException on error
	 * @throws IOException on error
	 */
	void view() throws InstantiationException, IllegalAccessException,
			IOException;

	/** 
	 * save BaseAction method, mapped to /save path
	 * @throws IOException on error
	 */
	void save() throws IOException;

	/**
	 * @return the tableEditorService
	 */
	TableEditorService getTableEditorService();

	/**
	 * @param tableEditorService the tableEditorService to set
	 */
	void setTableEditorService(
			TableEditorService tableEditorService);

	/**
	 * @return the validator
	 */
	Validator getValidator();

	/**
	 * @param validator the validator to set
	 */
	void setValidator(Validator validator);

	/**
	 * @return PropertyFilter
	 */
	PropertyFilter getPropertyFilter();

	/**
	 * @param propertyFilter PropertyFilter
	 */
	void setPropertyFilter(PropertyFilter propertyFilter);

	/**
	 * @return the mapper
	 */
	ViewModelMapper getMapper();

	/**
	 * @param mapper the mapper to set
	 */
	void setMapper(ViewModelMapper mapper);

	/**
	 * Prepare
	 */
	void prepare();

	/**
	 * Gets the model
	 * @return the model
	 */
	Object getModel();

	/**
	 * Sets the servlet request
	 * @param request the request to set
	 */
	void setServletRequest(HttpServletRequest request);

	/**
	 * Sets the servlet response
	 * @param response the response to set
	 */
	void setServletResponse(HttpServletResponse response);

	/**
	 * @return the filterClass
	 */
	Class getFilterClass();

	/**
	 * @param filterClass the filterClass to set
	 */
	void setFilterClass(Class filterClass);
	
	/**
	 * Parse filter
	 * @param filter filter to parse
	 * @return filter object
	 * @throws IllegalAccessException on error
	 * @throws InstantiationException on error
	 */
	Filter parseFilter(String filter) throws InstantiationException,
			IllegalAccessException;
}

