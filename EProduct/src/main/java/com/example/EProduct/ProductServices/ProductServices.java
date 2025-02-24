package com.example.EProduct.ProductServices;


import com.example.EProduct.Model.Product;
import com.example.EProduct.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {



    @Autowired
    ProductRepo repo;


    public List<Product> getProduct(){

//

        return repo.findAll();
    }

}
