package my.exercise.cryptocurrency.repository


import my.exercise.cryptocurrency.util.TestUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.ShallowEtagHeaderFilter
import spock.lang.Specification

import java.sql.Connection

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfitAnalysisApplicationTests extends Specification{

    private static final String POPULATE_DATA_SCRIPT = "sql/data.sql"
    @Autowired
    protected WebApplicationContext context

    protected MockMvc mvc;
    @Autowired
    private PriceListRepository priceListRepository

    protected String baseUrl = null
    protected String resourceIdName = null
    protected String serviceName = null

    def setup() {
        mvc = buildDefaultMockMvc()
        serviceName = "priceList"
        baseUrl = "/api/quotes"
        resourceIdName = "currency"
    }

    def "Get all Currency Price List"() {
        when: "Call - get all Currency Price List"

        def response = mvc.perform(get("/api/quotes")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())


        then: "We expect we got a successful response"
        response.andExpect(status().isOk())

        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("AllQuotes.json")))
    }

    def "Get BTC Quotes"() {
        when: "Call get BTC Quotes end point"

        def response = mvc.perform(get("/api/quotes/BTC")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())

        then: "We expect we got a successful response"
        response.andExpect(status().isOk())

        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("BTCQuotes.json")))
    }

    def "Get ETC Quotes"() {
        when: "Call get ETC Quotes end point"

        def response = mvc.perform(get("/api/quotes/ETC")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())

        then: "We expect we got a successful response"
        response.andExpect(status().isOk())

        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("ETCQuotes.json")))
    }

    def "Get LTC Quotes"() {
        when: "Call get LTC Quotes end point"

        def response = mvc.perform(get("/api/quotes/LTC")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())


        then: "We expect we got a successful response"
        response.andExpect(status().isOk())

        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("LTCQuotes.json")))
    }

    def "Get BTC Profit"() {
        when: "Call get BTC Quotes end point"

        def response = mvc.perform(get("/api/profits/BTC")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())

        then: "We expect we got a successful response"
        response.andExpect(status().isOk())

        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("BTCProfit.json")))
    }

    def "Get ETC Profit"() {
        when: "Call get BTC Quotes end point"

        def response = mvc.perform(get("/api/profits/ETC")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())

        then: "We expect we got a successful response"
        response.andExpect(status().isOk())

        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("ETCProfit.json")))
    }

    def "Get LTC Profit"() {
        when: "Call get BTC Quotes end point"

        def response = mvc.perform(get("/api/profits/LTC")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())

        then: "We expect we got a successful response"
        response.andExpect(status().isOk())

        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("LTCProfit.json")))
    }

    protected String getAllResponseFileData(String filename) {
        return TestUtil.readResourceFile("data" + "/" + filename)
    }

    void runDbScript(String scriptName, boolean commit) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator()
        populator.addScript(new ClassPathResource(scriptName));
        Connection connection = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource)
            populator.populate(connection)
            if (commit) {
                connection.commit()
            }
        }
        finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource)
            }
        }
    }

    MockMvc buildDefaultMockMvc() {
        return MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new ShallowEtagHeaderFilter())
                .build()
    }


}