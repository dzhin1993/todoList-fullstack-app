package com.zhinkoilya1993.backend.config;

import org.springframework.format.Formatter;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    @Override
    public LocalDateTime parse(String str, Locale locale) {
        return ObjectUtils.isEmpty(str) ? null : LocalDateTime.parse(str);
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
