package nxt.igot.vega.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import nxt.igot.vega.entity.ImageFeedbackEntity;

public interface ImageFeedbackRepo extends ElasticsearchRepository<ImageFeedbackEntity, Integer>{
	List<ImageFeedbackEntity> findByModerated(boolean moderated);
	List<ImageFeedbackEntity> findByUrl(String url);
}
