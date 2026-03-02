package org.adarsh.weather.repository;

import org.adarsh.weather.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    SearchHistory findTopByCityIgnoreCaseOrderBySearchedAtDesc(String city);

    List<SearchHistory> findTop5ByOrderBySearchedAtDesc();
}
