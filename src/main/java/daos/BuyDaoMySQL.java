package daos;

import config.conection;
import enums.PaymentMethods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import models.Buy;

public class BuyDaoMySQL {

    conection conexion = new conection();
    Connection connect = conexion.getConnection();

    PreparedStatement ps;
    ResultSet rs;

    public LinkedList getBuys() throws SQLException {
        LinkedList buys = new LinkedList();
        ps = connect.prepareStatement("SELECT * FROM buys;");
        rs = ps.executeQuery();

        while (rs.next()) {
            
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String mail = rs.getString("mail");
            String celular = rs.getString("celular");
            String direccion = rs.getString("direccion");
            String metodoPago = rs.getString("payMethod");
            Buy buy = new Buy (id, nombre, apellido, mail, celular, direccion, metodoPago);
            buys.add(buy);
        }
        return buys;
    }
    public Boolean postBuy(Buy buy) throws SQLException{
        try{
            ps = connect.prepareStatement("INSERT INTO buys(nombre, apellido, mail, celular, direccion, payMethod) VALUES (?,?,?,?,?,?)");
            ps.setString(1, buy.getNombre());
            ps.setString(2, buy.getApellido());
            ps.setString(3, buy.getMail());
            ps.setString(4, buy.getCelular());
            ps.setString(5, buy.getDireccion());
            ps.setInt(6, buy.getMetodoPago().getCodigo());
            ps.execute();
            return false;
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return true;
        }
    }
}
