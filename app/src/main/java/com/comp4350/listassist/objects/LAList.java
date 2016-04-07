package com.comp4350.listassist.objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "Name",
        "ShoppingListItems",
        "ShoppingListSuggestions",
        "Id"
})
public class LAList {

    @JsonProperty("Name")
    private String Name;
    @JsonProperty("ShoppingListItems")
    private List<ShoppingListItem> ShoppingListItems = new ArrayList<ShoppingListItem>();
    @JsonProperty("ShoppingListSuggestions")
    private List<SuggestedListItem> SuggestedListItems = new ArrayList<SuggestedListItem>();
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
     * The ShoppingListItems
     */
    @JsonProperty("ShoppingListItems")
    public List<ShoppingListItem> getShoppingListItems() {
        return ShoppingListItems;
    }

    /**
     *
     * @param ShoppingListItems
     * The ShoppingListItems
     */
    @JsonProperty("ShoppingListItems")
    public void setShoppingListItems(List<ShoppingListItem> ShoppingListItems) {
        this.ShoppingListItems = ShoppingListItems;
    }

    /**
     *
     * @return
     * The SuggestedListItems
     */
    @JsonProperty("ShoppingListSuggestions")
    public List<SuggestedListItem> getSuggestedListItems() {
        return SuggestedListItems;
    }

    /**
     *
     * @param SuggestedListItems
     * The SuggestedListItems
     */
    @JsonProperty("ShoppingListSuggestions")
    public void setSuggestedListItems(List<SuggestedListItem> SuggestedListItems) {
        this.SuggestedListItems = SuggestedListItems;
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