package com.jq.scrabble.scrabble_calculator.service;

import com.jq.scrabble.scrabble_calculator.entity.Score;
import com.jq.scrabble.scrabble_calculator.repo.ScoreRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScoreServiceTest {
    @Autowired
    private ScoreRepo repo;

    @Test
    void findTop10ByOrderByScoreDescCreatedAtAsc_ordersAndLimits() {
        // Arrange: insert >10 rows with varying scores; tie some scores with different createdAt
        var now = java.time.Instant.now();
        repo.save(new Score("A", 10)); // set createdAt via @PrePersist or manually if needed
        repo.save(new Score("B", 30));
        repo.save(new Score("C", 30)); // same score as B; earlier createdAt should come first
        repo.save(new Score("D", 40)); // same score as B; earlier createdAt should come first
        repo.save(new Score("E", 50)); // same score as B; earlier createdAt should come first
        repo.save(new Score("F", 60)); // same score as B; earlier createdAt should come first
        repo.save(new Score("G", 70)); // same score as B; earlier createdAt should come first
        repo.save(new Score("H", 80)); // same score as B; earlier createdAt should come first
        repo.save(new Score("I", 90)); // same score as B; earlier createdAt should come first
        repo.save(new Score("J", 100)); // same score as B; earlier createdAt should come first
        repo.save(new Score("K", 110)); // same score as B; earlier createdAt should come first
        // ... add more so total > 10
        // (If your entity relies on DB default timestamp, ensure it’s populated; otherwise set createdAt)

        // Act
        var top = repo.findTop10ByOrderByScoreDescCreatedAtAsc();

        // Assert: size==11, sorted by score desc; for ties, earlier createdAt first
        assertEquals(10, top.size());
        for (int i = 1; i < top.size(); i++) {
            var prev = top.get(i-1);
            var cur  = top.get(i);
            boolean ok = cur.getScore() <= prev.getScore()
                    && (cur.getScore() < prev.getScore()
                    || !cur.getCreatedAt().isBefore(prev.getCreatedAt()));
            assertTrue(ok, "Ordering by score desc, then createdAt asc violated");
        }
    }
}