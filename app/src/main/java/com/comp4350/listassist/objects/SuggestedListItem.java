package com.comp4350.listassist.objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "Id",
        "ListId",
        "Description"
})
public class SuggestedListItem {
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Id")
    private Integer Id;
    @JsonProperty("ListId")
    private Integer ListId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SuggestedListItem()
    {

    }

    public SuggestedListItem(String description, Integer id, Integer listId)
    {
        this.Description = description;
        this.Id = id;
        this.ListId = listId;
    }

    /**
     *
     * @return
     * The Description
     */
    @JsonProperty("Description")
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     * The Description
     */
    @JsonProperty("Description")
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     * The Id
     */
    @JsonProperty("Id")
    public Integer getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The Id
     */
    @JsonProperty("Id")
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The ListId
     */
    @JsonProperty("ListId")
    public Integer getListId() {
        return ListId;
    }

    /**
     *
     * @param ListId
     * The ListId
     */
    @JsonProperty("ListId")
    public void setListId(Integer ListId) {
        this.ListId = ListId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
