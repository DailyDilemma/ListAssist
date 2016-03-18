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
        "Checked"
})
public class LAItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("Description")
    private Object Description;
    @JsonProperty("Checked")
    private Boolean Checked;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The ID
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param Description
     * The Description
     */
    @JsonProperty("id")
    public void setId(String id) { this.id = id; }

    /**
     *
     * @return
     * The Description
     */
    @JsonProperty("Description")
    public Object getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     * The Description
     */
    @JsonProperty("Description")
    public void setDescription(Object Description) {
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}