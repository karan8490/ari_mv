
package com.kpmg.mw.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "links" })

/**
 * A Project
 * 
 * @author Saurabh
 *
 */

public class ProjectVO extends ResponseVO {

	@JsonProperty("links")
	private List<Link> links = new ArrayList<Link>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The links
	 */
	@JsonProperty("links")
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * 
	 * @param links
	 *            The links
	 */
	@JsonProperty("links")
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "ProjectVO [links=" + links + "]";
	}

}
