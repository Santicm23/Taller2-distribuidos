package javahaters;

public class Operations {

    public static Float operation1(Float valor, Float porcentaje) {
        Float descuento = valor * (porcentaje / 100);
        return valor - descuento;
    }

    public static Float operation2(Float valorInicial, Float valorFinal) {
        Float diferencia = valorInicial - valorFinal;
        return (diferencia / valorInicial) * 100;
    }
}
