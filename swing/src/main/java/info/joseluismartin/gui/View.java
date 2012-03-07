/*
 * Copyright 2008-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.joseluismartin.gui;


import javax.swing.JComponent;

/**
 * Interface for Views
 * 
 * @author Jose Luis Martin - (jlm@joseluismartin.info)
 */
public interface View<T> extends Binder<T>{

	/** 
	 * Gets the view name 
	 */
	String getName();
	
	/**
	 * Gets the view JComponent 
	 */
	JComponent getPanel();
	
	/**
	 *  validate
	 * @return true if there are not binding errors.
	 */
	boolean validateView();
	
	/** 
	 * Reset view state to default values
	 */
	void clear();
	
	/**
	 * Check if user change any controls of view
	 * @return true if any control has changed
	 */
	boolean isDirty();
	
	/**
	 * Enable/Disable All controls
	 */
	void enableView(boolean enabled);

}
