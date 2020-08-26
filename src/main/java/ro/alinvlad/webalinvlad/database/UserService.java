package ro.alinvlad.webalinvlad.database;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void save(String email, String password) throws InvalidPassword {
        if (password.length() < 5) {
            throw new InvalidPassword("Your password needs to be at least 5 characters!");
        }

        if(userDAO.findByEmail(email).size() > 0){
            throw new InvalidPassword("This email address is already being used!");
        }

        //Putem folosi functia MD5 pentru a cripta parola
        String passwordMD5 = DigestUtils.md5Hex(password);
        userDAO.save(email, passwordMD5);

        //userDAO.save(email, password);
    }


    public List<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
