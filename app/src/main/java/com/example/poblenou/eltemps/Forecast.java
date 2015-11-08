package com.example.poblenou.eltemps;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Forecast {
    private Long location_id;
    private Date date;
    private String short_desc;
    private Double min;
    private Double max;
    private Double humidity;
    private Double pressure;
    private Double wind;
    private Double degrees;
    private Long weatherId;

    public Long getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Long weatherId) {
        this.weatherId = weatherId;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getWind() {
        return wind;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }

    public Double getDegrees() {
        return degrees;
    }

    public void setDegrees(Double degrees) {
        this.degrees = degrees;
    }

    public String getFormattedTemp(Double temp, String units) {
        Long rounded = Math.round(temp);
        if (units.equals("metric")) {
            return rounded.toString() + "º";
        } else {
            return rounded.toString() + "º";
        }
    }

    @NonNull
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E d/M");
        return dateFormat.format(date);
    }

    public Long getFormattedPressure() {
        return Math.round(getPressure());
    }

    public Long getFormattedWindSpeed() {
        return Math.round(getWind());
    }

    public String getMaxTemp(String units) {
        return getFormattedTemp(getMax(), units);
    }

    public String getMinTemp(String units) {
        return getFormattedTemp(getMin(), units);
    }


}

