package pl.matiej.jdd;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        BigDecimal xxx = new BigDecimal(1232131999);
        System.out.println("big-> " + xxx);
        Long xyz = xxx.longValue();
        System.out.println("long-> "+ xyz);
    }
}
