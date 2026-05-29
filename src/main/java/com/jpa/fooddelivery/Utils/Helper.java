package com.jpa.fooddelivery.Utils;

import java.util.UUID;

public class Helper {
    public static Long generateRandomId() {
        return UUID.randomUUID().getMostSignificantBits();
    }
}
