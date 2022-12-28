package com.proudmur.articlesbackend.service;

import com.proudmur.articlesbackend.dao.RatingDao;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingDao ratingDao;

    @Autowired
    private DataSource dataSource;

    public void rateArticle(Integer userId, Integer articleId, Integer rating) {
        if (Boolean.TRUE.equals(ratingDao.rated(userId, articleId))) {
            ratingDao.clearRating(userId, articleId);
        }
        ratingDao.rateArticle(userId, articleId, rating);
    }

    public List<Integer> getRecommendation(int userId, int size) {

        JDBCDataModel dataModel = new PostgreSQLJDBCDataModel(dataSource, "ratings", "user_id", "article_id", "rating", null);
        CityBlockSimilarity similarity = new CityBlockSimilarity(dataModel);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

        try {
            List<RecommendedItem> recommended = recommender.recommend(userId, size);
            System.out.println(recommended.size());
            return recommended.stream().map(RecommendedItem::getItemID).map(Long::intValue).collect(Collectors.toList());
        } catch (TasteException e) {
            return Collections.emptyList();
        }
    }

}
