import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.codecentric.de.resilience.transport.dto.ConnoteDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class JsonParserTest {

    @Test
    public void parserJsonStringToList() throws Exception {
        String given =
            "{\"connoteList\":[334613576,333241878,331278358,336472994,337970194,332464252,335526510,335766368,339003069,339318711]}";

        JsonParser jsonParser = null;
        ObjectMapper mapper = new ObjectMapper();
        ConnoteDTO connoteDTO = mapper.readValue(given, ConnoteDTO.class);

        assertThat(connoteDTO.getConnoteList().size(), is(10));
    }
}
