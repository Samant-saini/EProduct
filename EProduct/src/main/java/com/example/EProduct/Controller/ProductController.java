package com.example.EProduct.Controller;
import com.example.EProduct.Model.ProductDto;
import com.example.EProduct.ProductServices.ProductServices;
import java.nio.file.Path;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;


import com.example.EProduct.Model.Product;
import com.example.EProduct.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller

@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo repo;
//    private ProductServices services;




    @GetMapping({"","/"})
    public String ShowProductList(Model model){
        List<Product> products=repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        System.out.println(products);
        model.addAttribute("products",products);
        return "products/index";
    }

//    @RequestMapping("/products") // use to get the products name
//    public List<Product> getProduct(){
//        return services.getProduct();
//
//    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        ProductDto productDto=new ProductDto();
        model.addAttribute("productDto",productDto);
        return "products/createProduct";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ) {
        if (productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError(
                    "productDto",
                    "imageFile",
                    "The image file is required."
            ));
        }
        if(result.hasErrors()){
            return "products/createProduct";
        }
        // save image file
        MultipartFile image = productDto.getImageFile();

        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = (Path) Paths.get(uploadDir);

            if (!Files.exists((java.nio.file.Path) uploadPath)) {
                Files.createDirectories((java.nio.file.Path) uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(createdAt); // Assuming createdAt is defined
        product.setImageName(storageFileName); // Assuming storageFileName is defined\
        repo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {
        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());

            model.addAttribute("productDto", productDto);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }

        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ) {
        try {
            Product product = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
            model.addAttribute("product", product);

            if (result.hasErrors()) {
                return "products/EditProduct";
            }

            if (!productDto.getImageFile().isEmpty()) {
                // delete old image
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }

                // save new image file
                MultipartFile image = productDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }

                product.setImageName(storageFileName);
            }
            product.setName(productDto.getName());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            repo.save(product);

        }

    catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
        return "redirect:/products";


    }
    @GetMapping("/delete")
    public String deleteProduct (
            @RequestParam int id
    ) {
        try {
            Product product = repo.findById(id).get();

            // delete product image
            Path imagePath = Paths.get("public/images/" + product.getImageName()); // Corrected method name

            try {
                Files.delete(imagePath);
            }
            catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }

            // Delete the product
            repo.delete(product); // Added product deletion
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/products";
    }


}
