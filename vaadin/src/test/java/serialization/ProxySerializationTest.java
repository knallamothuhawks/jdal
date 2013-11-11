/*
 * Copyright 2009-2013 the original author or authors.
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
package serialization;

import org.jdal.vaadin.beans.CachedBeanTargetSource;
import org.jdal.vaadin.ui.GuiFactory;
import org.jdal.vaadin.ui.table.PageableTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;

/**
 * 
 * @author Jose Luis Martin
 */
@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="serialization.xml")
public class ProxySerializationTest {

	
	@Autowired 
	private PageableTable table;
	@Autowired
	private BeanFactory beanFactory;
	
	@SuppressWarnings("unused")
	@Test
	public void testProxySerialization() {
		byte[] ser = SerializationUtils.serialize(table);
		PageableTable deserialized = (PageableTable) SerializationUtils.deserialize(ser);
		GuiFactory factory = deserialized.getGuiFactory();
		Assert.notNull(factory);	
		
		CachedBeanTargetSource targetSource = new CachedBeanTargetSource();
		targetSource.setTargetBeanName("scopedProxy.authorTable");
		targetSource.setBeanFactory(beanFactory);
		byte[] bytes = SerializationUtils.serialize(targetSource);
		
		byte[] bfbytes = SerializationUtils.serialize(beanFactory);
		
	}
}
