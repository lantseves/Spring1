package ru.latsev.Spring1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.latsev.Spring1.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product , Long> {

    List<Product> findAll() ;

    List<Product> findAllByPriceBetween(double min, double max);

    List<Product> findAllByPriceLessThanEqual(double max);

    List<Product> findAllByPriceGreaterThanEqual(double min);

    Page<Product> findAllByPriceBetween(Pageable pageable, double min, double max);

    Page<Product> findAllByPriceLessThanEqual(Pageable pageable, double max);

    Page<Product> findAllByPriceGreaterThanEqual(Pageable pageable, double min);
}
