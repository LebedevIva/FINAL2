package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByTitle(String title);

//    Поиск по части наименования товара вне зависимости от регистра *1
    List<Product> findByTitleContainingIgnoreCase(String name);

//    findByTitle - нахождение по нашему тайтлу
//    Containing - всё, что передано в переменной String name, может содержаться
//    IgnoreCase - игнорируется регистр

//    Фильтрация по диапазону *2
    List<Product> findByPriceGreaterThanEqualAndPriceLessThan(float Ot, float Do);

//    Сортировка по возрастанию *3
    @Query(value = "select * from product order by price", nativeQuery = true)
    List<Product> OrderByPrice();

//    Сортировка по убыванию *3
    @Query(value = "select * from product order by price desc", nativeQuery = true)
    List<Product> OrderByPriceDesc();

//    Фильтрация по категории *4
    @Query(value = "select * from product where category_id=?1", nativeQuery = true)
    List<Product> findByCategory(int category);

//    Поиск по части наименования товара и фильтрация по диапазону *12
    @Query(value = "select * from product where ((price >= ?2 and price <= ?3) and (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1'))", nativeQuery = true)
    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThan(String title, float Ot, float Do);

//    Фильтрация по диапазону и сортировка по возрастанию *23
    @Query(value = "select * from product where (price >= ?1 and price <= ?2) order by price", nativeQuery = true)
    List<Product> PriceGreaterThanEqualAndPriceLessThanOrderByPrice(float Ot, float Do);

    //    Фильтрация по диапазону и сортировка по убыванию *23
    @Query(value = "select * from product where (price >= ?1 and price <= ?2) order by price desc", nativeQuery = true)
    List<Product> PriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(float Ot, float Do);

//    Фильтрация по диапазону и категории *24
    @Query(value = "select * from product where category_id=?3 and (price >= ?1 and price <= ?2)", nativeQuery = true)
    List<Product> CategoryAndPriceGreaterThanEqualAndPriceLessThan(float Ot, float Do, int category);

    //Фильтрация по возрастанию и категории *34
    @Query(value = "select * from product where category_id=?1 order by price", nativeQuery = true)
    List<Product> CategoryOrderByPrice(int category);

    //Фильтрация по возрастанию и категории *34
    @Query(value = "select * from product where category_id=?1 order by price desc", nativeQuery = true)
    List<Product> CategoryOrderByPriceDesc(int category);

//    Поиск по части наименования товара и фильтрация по диапазону, сортировка по возрастанию *123
    @Query(value = "select * from product where ((price >= ?2 and price <= ?3) and (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) order by price", nativeQuery = true)
    List<Product> findByTitleOrderByPrice(String title, float Ot, float Do);

    //    Поиск по части наименования товара и фильтрация по диапазону, сортировка по убыванию *123
    @Query(value = "select * from product where ((price >= ?2 and price <= ?3) and (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) order by price desc", nativeQuery = true)
    List<Product> findByTitleOrderByPriceDesc(String title, float Ot, float Do);

//    Фильтрация по диапазону, возрастанию и категории *234
    @Query(value = "select * from product where category_id=?3 and (price >= ?1 and price <= ?2) order by price", nativeQuery = true)
    List<Product> CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPrice(float Ot, float Do, int category);

    //    Фильтрация по диапазону, убыванию и категории *234
    @Query(value = "select * from product where category_id=?3 and (price >= ?1 and price <= ?2) order by price desc", nativeQuery = true)
    List<Product> CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(float Ot, float Do, int category);

    //    Поиск по части наименования товара и фильтрация по диапазону, сортировка по возрастанию, фильтрация по категории *1234
    @Query(value = "select * from product where category_id=?4 and ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by price", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPrice(String title, float Ot, float Do, int category);

    //    Поиск по части наименования товара и фильтрация по диапазону, сортировка по убыванию, фильтрация по категории *1234
    @Query(value = "select * from product where category_id=?4 and ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%')) or (lower(title) LIKE '%?1') and (price >= ?2 and price <= ?3) order by price desc", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceDesc(String title, float Ot, float Do, int category);

    //Поиск по наименованию с сортировкой по возрастанию *13
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) order by price", nativeQuery = true)
    List<Product> findByTitleAndOrderByPrice(String title);

    //Поиск по наименованию с сортировкой по убыванию *13
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) order by price desc", nativeQuery = true)
    List<Product> findByTitleAndOrderByPriceDesc(String title);

    //Поиск по наименованию и категории *14
    @Query(value = "select * from product where category_id=?2 and ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%')) or (lower(title) LIKE '%?1')", nativeQuery = true)
    List<Product> findByTitleAndCategory(String title, int category);

    @Query(value = "SELECT MAX(id) FROM Product")
    Long getMaxProductId();
}
