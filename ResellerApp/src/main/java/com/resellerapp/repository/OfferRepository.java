package com.resellerapp.repository;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByUser(User user);

    @Query(nativeQuery = true,value = "SELECT * FROM offers WHERE bought_by_id IS NULL")
    List<Offer> getAllAvailable();

    List<Offer> findAllByBoughtBy_Username(String username);

}
