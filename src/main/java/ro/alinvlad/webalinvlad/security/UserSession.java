package ro.alinvlad.webalinvlad.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;

@Component
@SessionScope
public class UserSession {
    private int userId;
    HashMap<Integer, Integer> shoppingCart = new HashMap<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public HashMap<Integer, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(HashMap<Integer, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addNewProduct(Integer id) {
        if(shoppingCart.get(id) != null) {
            int currentQuantity = shoppingCart.get(id);
            int newQuantity = currentQuantity + 1;
            shoppingCart.put(id, newQuantity);
        } else {
            shoppingCart.put(id, 1);
        }
    }

}
