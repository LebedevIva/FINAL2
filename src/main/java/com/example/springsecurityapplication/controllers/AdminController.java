package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.repositories.PersonRepository;
import com.example.springsecurityapplication.repositories.ProductRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.servises.OrderServise;
import com.example.springsecurityapplication.servises.PersonServise;
import com.example.springsecurityapplication.servises.ProductServise;
import com.example.springsecurityapplication.util.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
//    Указали, что к данному классу имеет доступ только пользователь с ролью ADMIN
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

//    Указали, что к данному методу имеет доступ только пользователь с ролью ADMIN
//    @PreAuthorize("hasRole('ROLE_ADMIN')")


    @Value("${upload.path}")
    private String uploadPuth;

    private final OrderServise orderServise;

    private final PersonServise personServise;
    private final OrderRepository orderRepository;

    private final PersonRepository personRepository;
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    private final ProductServise productServise;

    private final CategoryRepository categoryRepository;
    @Autowired
    public AdminController(OrderServise orderServise, PersonServise personServise, OrderRepository orderRepository, ProductRepository productRepository, PersonRepository personRepository, ProductValidator productValidator, ProductServise productServise, CategoryRepository categoryRepository) {
        this.orderServise = orderServise;
        this.personServise = personServise;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.personRepository = personRepository;
        this.productValidator = productValidator;
        this.productServise = productServise;
        this.categoryRepository = categoryRepository;
    }

//    Метод по отображению главной страницы администратора с выводом товаров
    @GetMapping()
    public String admin(Model model){
        //        Получем объект аутентификации, и с помощью SecurityContextHolder.getContext() обращаемся к контексту и вызываем на нем метод аутентификации
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //        Преобразовываем объект аутентификации в специальный объект класс по работе с пользователями
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String role = personDetails.getPerson().getRole();

        if(role.equals("ROLE_USER")){
            return "redirect:/index";
        }

        model.addAttribute("products", productServise.getAllProduct());
        return "admin/admin";
    }

//    Метод по отображению формы добавления
    @GetMapping("/product/add") //Обработали тот путь на который у нас ведет ссылка добавить товар
    public String addProduct(Model model){
        model.addAttribute("product", new Product()); //Положили в модель пустой объект товара, чтобы потом его привязать к форме
        model.addAttribute("category", categoryRepository.findAll()); //Положили в модель пустой объект товара, чтобы потом его привязать к форме
        return "product/addProduct";
    }

//    Метод добавляет объект с формы в таблицу product
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, Model model, @RequestParam("file_one") MultipartFile file_two, @RequestParam("file_two") MultipartFile file_three, @RequestParam("file_three") MultipartFile file_four, @RequestParam("file_four") MultipartFile file_five, @RequestParam("file_five") MultipartFile file_one) throws IOException {

        productValidator.validate(product, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("category", categoryRepository.findAll());
            return "product/addProduct";
        }

        if(!file_one.isEmpty()){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(!file_two.isEmpty()){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(!file_three.isEmpty()){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(!file_four.isEmpty()){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(!file_five.isEmpty()){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }
        productServise.saveProduct(product);
        return "redirect:/admin";
    }

//    Метод удаляет product
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productServise.deleteProduct(id);
        return "redirect:/admin";
    }

//    Метод по отображению формы редактирования product
    @GetMapping("/product/edit/{id}") //Обрабатываем нажатие на ссылку редактировать товар
    public String editProduct(@PathVariable("id") int id, Model model){ //Принимаем "id" и помещаем его в специальную переменную id
//    Кладем в модель атрибут нашей страницы editProduct.html и продукт, который получили по id
        model.addAttribute("editProduct", productServise.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }


//    Метод редактирования product
    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id){
        productServise.updateProduct(id, product);
        return "redirect:/admin";
    }

    @GetMapping("/listOrders")
    public String ordersUser(Model model){
        model.addAttribute("orders",orderServise.getAllOrder());
        return "admin/listOrders";
    }

    //    Метод по отображению формы редактирования order
    @GetMapping("/listOrders/edit/{id}") //Обрабатываем нажатие на ссылку редактировать товар
    public String editOrder(@PathVariable("id") int id, Model model){ //Принимаем "id" и помещаем его в специальную переменную id
//    Кладем в модель атрибут нашей страницы editOrder.html и продукт, который получили по id
        model.addAttribute("editOrder", orderServise.getOrderId(id));
        model.addAttribute("status", Status.values());
        return "admin/editOrder";
    }

    //    Метод редактирования order
    @PostMapping("/listOrders/edit/{id}")
    public String editOrder(@ModelAttribute("editOrder") Order order, @PathVariable("id") int id){
        Order upOrder = orderServise.getOrderId(id);
        upOrder.setStatus(order.getStatus());

        orderServise.updateOrder(id, upOrder);
        return "redirect:/admin/listOrders";
    }

    @PostMapping("/product/admsearch")
    public String productSearch(@RequestParam("search") String search, @RequestParam("ot") String Ot, @RequestParam("do") String Do, @RequestParam(value = "price", required = false, defaultValue = "") String price, @RequestParam(value = "category", required = false, defaultValue = "") String category, Model model){

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

        model.addAttribute("value_search", search);
        model.addAttribute("value_ot", Ot);
        model.addAttribute("value_do", Do);
        model.addAttribute("products", productServise.getAllProduct());

        return "admin/admin";
    }


    @PostMapping("/listOrders/search")
    public String orderSearch(@RequestParam("search") String search, Model model){
        if(!search.isEmpty()){
            model.addAttribute("search_order", orderRepository.findByNumber(search));
        }
        model.addAttribute("value_search", search);
        model.addAttribute("orders", orderServise.getAllOrder());
        return "admin/listOrders";
    }

    @GetMapping("/listPersons")
    public String getPersons(Model model){
     //   model.addAttribute("person",personServise.getAllPerson());
        List<Person> persons = personServise.getAllPerson();
        model.addAttribute("persons", persons);
        model.addAttribute("status", Status.values());
        return "admin/listPersons";
    }

//    @PostMapping("/update")
//    public String updatePerson(@ModelAttribute("person") Person person) {
//        personServise.updatePerson(person);
//        return "redirect:/persons";
//    }


   // @PostMapping("/update")
   // public String updateUser(@ModelAttribute("person") Person person, Model model) {
   //     System.out.println("Person to update: " + person);
   //     Optional<Person> optionalPerson = personRepository.findById(person.getId());
   //     System.out.println("Found person: " + optionalPerson);
   //     if (optionalPerson.isPresent()) {
   //         Person existingPerson = optionalPerson.get();
   //         existingPerson.setRole(person.getRole());
    //        personRepository.save(existingPerson);
    //    }
    //    return "redirect:/admin/listPersons";
  //  }

    @PostMapping("/update")
    public String updateUser(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        Optional<Person> optionalPerson = personRepository.findById((int) id);
        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();
            existingPerson.setRole(request.getParameter("role"));
            personRepository.save(existingPerson);
        }
        return "redirect:/admin/listPersons";
    }



}
