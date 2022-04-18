package com.github.mdpetrenko.market.core.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @Enumerated(EnumType.STRING)
    private Categories title;

    @OneToMany(mappedBy = "category")
    private List<Product> products;


    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Categories {
        CLASSIC("Classic"), ELECTRIC("Electric"), CHILD("Child");

        private final String title;

        Categories(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

}
