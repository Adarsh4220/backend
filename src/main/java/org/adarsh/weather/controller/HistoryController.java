package org.adarsh.weather.controller;

import org.adarsh.weather.entity.SearchHistory;
import org.adarsh.weather.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private SearchHistoryRepository repository;

    @GetMapping
    public List<SearchHistory> getHistory() {
        return repository.findTop5ByOrderBySearchedAtDesc();
    }
    }
