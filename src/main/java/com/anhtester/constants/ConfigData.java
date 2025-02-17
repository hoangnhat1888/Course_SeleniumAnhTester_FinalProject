package com.anhtester.constants;

import com.anhtester.ExcelHelper.PropertiesHelper;

public class ConfigData {
    //Define Public var
    public static final String URL = PropertiesHelper.getValue("URL");
    public static final String EMAIL = PropertiesHelper.getValue("EMAIL");
    public static final String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    //public static int EXPLICIT_WAIT_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("TIMEOUT_EXPLICIT_WA     IT"));
    public static final int EXPLICIT_WAIT_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT_TIMEOUT"));
    public static final double STEP_TIME = Double.parseDouble(PropertiesHelper.getValue("STEP_TIME"));
    public static final int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));
    public static final String SCREENSHOT_PATH = PropertiesHelper.getValue("SCREENSHOT_PATH");
    public static final String RECORD_VIDEO_PATH = PropertiesHelper.getValue("RECORD_VIDEO_PATH");
}
