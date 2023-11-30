
package services;

import com.google.gson.Gson;
import daos.BuyDaoMySQL;
import java.sql.SQLException;
import java.util.List;
import models.Buy;
import models.Result;

public class BuyServices {
    private final Gson GSON = new Gson();
    private final BuyDaoMySQL DAO = new BuyDaoMySQL();
    
    List buys; 
    public String getBuys() throws SQLException {
        buys = DAO.getBuys();
        String result = GSON.toJson(buys);
              System.out.println(result);
        return result;
    }
     public String postBuy(String buy) throws SQLException{
         System.out.println(buy);
        Buy newBuy = GSON.fromJson(buy, Buy.class);
        boolean error = DAO.postBuy(newBuy);
        Result result = new Result(error);
        return GSON.toJson(result);
    }
}
