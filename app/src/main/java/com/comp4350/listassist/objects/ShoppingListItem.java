package com.comp4350.listassist.objects;

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
@JsonPropertyOrder({
        "Description",
        "Checked",
        "Id",
        "ListId"
})
public class ShoppingListItem {

    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Checked")
    private Boolean Checked;
    @JsonProperty("Id")
    private Integer Id;
    @JsonProperty("ListId")
    private Integer ListId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     * The Checked
     */
    @JsonProperty("Checked")
    public Boolean getChecked() {
        return Checked;
    }

    /**
     *
     * @param Checked
     * The Checked
     */
    @JsonProperty("Checked")
    public void setChecked(Boolean Checked) {
        this.Checked = Checked;
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