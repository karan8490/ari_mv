
package com.kpmg.mw.vo;

import java.util.HashMap;
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
@JsonPropertyOrder({ "name", "href", "id", "rel", "type", "position" })
public class Link {

	@JsonProperty("name")
	private String name;
	@JsonProperty("href")
	private String href;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("rel")
	private String rel;
	@JsonProperty("type")
	private String type;
	@JsonProperty("position")
	private Integer position;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The href
	 */
	@JsonProperty("href")
	public String getHref() {
		return href;
	}

	/**
	 * 
	 * @param href
	 *            The href
	 */
	@JsonProperty("href")
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * 
	 * @return The id
	 */
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The rel
	 */
	@JsonProperty("rel")
	public String getRel() {
		return rel;
	}

	/**
	 * 
	 * @param rel
	 *            The rel
	 */
	@JsonProperty("rel")
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The position
	 */
	@JsonProperty("position")
	public Integer getPosition() {
		return position;
	}

	/**
	 * 
	 * @param position
	 *            The position
	 */
	@JsonProperty("position")
	public void setPosition(Integer position) {
		this.position = position;
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
		return "Link [name=" + name + ", href=" + href + ", id=" + id + ", rel=" + rel + ", type=" + type
				+ ", position=" + position + "]";
	}

}
