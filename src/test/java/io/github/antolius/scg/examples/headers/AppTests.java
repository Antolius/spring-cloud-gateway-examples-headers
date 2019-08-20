package io.github.antolius.scg.examples.headers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 0)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
		properties = {"backend.url=http://localhost:${wiremock.server.port}"})
public class AppTests {

	@Autowired
	private WebTestClient client;

	@Test
	public void shouldKeepBackendConnectionAlive() {
		// given
		stubFor(get(urlEqualTo("/foo/bars")).willReturn(aResponse().withStatus(200)));

		// when
		var actual = client.get().uri("/foo/bars").exchange();

		// then
		verify(getRequestedFor(urlEqualTo("/foo/bars")).withHeader("Connection",
				equalTo("keep-alive")));
		actual.expectStatus().isOk();
	}

}
