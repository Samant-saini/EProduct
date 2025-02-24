package com.example.EProduct.Controller;
import com.example.EProduct.ProductServices.ProductServices;
import org.springframework.ui.Model;


import com.example.EProduct.Model.Product;
import com.example.EProduct.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller

@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo repo;
//    private ProductServices services;




    @GetMapping({"","/"})
    public String ShowProductList(Model model){
        List<Product> products=repo.findAll();
        System.out.println(products);
        model.addAttribute("products",products);
        return "products/index";
    }

//    @RequestMapping("/products") // use to get the products name
//    public List<Product> getProduct(){
//        return services.getProduct();
//
//    }


}
