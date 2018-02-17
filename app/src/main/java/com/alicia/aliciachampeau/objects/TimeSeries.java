package com.alicia.aliciachampeau.objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by suiliang on 2/17/18.
 */

public final class TimeSeries {

    public final ArrayList<StockQuote> stockQuotes = new ArrayList<>();

    public TimeSeries(JSONObject json) {
        for (Iterator<String> it = json.keys(); it.hasNext(); ) {
            try {
                String time = it.next();
                stockQuotes.add(new StockQuote(time, json.getJSONObject(time)));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        Collections.sort(stockQuotes);
    }
}
