package at.ac.tuwien.ba.stac.client.search.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this class is a POJO containing the fields parameter of an STAC search.
 * see https://api.stacspec.org/v1.0.0-rc.1/item-search/#operation/getItemSearch
 * for detailed information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryParamFields {

    private List<String> include = new ArrayList<>();
    private List<String> exclude = new ArrayList<>();


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> getInclude() {
        return include;
    }

    public void setInclude(List<String> include) {
        this.include = include;
    }

    public void addFieldToInclude(String field) {
        this.include.add(field);
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> getExclude() {
        return exclude;
    }

    public void setExclude(List<String> exclude) {
        this.exclude = exclude;
    }

    public void addFieldToExclude(String field) {
        this.exclude.add(field);
    }

    @JsonIgnore
    String toUrlQuery() {
        var excludeSigned = this.exclude.stream().map(
                entry -> entry.startsWith("-") ? entry : "-" + entry
        ).collect(Collectors.toList());
        return "fields=" + String.join(",", include) + "," + String.join(",", excludeSigned);
    }

}
