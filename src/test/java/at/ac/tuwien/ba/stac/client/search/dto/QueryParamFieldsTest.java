package at.ac.tuwien.ba.stac.client.search.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryParamFieldsTest {

    private static final String TEMPLATE_JSON = "{\"include\":[%s],\"exclude\":[%s]}";
    private static final String TEMPLATE_URL_QUERY = "fields=%s";
    private static final List<String> INCLUDE_LIST = Arrays.asList("id", "properties.eo:cloud_cover");
    private static final List<String> EXCLUDE_LIST = Arrays.asList("geometry", "properties.datetime");

    private ObjectMapper mapper;

    @BeforeEach
    public void init(){
        this.mapper = new ObjectMapper();
    }

    @Test
    public void itShould_SerializeIncludeAndExclude() throws Exception{

        var fields = new QueryParamFields();
        fields.setInclude(INCLUDE_LIST);
        fields.setExclude(EXCLUDE_LIST);

        String res = mapper.writeValueAsString(fields);

        assertThat(res).isEqualTo(String.format(
                TEMPLATE_JSON,
                toJsonListContent(INCLUDE_LIST),
                toJsonListContent(EXCLUDE_LIST)
                )
        );
    }

    @Test
    public void itShould_SerializeIncludeOnly() throws Exception{

        var fields = new QueryParamFields();
        fields.setInclude(INCLUDE_LIST);

        String res = mapper.writeValueAsString(fields);

        assertThat(res)
                .contains(toJsonListContent(INCLUDE_LIST))
                .doesNotContain("\"exclude\":");
    }

    @Test
    public void itShould_SerializeExcludeOnly() throws Exception{

        var fields = new QueryParamFields();
        fields.setExclude(EXCLUDE_LIST);

        String res = mapper.writeValueAsString(fields);

        assertThat(res)
                .contains(toJsonListContent(EXCLUDE_LIST))
                .doesNotContain("\"include\":");
    }

    @Test
    public void itShould_SerializeUrlQuery() {

        var fields = new QueryParamFields();
        fields.setInclude(INCLUDE_LIST);
        fields.setExclude(EXCLUDE_LIST);

        String res = fields.toUrlQuery();

        assertThat(res)
                .startsWith("fields=")
                .contains(String.join(",", INCLUDE_LIST))
                .contains("-" + EXCLUDE_LIST.get(0));
    }

    @Test
    public void itShould_SerializeUrlQueryIncludeOnly() {

        var fields = new QueryParamFields();
        fields.setInclude(INCLUDE_LIST);

        String res = fields.toUrlQuery();

        assertThat(res)
                .startsWith("fields=")
                .contains(String.join(",", INCLUDE_LIST))
                .doesNotContain("-" + EXCLUDE_LIST.get(0));
    }

    @Test
    public void itShould_SerializeUrlQueryExcludeOnly() {

        var fields = new QueryParamFields();
        fields.setExclude(EXCLUDE_LIST);

        String res = fields.toUrlQuery();

        assertThat(res)
                .startsWith("fields=")
                .doesNotContain(String.join(",", INCLUDE_LIST))
                .contains("-" + EXCLUDE_LIST.get(0));
    }

    @Test
    public void itShould_notContain_doubleNegation_inQueryUrl() {

        var fields = new QueryParamFields();
        fields.addFieldToExclude("-not");

        String res = fields.toUrlQuery();

        assertThat(res)
                .doesNotContain("--not")
                .contains("-not");
    }

    private String toJsonListContent(List<String> list) {
        var quoteMarkList = list.stream().map(item -> "\"" + item + "\"").collect(Collectors.toList());
        return String.join(",", quoteMarkList);
    }
}
