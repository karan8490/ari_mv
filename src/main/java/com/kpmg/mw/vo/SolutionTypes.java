/**
 * 
 */
package com.kpmg.mw.vo;

/**
 * @author 
 *
 */
public class SolutionTypes {

    private final long id;
    private final String type;
    private String solutionTypes[];
    
	public String[] getSolTypes() {
		return solutionTypes;
	}

	public String[] setSolTypes() {
		solutionTypes = new String [2];
		solutionTypes[0] = ".Net/IIS";
		solutionTypes[1] = "Java/Tomcat";			
		return solutionTypes;
	}

	public SolutionTypes(long id, String content, String [] solTypes) {
        this.id = id;
        this.type = content;
        this.solutionTypes = solTypes;
    }

	public SolutionTypes() {
        this.type = "SolutionTypes";
        this.id = 1;
        solutionTypes = this.setSolTypes();
	}

	public long getId() {
        return id;
    }

    public String getContent() {
        return type;
    }
    
}