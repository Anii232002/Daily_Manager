package com.example.dailymanager.Habits;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Calc {

    public static final Calc INSTANCE;


    public final String calculateTimeBetweenDates(String startDate) throws ParseException {

        String endDate = this.timeStampToString(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/ddHH:mm");//dd/MM/yyyy HH:mm
        Date date1 = sdf.parse(startDate);
        Date date2 = sdf.parse(endDate);
        boolean isNegative = false;



        long difference = date2.getTime() - date1.getTime();
        if (difference < 0L) {
            difference = -difference;
            isNegative = true;
        }

        long minutes = difference / (long)60 / (long)1000;
        long hours = difference / (long)60 / (long)1000 / (long)60;
        long days = difference / (long)60 / (long)1000 / (long)60 / (long)24;
        long months = difference / (long)60 / (long)1000 / (long)60 / (long)24 / (long)30;
        long years = difference / (long)60 / (long)1000 / (long)60 / (long)24 / (long)365;
        if (isNegative) {
            return minutes < (long)240 ? "Starts in " + minutes + " minutes" : (hours < (long)48 ? "Starts in " + hours + " hours" : (days < (long)61 ? "Starts in " + days + " days" : (months < (long)24 ? "Starts in " + months + " months" : "Starts in " + years + " years")));
        } else {
            return minutes < (long)240 ? minutes + " minutes ago" : (hours < (long)48 ? hours + " hours ago" : (days < (long)61 ? days + " days ago" : (months < (long)24 ? months + " months ago" : years + " years ago")));
        }
    }

    private final String timeStampToString(long timeStamp) {
        Timestamp stamp = new Timestamp(timeStamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/ddHH:mm"); //dd/MM/yyyy HH:mm
        String date = sdf.format((Date)(new java.sql.Date(stamp.getTime())));
        return date.toString();
    }


    public final String cleanDate(int _day, int _month, int _year) {
        String day = String.valueOf(_day);
        String month = String.valueOf(_month);
        if (_day < 10) {
            day = "" + '0' + _day;
        }

        if (_month < 9) {
            month = "" + '0' + (_month + 1);
        } else if (_month >= 9 && _month <= 11) {
            month = String.valueOf(_month + 1);
        }

        return day + '/' + month + '/' + _year;
    }


    public final String cleanTime(int _hour, int _minute) {
        String hour = String.valueOf(_hour);
        String minute = String.valueOf(_minute);
        if (_hour < 10) {
            hour = "" + '0' + _hour;
        }

        if (_minute < 10) {
            minute = "" + '0' + _minute;
        }

        return hour + ':' + minute;
    }

    public Calc() {
    }

    static {
        Calc var0 = new Calc();
        INSTANCE = var0;
    }
}
