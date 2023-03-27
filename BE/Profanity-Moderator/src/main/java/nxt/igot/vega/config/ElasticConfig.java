package nxt.igot.vega.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@Primary
public class ElasticConfig extends AbstractFactoryBean {
	
	Logger log = LoggerFactory.getLogger(ElasticConfig.class);

	@Value("${spring.elastic.host}")
	String host;


	@Value("${spring.elastic.port}")
	Integer port;
	
	@Value("${spring.elastic.transport}")
	String transport;
	
	@Value("${spring.elastic.user}")
	String userName;

	@Value("${spring.elastic.pass}")
	String password;


	private RestHighLevelClient restHighLevelClient;


	@Override
	public Class<RestHighLevelClient> getObjectType() {
		return RestHighLevelClient.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	private RestHighLevelClient buildClient() {
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
		try {
			restHighLevelClient = new RestHighLevelClient(
					RestClient.builder(new HttpHost(host,port, transport))
					.setHttpClientConfigCallback(
							httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return restHighLevelClient;
	}

	@Override
	protected Object createInstance() throws Exception {
		return buildClient();
	}

}
