package ro.alinvlad.webalinvlad.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.alinvlad.webalinvlad.database.ProductDAO;

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public String saveProduct(String model, String about_product, double price) {
        //validam datele de intrare
        if (about_product.length() < 5) {
            return "Introduceti o lungime mai mare de 5 caractere";
        }


        productDAO.saveProduct(model, about_product, price);
        return "ok";
    }
}
