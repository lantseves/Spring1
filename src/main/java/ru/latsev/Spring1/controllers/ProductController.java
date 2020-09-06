package ru.latsev.Spring1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.latsev.Spring1.services.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService service ;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String getAllProduct(Model model) {
        model.addAttribute("products" , service.getAllProduct()) ;
        return "product_list";
    }

    @RequestMapping(value = "/max" , method = RequestMethod.GET)
    public String getAllProductMax(Model model,
                                   @RequestParam("max") double max) {
        model.addAttribute("products" , service.getProductByPriceLess(max)) ;
        return "product_list";
    }


    @RequestMapping(value = "/min" , method = RequestMethod.GET)
    public String getAllProductMin(Model model,
                                   @RequestParam("min") double min) {
        model.addAttribute("products" , service.getProductByPriceGreater(min)) ;
        return "product_list";
    }

    @RequestMapping(value = "/between" , method = RequestMethod.GET)
    public String getAllProductBetween(Model model,
                                       @RequestParam("min") double min,
                                       @RequestParam("max") double max) {
        model.addAttribute("products" , service.getProductByPriceBetween(min , max)) ;
        return "product_list";
    }

    /////////-------- Задание 4 ----------////////////
    @RequestMapping(value = "/page" , method = RequestMethod.GET)
    public String getAllProductPage(Model model,
                                    @RequestParam("page") int pageNumber) {
        model.addAttribute("products" , service.getPageProduct(pageNumber)) ;
        model.addAttribute("pageNumberDown" , "page?page=" + (pageNumber - 1)) ;
        model.addAttribute("pageNumberUp" , "page?page=" + (pageNumber + 1)) ;
        return "page_product_list";
    }

    @RequestMapping(value = "/page/min" , method = RequestMethod.GET)
    public String getAllProductPageMin(Model model,
                                       @RequestParam("page") int pageNumber,
                                       @RequestParam("min") int min) {
        model.addAttribute("products" , service.getProductPageByPriceGreater(pageNumber, min)) ;
        model.addAttribute("pageNumberDown" , String.format("min?page=%d&min=%d" , pageNumber - 1, min)) ;
        model.addAttribute("pageNumberUp" , String.format("min?page=%d&min=%d" , pageNumber + 1, min)) ;
        return "page_product_list";
    }

    @RequestMapping(value = "/page/max" , method = RequestMethod.GET)
    public String getAllProductPageMax(Model model,
                                       @RequestParam("page") int pageNumber,
                                       @RequestParam("max") int max) {
        model.addAttribute("products" , service.getProductPageByPriceLess(pageNumber, max)) ;
        model.addAttribute("pageNumberDown" , String.format("max?page=%d&max=%d" , pageNumber - 1, max)) ;
        model.addAttribute("pageNumberUp" , String.format("max?page=%d&max=%d" , pageNumber + 1, max)) ;
        return "page_product_list";
    }

    @RequestMapping(value = "/page/between" , method = RequestMethod.GET)
    public String getAllProductPageBetween(Model model,
                                           @RequestParam("page") int pageNumber,
                                           @RequestParam("max") int max,
                                           @RequestParam("min") int min) {
        model.addAttribute("products" , service.getProductPageByPriceBetween(pageNumber, min, max)) ;
        model.addAttribute("pageNumberDown" , String.format("between?page=%d&min=%d&max=%d" , pageNumber - 1, min, max)) ;
        model.addAttribute("pageNumberUp" , String.format("between?page=%d&min=%d&max=%d" , pageNumber + 1, min, max)) ;
        return "page_product_list";
    }
}
