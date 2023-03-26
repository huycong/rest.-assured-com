package com.congnh.rest.common.libraries.helper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeHelper {

    public static String dtFormated() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd_hhmmss");
        return df.format(new Date()).toString();
    }

    public static String instantToDateTimeString(Instant timeInstant, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        ZonedDateTime dateTime = timeInstant.atZone(ZoneId.of("Asia/Ho_Chi_Minh"));

        return formatter.format(dateTime);
    }

    public static Instant convertDateTimeStringToInstant(String strDate, String format) {
        try {
            SimpleDateFormat sdfrmt = new SimpleDateFormat(format);
            sdfrmt.setLenient(false);
            Date date = sdfrmt.parse(strDate);
            return Instant.ofEpochMilli(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static String timeFormat(Long time, String partern) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat(partern);
        Date date = new Date(time);
        return dateFormat.format(date);
    }

    public static Long timeNow() {
        return System.currentTimeMillis();
    }

    public static Long timeTomorrow() {
        return System.currentTimeMillis() + 86400000L;
    }


    public static String timeNowTxt() throws IOException {
        return timeFormat(System.currentTimeMillis(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public static String timeTomorrowTxt() throws IOException {
        return timeFormat(System.currentTimeMillis() + 86400000L, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }


    public static Long timeMoreOneWeek() {
        return System.currentTimeMillis() + 86400000L * 7;
    }

    public static String timeMoreOneWeekTxt() throws IOException {
        return timeFormat(System.currentTimeMillis() + 86400000L* 7, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public static Long timeLessOneWeek() {
        return System.currentTimeMillis() - 86400000L * 7;
    }

    public static String timeLessOneWeekTxt() throws IOException {
        return timeFormat(System.currentTimeMillis() - 86400000L* 7, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public static Long timeMoreOneMonth() {
        return System.currentTimeMillis() + 86400000L * 30;
    }

    public static String timeMoreOneMonthTxt() throws IOException {
        return timeFormat(System.currentTimeMillis() + 86400000L * 30, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public static Long timeLessOneMonth() {
        return System.currentTimeMillis() - 86400000L * 30;
    }

    public static String timeLessOneMonthTxt() throws IOException {
        return timeFormat(System.currentTimeMillis() - 86400000L * 30, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }
}

