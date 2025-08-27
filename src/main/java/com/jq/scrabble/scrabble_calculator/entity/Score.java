package com.jq.scrabble.scrabble_calculator.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // matches AUTO_INCREMENT in MySQL
    private Long id;

    @Column(nullable = false, length = 32)
    private String word;

    @Column(nullable = false)
    private int score;

    @Column(name = "created_at", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    // Constructors
    public Score() {}

    public Score(String word, int score) {
        this.word = word;
        this.score = score;
    }

    // Ensure createdAt is set if JPA doesn’t fill it
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    // Getters & setters
    public Long getId() { return id; }

    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
