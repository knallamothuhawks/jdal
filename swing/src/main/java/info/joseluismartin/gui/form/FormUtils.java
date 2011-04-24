/**
 * 
 */
package info.joseluismartin.gui.form;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freixas.jcalendar.JCalendar;
import org.freixas.jcalendar.JCalendarCombo;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Static utility library for use in Swing Forms
 * 
 * @author Jose Luis Martin - (jlm@joseluismartin.info)
 */
@SuppressWarnings("unchecked")
public abstract class FormUtils {
	
	private static final Log log = LogFactory.getLog(FormUtils.class);
	
	public static void link(final JComboBox primary, final JComboBox dependent, final String propertyName) {
		link(primary, dependent, propertyName, false);
	}
	
	/**
	 * Add a link on primary and dependent JComboBoxes by property name. 
	 * When selection changes on primary use propertyName to get a Collection and fill dependent JComboBox with it
	 * @param primary JComboBox when selection changes
	 * @param dependent JComboBox that are filled with collection	
	 * @param propertyName the property name for get the collection from primary selected item
	 * @param addNull if true, add a null as first combobox item
	 */
	public static void link(final JComboBox primary, final JComboBox dependent, final String propertyName, 
			final boolean addNull) {
		
		primary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selected = primary.getSelectedItem();
				if (selected != null) {
					BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(selected);
					if (wrapper.isWritableProperty(propertyName)) {
						Collection collection = (Collection) wrapper.getPropertyValue(propertyName);
						Vector<Object> vector = new Vector<Object>(collection);
						if (addNull) vector.add(0, null);
						DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
						dependent.setModel(model);
				
					}
					else {
						log.error("Can't write on propety '" + propertyName + "' of class: '" + selected.getClass() + "'");
					}
				}
			}
		});
	}
	
	/**
	 * Return a List of Objects from a ComboBoxModel
	 * @param model ComboBoxModel
	 * @return a list of Objects with ComboBoxModel items
	 */
	public static List<Object> getComboModelList(ComboBoxModel model) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < model.getSize(); i++) {
			list.add(model.getElementAt(i));
		}
		return list;
	}
	
	public static JComboBox newCombo(int chars) {
		StringBuilder sb = new StringBuilder(chars);
		while (chars-- > 0) sb.append("X");
		JComboBox combo = new JComboBox();
		combo.setPrototypeDisplayValue(sb.toString());
		return combo;
	}
	
	/**
	 * Make font of JLabel bold
	 * @param label JLabel to make bold
	 */
	public static void setBold(JLabel label) {
		label.setFont(label.getFont().deriveFont(Font.BOLD));
	}
	
	/**
	 * Create Titled Border
	 * @param name the title
	 * @return Border
	 */
	public static Border createTitledBorder(String name) {
		Border margin = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border title = BorderFactory.createTitledBorder(name);
		
		return BorderFactory.createCompoundBorder(title, margin);
	}
	
	/**
	 * Create a new JCalendarCombo with DateFormat.SHORT
	 * @return JCalendarCombo
	 */
	public static JCalendarCombo newJCalendarCombo() {
		JCalendarCombo combo = new JCalendarCombo(JCalendar.DISPLAY_DATE | JCalendar.DISPLAY_TIME, false);
		combo.setDateFormat(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT));
		combo.setNullAllowed(true);
		combo.setDate(null);
		
		return combo;
	}
	
	/**
	 * Get Default OK Button from LookAndFeel (like JOptionPane)
	 */
	public static JButton newOKButton() {
		String text = UIManager.getString("OptionPane.okButtonText");
		Icon icon = UIManager.getIcon("OptionPane.okIcon");
		int mnemonic = getMnemonic("OptionPane.okButtonMnemonic");
		JButton b = new JButton(text, icon);
		b.setMnemonic(mnemonic);
		b.setAlignmentX(Container.CENTER_ALIGNMENT);
		b.setAlignmentY(Container.CENTER_ALIGNMENT);
		return b;
	}
	

	/**
	 * Get Default OK Button from LookAndFeel (like JOptionPane)
	 */
	public static JButton newCancelButton() {
		String text = UIManager.getString("OptionPane.cancelButtonText");
		Icon icon = UIManager.getIcon("OptionPane.cancelIcon");
		int mnemonic = getMnemonic("OptionPane.cancelButtonMnemonic");
		JButton b = new JButton(text, icon);
		b.setMnemonic(mnemonic);
		b.setAlignmentX(Container.CENTER_ALIGNMENT);
		b.setAlignmentY(Container.CENTER_ALIGNMENT);
		
		return b;
	}

	private static int getMnemonic(String key) {
		String value = (String) UIManager.get(key);
		
		if (value == null) {
			return 0;
		}
		try {
			return Integer.parseInt(value);
		}
		catch (NumberFormatException nfe) { }

		return 0;
	}
	
	/**
	 * Load Icon from url
	 * @param url
	 * @return Icon, null on faliure
	 */
	public static Icon getIcon(String url) {
		Resource resource = new ClassPathResource(url);
		Icon icon = null;
		try {
			Image image = Toolkit.getDefaultToolkit().getImage(resource.getURL());
			icon = new ImageIcon(image);
		} catch (IOException e) {
			log.error(e);
		}
		return icon;
	}

	/**
	 * Load icon if icon = null, else return icon.
	 * @param icon icon to load
	 * @param url String with url
	 * @return icon
	 */
	public static Icon getIcon(Icon icon, String url) {
		return icon != null ? icon : getIcon(url);
	}
}
