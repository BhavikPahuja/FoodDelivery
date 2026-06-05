package com.jpa.fooddelivery.Utils;

public class RoleConstants {

    private static String ROLE_ADMIN = "ADMIN";
    private static String ROLE_CUSTOMER = "CUSTOMER";
    private static String ROLE_DELIVERY_BOY = "DELIVERY_BOY";

    public static String getROLE_ADMIN() {
        return "ROLE_" + ROLE_ADMIN;
    }
    public static String getROLE_CUSTOMER() {
        return "ROLE_" + ROLE_CUSTOMER;
    }
    public static String getROLE_DELIVERY_BOY() {
        return "ROLE_" + ROLE_DELIVERY_BOY;
    }
}
