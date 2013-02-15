/** Copyright (c) 2012 Memorial Sloan-Kettering Cancer Center.
**
** This library is free software; you can redistribute it and/or modify it
** under the terms of the GNU Lesser General Public License as published
** by the Free Software Foundation; either version 2.1 of the License, or
** any later version.
**
** This library is distributed in the hope that it will be useful, but
** WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
** MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
** documentation provided hereunder is on an "as is" basis, and
** Memorial Sloan-Kettering Cancer Center 
** has no obligations to provide maintenance, support,
** updates, enhancements or modifications.  In no event shall
** Memorial Sloan-Kettering Cancer Center
** be liable to any party for direct, indirect, special,
** incidental or consequential damages, including lost profits, arising
** out of the use of this software and its documentation, even if
** Memorial Sloan-Kettering Cancer Center 
** has been advised of the possibility of such damage.  See
** the GNU Lesser General Public License for more details.
**
** You should have received a copy of the GNU Lesser General Public License
** along with this library; if not, write to the Free Software Foundation,
** Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
**/

// package
package org.mskcc.cbio.importer.model;

// imports
import org.mskcc.cbio.cgds.model.ClinicalAttributeAbstract;

import java.util.Map;
import java.util.HashMap;

/**
 * Class which contains clinical attributes metadata.
 */
public class ClinicalAttributesMetadata extends ClinicalAttributeAbstract {

	// worksheet column header that is used as key to find row to update
	public static final String WORKSHEET_UPDATE_COLUMN_KEY = "COLUMNHEADER";
    public static final String WORKSHEET_ALIAS_KEY = "ALIASES";

	// bean properties
    private String columnHeader;
	private String datatype;
	private String aliases;
	private String annotationStatus;
	private String diseaseSpecificity;
	Map<String, String> propertiesMap;

    /**
     * Create a ClinicalAttributesMetadata instance with properties in given array.
	 * Its assumed order of properties is that from google worksheet.
     *
	 * @param properties String[]
     */
    public ClinicalAttributesMetadata(String[] properties) {
        super(properties[1].trim(), properties[2].trim());

		if (properties.length < 7) {
            throw new IllegalArgumentException("corrupt properties array passed to contructor");
		}

        this.columnHeader = properties[0].trim();
		this.datatype = properties[3].trim();
		this.aliases = properties[4].trim();
		this.annotationStatus = properties[5].trim();
		this.diseaseSpecificity = properties[6].trim();

        // properties map - used by code that updates row in the worksheet
        // (google spreadsheet api, removes certain symbols (including spaces) from column headings)
        propertiesMap = new HashMap<String, String>();
        propertiesMap.put("COLUMNHEADER", this.columnHeader);
        propertiesMap.put("DISPLAYNAME", super.getDisplayName());
        propertiesMap.put("DESCRIPTION", super.getDescription());
        propertiesMap.put("DATATYPE", this.datatype);
        propertiesMap.put("ALIASES", this.aliases);
        propertiesMap.put("ANNOTATIONSTATUS", this.annotationStatus);
        propertiesMap.put("DISEASESPECIFICITY", this.diseaseSpecificity);
	}

    public ClinicalAttributesMetadata() {
        super("","");
        this.columnHeader = "";
        this.datatype = "";
        this.aliases = "";
        this.annotationStatus = "";
        this.diseaseSpecificity = "";
        makePropertiesMap();
    }

    /**
     * properties map - used by code that updates row in the worksheet
     * (google spreadsheet api, removes certain symbols (including spaces) from column headings)
     */
    public void makePropertiesMap() {
        propertiesMap = new HashMap<String, String>();
        propertiesMap.put("COLUMNHEADER", this.columnHeader);
        propertiesMap.put("DISPLAYNAME", super.getDisplayName());
        propertiesMap.put("DESCRIPTION", super.getDescription());
        propertiesMap.put("DATATYPE", this.datatype);
        propertiesMap.put("ALIASES", this.aliases);
        propertiesMap.put("ANNOTATIONSTATUS", this.annotationStatus);
        propertiesMap.put("DISEASESPECIFICITY", this.diseaseSpecificity);

    }

	public String getColumnHeader() { return columnHeader; }
	public void setColumnHeader(String columnHeader) { this.columnHeader = columnHeader; makePropertiesMap(); }
	public String getDatatype() { return datatype; }
	public void setDatatype(String datatype) { this.datatype = datatype; makePropertiesMap(); }
	public String getAliases() { return aliases; }
	public void setAliases(String aliases) { this.aliases = aliases; makePropertiesMap(); }
	public String getAnnotationStatus() { return annotationStatus; }
	public void setAnnotationStatus(String annotationStatus) { this.annotationStatus = annotationStatus; makePropertiesMap(); }
	public String getDiseaseSpecificity() { return diseaseSpecificity; }
	public void setDiseaseSpecificity(String diseaseSpecificity) { this.diseaseSpecificity = diseaseSpecificity; makePropertiesMap(); }
	public Map<String, String> getPropertiesMap() { return propertiesMap; }
}
