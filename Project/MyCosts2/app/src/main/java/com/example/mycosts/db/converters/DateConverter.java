package com.example.mycosts.db.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.mycosts.utils.DateUtils;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public String fromDate(Date date) {
        return DateUtils.convertDateToString(date);
    }

    @TypeConverter
    public Date toDate(String string) {
        return DateUtils.convertStringToDate(string);
    }
}
