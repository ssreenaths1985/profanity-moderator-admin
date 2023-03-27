package nxt.igot.vega.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
	@Value(value = "${spring.kafka.bootstrapAddress}")
    private String bootstrapAddress;
	
	@Value(value="${spring.kafka.topicname.flagged}")
	String flagged;
	@Value(value="${spring.kafka.topicname.moderated}")
	String moderated;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
    
    @Bean
    public NewTopic topic1() {
         return new NewTopic(flagged, 1, (short) 1);
    }
    
    @Bean
    public NewTopic topic2() {
         return new NewTopic(moderated, 1, (short) 1);
    }
}
