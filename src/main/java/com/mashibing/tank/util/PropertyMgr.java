package com.mashibing.tank.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

    private PropertyMgr() {
    }

    static final Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key) {
        if (props == null) {
            return "";
        }
        return (String) props.get(key);
    }

    public static int getInt(String key) {
        if (props == null) {
            return 0;
        }
        return Integer.parseInt((String) props.get(key));
    }
}
