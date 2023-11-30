
package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.BuyServices;



@WebServlet(name = "BuyController", urlPatterns = {"/api/buy"})
public class BuyController extends HttpServlet {
    
    BuyServices buyService = new BuyServices(); 
    
    @Override
    protected void doGet(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {
        
        try {
            String buys = buyService.getBuys();
            enviar(res, buys);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }
    
     @Override
        protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {
    
        try {
            String body = bodyToString(req.getInputStream());  
            String result = buyService.postBuy(body);
            enviar(res, result);
        } 
        catch (SQLException ex) {
            System.out.println(ex.toString());
       }
    }


     private void enviar(HttpServletResponse res, String json) throws IOException{
        
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
         private String bodyToString(InputStream inputStream){
        
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.hasNext() 
                ? scanner.useDelimiter("\\A").next()
                : "";
    }
    }
    
    

