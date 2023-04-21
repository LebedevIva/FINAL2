package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.repositories.ProductRepository;
import com.example.springsecurityapplication.servises.ProductServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class MainController {

    private final ProductRepository productRepository;

    private  final ProductServise productServise;

    @Autowired
    public MainController(ProductRepository productRepository, ProductServise productServise) {
        this.productRepository = productRepository;
        this.productServise = productServise;
    }

//    Метод предназначен для отображения товаров без прохождения аутентификации и валидации
    @GetMapping("")
    public String getAllProduct(Model model){
        model.addAttribute("products", productServise.getAllProduct());
        return "product/product";
    }

    @GetMapping("/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productServise.getProductId(id));
        return "product/infoProduct";
    }

    @PostMapping("/product/searching")
    public String searchProduct(@RequestParam("search") String search, @RequestParam("ot") String Ot, @RequestParam("do") String Do, @RequestParam(value = "price", required = false, defaultValue = "") String price, @RequestParam(value = "category", required = false, defaultValue = "") String category, Model model){

        //*1234
        if(!search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && !price.isEmpty() && !category.isEmpty()){
            if(price.equals("ascending_price")){
                switch (category){
                    case ("shoes"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPrice(search, Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                        break;
                    case ("cloth"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPrice(search, Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPrice(search, Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                        break;
                }
            } else if(price.equals("descending_price")){
                switch (category){
                    case ("shoes"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search, Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                        break;
                    case ("cloth"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search, Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search, Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                        break;
                }
            }
        }

        //*234
        if(search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && !price.isEmpty() && !category.isEmpty()){
            if(price.equals("ascending_price")){
                switch (category){
                    case ("shoes"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPrice(Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                        break;
                    case ("cloth"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPrice(Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPrice(Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                        break;
                }
            } else if(price.equals("descending_price")){
                switch (category){
                    case ("shoes"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                        break;
                    case ("cloth"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                        break;
                }
            }
        }


        //*123
        if(!search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && !price.isEmpty() && category.isEmpty()){
            if(price.equals("ascending_price")){
                model.addAttribute("search_product", productRepository.findByTitleOrderByPrice(search, Float.parseFloat(Ot), Float.parseFloat(Do)));
            } else if(price.equals("descending_price")){
                model.addAttribute("search_product", productRepository.findByTitleOrderByPriceDesc(search, Float.parseFloat(Ot), Float.parseFloat(Do)));
            }
        }

        //*34
        if(search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && !price.isEmpty() && !category.isEmpty()){
            if(price.equals("ascending_price")){
                switch (category){
                    case ("shoes"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPrice(1));
                        break;
                    case ("cloth"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPrice(2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPrice(3));
                        break;
                }
            } else if(price.equals("descending_price")){
                switch (category){
                    case ("shoes"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPriceDesc(1));
                        break;
                    case ("cloth"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPriceDesc(2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPriceDesc(3));
                        break;
                }
            }
        }

        //*24
        if(search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && price.isEmpty() && !category.isEmpty()){
            switch (category){
                case ("shoes"):
                    model.addAttribute("search_product", productRepository.CategoryAndPriceGreaterThanEqualAndPriceLessThan(Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                    break;
                case ("cloth"):
                    model.addAttribute("search_product", productRepository.CategoryAndPriceGreaterThanEqualAndPriceLessThan(Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                    break;
                case ("accessories"):
                    model.addAttribute("search_product", productRepository.CategoryAndPriceGreaterThanEqualAndPriceLessThan(Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                    break;
            }
        }

        //*23
        if(search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && !price.isEmpty() && category.isEmpty()){
            if(price.equals("ascending_price")){
                model.addAttribute("search_product", productRepository.PriceGreaterThanEqualAndPriceLessThanOrderByPrice(Float.parseFloat(Ot), Float.parseFloat(Do)));
            } else if(price.equals("descending_price")){
                model.addAttribute("search_product", productRepository.PriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(Float.parseFloat(Ot), Float.parseFloat(Do)));
            }
        }

        //*12
        if(!search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && price.isEmpty() && category.isEmpty()){
            model.addAttribute("search_product", productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThan(search, Float.parseFloat(Ot), Float.parseFloat(Do)));
        }

        //*13
        if(!search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && !price.isEmpty() && category.isEmpty()){
            if(price.equals("ascending_price")){
                model.addAttribute("search_product", productRepository.findByTitleAndOrderByPrice(search));
            } else if(price.equals("descending_price")){
                model.addAttribute("search_product", productRepository.findByTitleAndOrderByPriceDesc(search));
            }
        }

        //*14
        if (!search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && price.isEmpty() && !category.isEmpty()){
            switch (category){
                case ("shoes"):
                    model.addAttribute("search_product", productRepository.findByTitleAndCategory(search, 1));
                    break;
                case ("cloth"):
                    model.addAttribute("search_product", productRepository.findByTitleAndCategory(search, 2));
                    break;
                case ("accessories"):
                    model.addAttribute("search_product", productRepository.findByTitleAndCategory(search, 3));
                    break;
            }
        }

        //*1
        if(!search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && price.isEmpty() && category.isEmpty()){
            model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));
        }

        //*2
        if(search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && price.isEmpty() && category.isEmpty()){
            model.addAttribute("search_product", productRepository.findByPriceGreaterThanEqualAndPriceLessThan(Float.parseFloat(Ot), Float.parseFloat(Do)));
        }

        //*3
        if(search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && !price.isEmpty() && category.isEmpty()){
            if(price.equals("ascending_price")){
                model.addAttribute("search_product", productRepository.OrderByPrice());
            } else if (price.equals("descending_price")) {
                model.addAttribute("search_product", productRepository.OrderByPriceDesc());
            }
        }

        //*4
        if(search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && price.isEmpty() && !category.isEmpty()){
            switch (category){
                case ("shoes"):
                    model.addAttribute("search_product", productRepository.findByCategory(1));
                    break;
                case ("cloth"):
                    model.addAttribute("search_product", productRepository.findByCategory(2));
                    break;
                case ("accessories"):
                    model.addAttribute("search_product", productRepository.findByCategory(3));
                    break;
            }
        }

        model.addAttribute("value_searching", search);
        model.addAttribute("value_ot", Ot);
        model.addAttribute("value_do", Do);
        model.addAttribute("products", productServise.getAllProduct());

        return "product/product";
    }
}
