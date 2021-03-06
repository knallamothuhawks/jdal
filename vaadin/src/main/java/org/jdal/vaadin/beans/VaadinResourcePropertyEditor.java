/*
 * Copyright 2009-2011 the original author or authors.
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
package org.jdal.vaadin.beans;

import java.beans.PropertyEditorSupport;

import org.jdal.vaadin.ui.FormUtils;

import com.vaadin.server.Resource;

public class VaadinResourcePropertyEditor extends PropertyEditorSupport {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Resource resource = FormUtils.getResource(text);
		setValue(resource);
	}
		
}
