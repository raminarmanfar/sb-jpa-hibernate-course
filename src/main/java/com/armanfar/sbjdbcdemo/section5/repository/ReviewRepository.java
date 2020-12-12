package com.armanfar.sbjdbcdemo.section5.repository;

import com.armanfar.sbjdbcdemo.section5.model.Review;
import com.armanfar.sbjdbcdemo.section5.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class ReviewRepository {

    @Autowired
    EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Review> findAll() {
        return em.createNamedQuery("find_all_reviews", Review.class).getResultList();
    }

    public Review findById(Long id) {
        return em.find(Review.class, id);
    }

    public Review save(Review review) {
        if (review.getId() == null) {
            em.persist(review);
        } else {
            em.merge(review);
        }
        return review;
    }

    public void deleteById(Long id) {
        Review review = findById(id);
        if(review != null) {
            em.remove(review);
        }
    }
}
