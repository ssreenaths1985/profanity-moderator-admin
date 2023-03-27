package nxt.igot.vega.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import springfox.documentation.spring.web.json.Json;

public class CustomTextFeedbackRepoImpl implements CustomTextFeedbackRepo{
	
	@Autowired
	RestHighLevelClient restHighLevelClient;
	@Value("${profanity.moderation.index}")
	String moderationIndex;
	

	@Override
	public List<String> finddistinctFlags() {
		List<String> result = new ArrayList<>();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		TermsAggregationBuilder aggregation = AggregationBuilders.terms("flag")
		        .field("flag.keyword");
		
		searchSourceBuilder.aggregation(aggregation);
		searchSourceBuilder.size(0);
		SearchRequest searchRequest = new SearchRequest(moderationIndex);
		searchRequest.source(searchSourceBuilder);
		try {
			SearchResponse resp = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
			 String respJosn = resp.toString();
			 JsonElement obj = JsonParser.parseString(respJosn);
			 JsonElement aggObj = obj.getAsJsonObject().get("aggregations");
			 JsonElement streamObj = aggObj.getAsJsonObject().get("sterms#flag");
			 JsonElement bucketObj = streamObj.getAsJsonObject().get("buckets");
			 for(JsonElement flag : bucketObj.getAsJsonArray()) {
				 result.add(flag.getAsJsonObject().get("key").toString().replace("\"", ""));
			 }
			
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

}
