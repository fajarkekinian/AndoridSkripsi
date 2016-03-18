package com.app.quranqu.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Edited by trian on 03/16/2016.
 */

public class AppUtility {
    static ProgressDialog mProgress;
    /**
     *  create progress bar
     * **/
    public static void progressDialog(Context c,String s,Boolean b){
        mProgress = new ProgressDialog(c);
        mProgress.setMessage(s);
        mProgress.setCancelable(b);
    }

    /**
     *  show progress bar
     * **/
    public static void progressDialogShow(){
        mProgress.show();
    }

    /**
     *  dismiss progress bar
     * **/
    public static void progressDialogDismiss(){
        mProgress.dismiss();
    }

    public static void callPopUp(final Context c, String p) {
        final String mPhoneNum = p;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Panggilan");
        builder.setMessage("Anda ingin melakukan panggilan ke call center  MY INDIHOME (free call) ?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPhoneNum));
                String permission = "android.permission.CALL_PHONE";
                int res = c.checkCallingOrSelfPermission(permission);
                if(res == PackageManager.PERMISSION_GRANTED)
                    c.startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void callPopUp(final Context c, String p,String t, String m) {
        final String mPhoneNum = p;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(t);
        builder.setMessage(m);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPhoneNum));
                String permission = "android.permission.CALL_PHONE";
                int res = c.checkCallingOrSelfPermission(permission);
                if (res == PackageManager.PERMISSION_GRANTED)
                    c.startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void sendSMS(Context c, String np){
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String(np));
        smsIntent.putExtra("sms_body", "");

        try {
            c.startActivity(smsIntent);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String capitalizeFirstLetter(String s){
        String result="";
        result = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        return result;
    }
    public static void showToast(Activity activity, String message){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }
    public static void printLog(String message){
        Log.d(AppConstant.APP_TAG, message);
    }

    public int getIntDay(String d){
        Calendar c = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try{
            c.setTime(sdf.parse(d));
        }catch (Exception e){
            e.printStackTrace();
        }
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }
    public static String convertDayToIndonesian(int d){
        String day="";
        switch (d){

            case 7 : day = "MINGGU";break;
            case 1 : day = "SENIN";break;
            case 2 : day = "SELASA";break;
            case 3 : day = "RABU";break;
            case 4 : day = "KAMIS";break;
            case 5 : day = "JUMAT";break;
            case 6 : day = "SABTU";break;

        }
        return day;
    }
    public static String convertMonthToIndonesian(int m){
        String month="";
        switch (m){
            case 0  : month = "JANUARI";break;
            case 1  : month = "FEBRUARI";break;
            case 2  : month = "MARET";break;
            case 3  : month = "APRIL";break;
            case 4  : month = "MEI";break;
            case 5  : month = "JUNI";break;
            case 6  : month = "JULI";break;
            case 7  : month = "AGUSTUS";break;
            case 8  : month = "SEPTEMBER";break;
            case 9  : month = "OKTOBER";break;
            case 10 : month = "NOVEMBER";break;
            case 11 : month = "DESEMBER";break;
        }
        return month;
    }
    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    public static String encodedParam(String value){
        String v = null;
        try {
            v = URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return v;
    }
    public static String HttpUrlConnectionGet(String u, Integer to){
        String response="";
        HttpURLConnection conn=null;
        try{
            URL url = new URL(u);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(to);
            conn.setReadTimeout(to+5000);
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            response = sb.toString();
        } catch (java.net.SocketTimeoutException e) {
            response = AppConstant.APP_REQUEST_TIME_OUT;
            return  response ;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null){
                conn.disconnect();
            }
        }

        return response;
    }

    /**
     * post method to retrieve data from back end server
     * **/
    public static String HttpUrlConnectionPost(String u, String q, String c, Integer to){
        String response="";
        HttpURLConnection conn=null;
//        String basicAuth = "Basic " + new String(Base64.encode(AppConstant.AUTH.getBytes(), Base64.NO_WRAP));
        try{
            URL url = new URL(u);
            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestProperty("Authorization", basicAuth);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(to);
            conn.setReadTimeout(to+5000);
            DataOutputStream wr = new DataOutputStream (conn.getOutputStream());
            wr.write(q.getBytes(c));
            wr.flush ();
            wr.close ();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            response = sb.toString();
        } catch (java.net.SocketTimeoutException e) {
            response = AppConstant.APP_REQUEST_TIME_OUT;
            return  response ;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null){
                conn.disconnect();
            }
        }

        return response;
    }
    public static String getApiPost(String u, String param, String var, Integer to){
        String response="";
        try{
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(to);
            conn.setReadTimeout(to);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(var+"=" + encodedParam(param));
            //wr.writeBytes(encodedParam(req));
            wr.flush ();
            wr.close ();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
//            if (conn.getResponseCode()==HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
//                response = "Time Out";
//            }
//            if (conn.getResponseCode()!=HttpURLConnection.HTTP_OK) {
//                response = "Time Out";
//            }
            response = sb.toString();
        } catch (java.net.SocketTimeoutException e) {
            response = "Time Out";
            return  response ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
    public static String getApiPost(String u, String param, String var){
        String response="";
        try{
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(var+"=" + encodedParam(param));
            //wr.writeBytes(encodedParam(req));
            wr.flush ();
            wr.close ();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            response = sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    /**
     * convert (encode) image to string
     * **/
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    /**
     * convert (decode) string to image
     * **/
    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
