package com.genersoft.iot.vmp.utils;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import java.util.Locale;

/**    
 * 全局时间工具类
 * @author lin
 */
public class DateUtil {

    /**
     * 兼容不规范的iso8601时间格式
     */
	private static final String ISO8601_COMPATIBLE_PATTERN = "yyyy-M-d'T'H:m:s";

    /**
     * 用以输出标准的iso8601时间格式
     */
	private static final String ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * wvp内部统一时间格式
     */
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";


    public static final DateTimeFormatter formatterCompatibleISO8601 = DateTimeFormatter.ofPattern(ISO8601_COMPATIBLE_PATTERN, Locale.getDefault()).withZone(ZoneId.systemDefault());
    public static final DateTimeFormatter formatterISO8601 = DateTimeFormatter.ofPattern(ISO8601_PATTERN, Locale.getDefault()).withZone(ZoneId.systemDefault());
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN, Locale.getDefault()).withZone(ZoneId.systemDefault());

	public static String yyyy_MM_dd_HH_mm_ssToISO8601(String formatTime) {
        return formatterISO8601.format(formatter.parse(formatTime));
    }
	
	public static String ISO8601Toyyyy_MM_dd_HH_mm_ss(String formatTime) {
        return formatter.format(formatterCompatibleISO8601.parse(formatTime));

    }

    /**
     * yyyy_MM_dd_HH_mm_ss 转时间戳
     * @param formatTime
     * @return
     */
	public static long yyyy_MM_dd_HH_mm_ssToTimestamp(String formatTime) {
        TemporalAccessor temporalAccessor = formatter.parse(formatTime);
        Instant instant = Instant.from(temporalAccessor);
        return instant.getEpochSecond();
	}

    /**
     * 获取当前时间
     * @return
     */
    public static String getNow() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        return formatter.format(nowDateTime);
    }

    /**
     * 格式校验
     * @param timeStr 时间字符串
     * @param dateTimeFormatter 待校验的格式
     * @return
     */
    public static boolean verification(String timeStr, DateTimeFormatter dateTimeFormatter) {
        try {
            LocalDate.parse(timeStr, dateTimeFormatter);
            return true;
        }catch (DateTimeParseException exception) {
            return false;
        }
    }
}
