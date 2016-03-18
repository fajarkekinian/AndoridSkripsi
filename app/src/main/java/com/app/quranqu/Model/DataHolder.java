package com.app.quranqu.Model;

public class DataHolder {
    public String data1;
    public String data2;
    public String data3;
    public String data4;
    public String data5;
    public String data6;
    public String data7;
    public String data8;
    public String data9;
    public String data10;
    public String data11;
    public String data12;

    public DataHolder(String d1){
        this.data1 = d1;
    }
    public DataHolder(String d1, String d2){
        this.data1 = d1;
        this.data2 = d2;
    }
    public DataHolder(String d1, String d2, String d3){
        this.data1 = d1;
        this.data2 = d2;
        this.data3 = d3;
    }

    public DataHolder(String d1, String d2, String d3, String d4){
        this.data1 = d1;
        this.data2 = d2;
        this.data3 = d3;
        this.data4 = d4;
    }

    public DataHolder(String d1, String d2, String d3, String d4, String d5){
        this.data1 = d1;
        this.data2 = d2;
        this.data3 = d3;
        this.data4 = d4;
        this.data5 = d5;
    }

    public DataHolder(String d1, String d2, String d3, String d4, String d5, String d6, String d7,
                      String d8, String d9, String d10){
        this.data1 = d1;
        this.data2 = d2;
        this.data3 = d3;
        this.data4 = d4;
        this.data5 = d5;
        this.data6 = d6;
        this.data7 = d7;
        this.data8 = d8;
        this.data9 = d9;
        this.data10 = d10;
    }

    public String getId(){
        return data1;
    }

    public String getTvName(){
        return data2;
    }

    public String getTvCode(){
        return data3;
    }

    public String getAcara(){
        return data4;
    }

    public String getStartTime(){
        return data5;
    }

    public String getEndTime(){
        return data6;
    }

    public String getThumbImage(){
        return data7;
    }

    public String getPosterImage(){
        return data8;
    }

    public String getTvodStream(){
        return data9;
    }

    public String getWebUrl(){
        return data10;
    }
}
