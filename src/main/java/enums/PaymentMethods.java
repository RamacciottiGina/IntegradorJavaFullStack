
package enums;

import com.google.gson.annotations.SerializedName;

public enum PaymentMethods {
    
    
    @SerializedName("1")
    A("Efectivo", 1),
    @SerializedName("2")
    B("Transferencia Bancaria", 2),
    @SerializedName("3")
    C("Mercado Pago", 3);
    private String nombre; 
    private int codigo;
    
    private PaymentMethods (String nombre, int codigo) {
        this.nombre = nombre; 
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }
    
}
