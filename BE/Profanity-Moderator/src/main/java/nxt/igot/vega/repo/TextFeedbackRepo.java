package nxt.igot.vega.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import nxt.igot.vega.entity.TextFeedbackEntity;

public interface TextFeedbackRepo extends ElasticsearchRepository<TextFeedbackEntity, String>, CustomTextFeedbackRepo{

	List<TextFeedbackEntity> findByModeratedOrderByTimestampDesc(boolean moderated, Pageable pageableRequest);
	List<TextFeedbackEntity> findByModeratedAndFlagOrderByTimestampDesc(boolean moderated,String flag, Pageable pageableRequest);
	List<TextFeedbackEntity> findByModeratedOrderByTimestampAsc(boolean moderated, Pageable pageableRequest);
	List<TextFeedbackEntity> findByModeratedAndFlagOrderByTimestampAsc(boolean moderated,String flag, Pageable pageableRequest);
	List<TextFeedbackEntity> findByModerated(boolean moderated, Pageable pageableRequest);
}
