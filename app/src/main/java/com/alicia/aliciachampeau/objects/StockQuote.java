package com.alicia.aliciachampeau.objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * Created by suiliang on 2/17/18.
 */

public final class StockQuote implements Comparator<StockQuote>, Comparable<StockQuote> {

    public final Date time;
    public final double open, high, low, close;
    public final int volume;

    public StockQuote(String time, JSONObject json) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        try {
            this.time = dateFormat.parse(time);
            open = json.getDouble("1. open");
            high = json.getDouble("2. high");
            low = json.getDouble("3. low");
            close = json.getDouble("4. close");
            volume = json.getInt("5. volume");
        } catch (JSONException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compare(StockQuote quote1, StockQuote quote2) {
        return quote1.time.compareTo(quote2.time);
    }

    @Override
    public int compareTo(StockQuote quote) {
        return this.time.compareTo(quote.time);
    }
}
