package com.comp4350.listassist.objects;

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
@JsonPropertyOrder({
        "Name",
        "LAItem",
        "Id"
})
public class LAList {

    @JsonProperty("Name")
    private String Name;
    @JsonProperty("ShoppingListItems")
    private List<LAItem> LAItems = new ArrayList<LAItem>();
    @JsonProperty("Id")
    private Integer Id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Name
     */
    @JsonProperty("Name")
    public String getName() {
        return Name;
    }

    /**
     *
     * @param Name
     * The Name
     */
    @JsonProperty("Name")
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     *
     * @return
     * The LAItem
     */
    @JsonProperty("ShoppingListItems")
    public List<com.comp4350.listassist.objects.LAItem> getLAItems() {
        return LAItems;
    }

    /**
     *
     * @param LAItem
     * The LAItem
     */
    @JsonProperty("ShoppingListItems")
    public void setLAItems(List<LAItem> LAItem) {
        this.LAItems = LAItem;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}