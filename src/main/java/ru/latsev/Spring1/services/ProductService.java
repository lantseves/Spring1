package ru.latsev.Spring1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.latsev.Spring1.entities.Product;
import ru.latsev.Spring1.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository repository ;
    private final int PAGE_SIZE = 5 ;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProduct() {
        return repository.findAll() ;
    }

    /*
    3. С помощью GET-запроса указывать фильтрацию по:
    b. только максимальной,
    */
    public List<Product> getProductByPriceLess(double max) {
        return repository.findAllByPriceLessThanEqual(max) ;
    }
    /*
    3. С помощью GET-запроса указывать фильтрацию по:
    a. только минимальной,
    */
    public List<Product> getProductByPriceGreater(double min) {
        return repository.findAllByPriceGreaterThanEqual(min) ;
    }

    /*
    3. С помощью GET-запроса указывать фильтрацию по:
    c. минимальной и максимальной цене.
     */
    public List<Product> getProductByPriceBetween(double min, double max) {
        return repository.findAllByPriceBetween(min , max) ;
    }

    // * Добавить постраничное отображение (по 5 записей на странице).
    public List<Product> getPageProduct(int pageNumber) {
        Page<Product> pr = repository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
        return pr.stream().collect(Collectors.toList());
    }

    public List<Product> getProductPageByPriceGreater(int pageNumber, double min) {
        Page<Product> pr = repository.findAllByPriceGreaterThanEqual(PageRequest.of(pageNumber, PAGE_SIZE) , min) ;
        return pr.stream().collect(Collectors.toList());
    }

    public List<Product> getProductPageByPriceLess(int pageNumber, double max) {
        Page<Product> pr = repository.findAllByPriceLessThanEqual(PageRequest.of(pageNumber, PAGE_SIZE), max) ;
        return pr.stream().collect(Collectors.toList());
    }

    public List<Product> getProductPageByPriceBetween(int pageNumber, double min, double max) {
        Page<Product> pr = repository.findAllByPriceBetween(PageRequest.of(pageNumber, PAGE_SIZE), min , max) ;
        return pr.stream().collect(Collectors.toList());
    }
}
