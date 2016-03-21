package com.app.quranqu.utils;

/**
 * edited by trian on 03/17/2016
 */

public class AppAPI {

    public static String BASE_URL   = "http://103.43.45.112/service/api/"; //Development
    //public static String BASE_URL                           = "http://api.indihome.co.id/api/"; //Production

    public static String API_AUTH_TOKEN = BASE_URL +"authorize/token";
    public static String API_LIST_SURAH = BASE_URL + "resources/surah";
    public static String API_LIST_SURAH_DETAIL = BASE_URL+"resources/reading/surah/1";
}
