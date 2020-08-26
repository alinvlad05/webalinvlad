package ro.alinvlad.webalinvlad.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ro.alinvlad.webalinvlad.database.*;
import ro.alinvlad.webalinvlad.security.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    UserSession userSession;

    @Autowired
    OrderDao orderDao;

    //CE TREBUIE SA FACEM AICI???
    // - daca parolele sunt identice
    @GetMapping("/register-form")
    public ModelAndView registerAction(@RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       @RequestParam("password-again") String password2) {
        ModelAndView modelAndView = new ModelAndView("register");
        if (!password.equals(password2)) {
            modelAndView.addObject("message", "Parole nu sunt identice!");
            return modelAndView;
        } else {
            try {
                userService.save(email, password);
            } catch (InvalidPassword invalidPassword) {
                String messageException = invalidPassword.getMessage();
                modelAndView.addObject("message", messageException);
                return modelAndView;
            }
        }

        //redirectionam user-ul catre pagina index.html daca este totul ok
        return new ModelAndView("redirect:/index.html");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        List<User> userList = userService.findByEmail(email);
        if (userList.size() == 0) {
            modelAndView.addObject("message", "Wrong email or password.");
        }
        if (userList.size() > 1) {
            modelAndView.addObject("message", "Wrong email or password.");
        }
        if (userList.size() == 1) {
            User userFromDatabase = userList.get(0);
            if (!userFromDatabase.getPassword().equals(DigestUtils.md5Hex(password))) {
                modelAndView.addObject("message", "Wrong email or password.");
            } else {
                userSession.setUserId(userFromDatabase.getId());
                modelAndView = new ModelAndView("redirect:/dashboard");
            }
        }
        return modelAndView;
    }

    @GetMapping("dashboard")
    public ModelAndView dashboard() {

        List<Product> products = productDAO.findAll();
        for(Product p: products) {
            p.setUrl("product?id=" + p.getId());
        }

        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("products", products);

        return modelAndView;
    }

    @GetMapping("/my-orders-history")
    public ModelAndView history() {
        List<Order> orders = orderDao.findOrderForUser(userSession.getUserId());
        ModelAndView modelAndView = new ModelAndView("orders");
        modelAndView.addObject("orders", orders);

        return modelAndView;
    }

    @GetMapping(value = "/download-order-history", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] download(HttpServletResponse httpServletResponse) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("Id comanda");
        firstRow.createCell(1).setCellValue("Pret total");

        //mergem in baza de date si citim comenzile
        //scriem fiecare comanda in fisierul Excel

        FileOutputStream fileOutputStream = new FileOutputStream("raport");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"report.xls\"");

        //intoarcem un fisier xcel
        return new FileInputStream("raport").readAllBytes();
    }

}
