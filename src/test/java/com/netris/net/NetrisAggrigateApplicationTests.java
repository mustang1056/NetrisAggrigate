package com.netris.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class NetrisAggrigateApplicationTests {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void check_valid_json() throws JSONException {
		StringBuilder testTemplateJson = new StringBuilder();

		testTemplateJson.append("[{\"id\":\"2\", \"urlType\":\"LIVE\",\"videoUrl\":\"rtsp://127.0.0.1/20\", \"ttl\":\"180\", \"value\":\"fa4b5f64-249b-11e9-ab14-d663bd873d93\"},");
		testTemplateJson.append("{\"id\":\"20\", \"urlType\":\"ARCHIVE\",\"videoUrl\":\"rtsp://127.0.0.1/2\", \"ttl\":\"60\", \"value\":\"fa4b5b22-249b-11e9-ab14-d663bd873d93\"},");
		testTemplateJson.append("{\"id\":\"1\", \"urlType\":\"LIVE\",\"videoUrl\":\"rtsp://127.0.0.1/1\", \"ttl\":\"120\", \"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\"},");
		testTemplateJson.append("{\"id\":\"3\", \"urlType\":\"ARCHIVE\",\"videoUrl\":\"rtsp://127.0.0.1/3\", \"ttl\":\"120\", \"value\":\"fa4b5d52-249b-11e9-ab14-d663bd873d93\"}]");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange("/callable", HttpMethod.GET, entity, String.class);


		JSONAssert.assertEquals(testTemplateJson.toString(), response.getBody(), false);


	}

}
