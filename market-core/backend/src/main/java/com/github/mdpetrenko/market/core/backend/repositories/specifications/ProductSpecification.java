package com.github.mdpetrenko.market.core.backend.repositories.specifications;


import com.github.mdpetrenko.market.core.backend.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> priceGreaterThanOrEqualTo(Integer price) {
        return (r, cq, cb) -> cb.greaterThanOrEqualTo(r.get("price"), price);
    }

    public static Specification<Product> priceLessThanOrEqualTo(Integer price) {
        return (r, cq, cb) -> cb.lessThanOrEqualTo(r.get("price"), price);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (r, cq, cb) -> cb.like(r.get("title"), String.format("%%%s%%", titlePart));
    }
}
