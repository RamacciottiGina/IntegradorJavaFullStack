
package models;

import enums.PaymentMethods;

public class Buy {
    private int id;
    private String nombre, apellido, direccion, mail, celular; 
    private PaymentMethods metodoPago;

 
    public Buy(int id, String nombre, String apellido, String direccion, String mail, String celular, String metodoPago) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.mail = mail;
        this.celular = celular;
        this.metodoPago = setPayMethod(metodoPago);
    }
   
    
    
    public Buy(String nombre, String apellido, String mail,String celular, String calle, String piso, String metodoPago) {
        this.id = 0;
        this.celular = celular;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.direccion = direccion;
        this.metodoPago =setPayMethod(Integer.parseInt(metodoPago));
    }

    public int getId() {
        return id;
    }

    public String getCelular() {
        return celular;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMail() {
        return mail;
    }

    public PaymentMethods getMetodoPago() {
        return metodoPago;
    }

 

    
 private PaymentMethods setPayMethod(String metodoPago){
        
        switch(metodoPago){
            case "Efectivo":
                return PaymentMethods.A;
            case "Transferencia Bancaria":
                return PaymentMethods.B;
            case "Mercado Pago":
                return PaymentMethods.C;
            default:
                return PaymentMethods.A;
        }
    }
    
    private PaymentMethods setPayMethod(int metodoPago){
        System.out.println("INTCAT"+metodoPago);
        switch(metodoPago){
            case 1:
                return PaymentMethods.A;
            case 2:
                return PaymentMethods.B;
            case 3:
                return PaymentMethods.C;
            default:
                return PaymentMethods.A;
        }
    } 
    
}
