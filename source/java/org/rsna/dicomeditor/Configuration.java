/*---------------------------------------------------------------
*  Copyright 2015 by the Radiological Society of North America
*
*  This source software is released under the terms of the
*  RSNA Public License (http://mirc.rsna.org/rsnapubliclicense)
*----------------------------------------------------------------*/

package org.rsna.dicomeditor;

import java.awt.Color;
import java.io.File;
import org.apache.log4j.Logger;
import org.rsna.ctp.stdstages.anonymizer.IntegerTable;
import org.rsna.ui.ApplicationProperties;
import org.rsna.util.FileUtil;
import org.rsna.util.StringUtil;

/**
 * The singleton class that encapsulates the configuration of the program.
 */
public class Configuration {

	static final Logger logger = Logger.getLogger(Configuration.class);

    public static final String propfile 		= "dicomeditor.properties";
    public static final String idtablepropfile 	= "idtable.properties";
    public static final String dicomScriptFile	= "dicom-anonymizer.script";
    public static final String lookupTableFile	= "lookup-table.properties";
    public static final String xmlScriptFile	= "xml-anonymizer.script";
    public static final String helpfile 		= "help.html";
    public static final String mappingFile 		= "mapping.csv";

	public static final Color background = Color.getHSBColor(0.58f, 0.17f, 0.95f);

	IntegerTable integerTable = null;
	
	static Configuration configuration = null;
    private ApplicationProperties props;

	/**
	 * Get the singleton instance of the Configuration.
	 * @return the Configuration.
	 */
	public static synchronized Configuration getInstance() {
		if (configuration == null) configuration = new Configuration();
		return configuration;
	}

	//The protected constructor.
	protected Configuration() {
		props = new ApplicationProperties(new File(propfile));
		File home = new File(System.getProperty("user.dir"));
		File databaseDir = new File(home, "data");
		databaseDir.mkdirs();
		try {
			integerTable = new IntegerTable(databaseDir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ApplicationProperties getProps() {
		return props;
	}
	
	public IntegerTable getIntegerTable() {
		return integerTable;
	}

	public void put(String key, String value) {
		props.setProperty(key, value);
	}

	public String get(String key) {
		return props.getProperty(key);
	}

	public void store() {
		props.store();
	}

}
