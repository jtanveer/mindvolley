package com.jtanveer.mindvolley;

import java.security.SecureRandom;

/**
 * Created by jtanveer on 17/4/18.
 */

class UniqueKeyGenerator {

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static SecureRandom rand = new SecureRandom();

    static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rand.nextInt(AB.length())));
        }

        return sb.toString();
    }

}
