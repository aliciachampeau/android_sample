package com.alicia.aliciachampeau;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Time;

/**
 * Created by suiliang on 2/17/18.
 */

public final class AlphaAdvantageApi {

    private static final String INTRADAY_URL = "https://www.alphavantage.co/query?function=%s&symbol=%s&interval=1min&apikey=%s";
    private static final String API_KEY = "VXBN3VU00MXN8SKD";


    private static RequestQueue alphavantageQueue;

    private static class IntraDayRequest extends StringRequest {

        public IntraDayRequest(Response.Listener<String> listener, Function function, String symbol, Time interval) {
            super(Request.Method.GET,
                    String.format(INTRADAY_URL, function.headerKey, symbol, API_KEY),
                    listener,
                    null);
        }
    }

    public enum Function {
        TIME_SERIES_INTRADAY("TIME_SERIES_INTRADAY");

        private final String headerKey;

        Function(String headerKey) {
            this.headerKey = headerKey;
        }
    }


    public synchronized static void intraday(Function function, String symbol, Time interval, Response.Listener<String> listener) {
        alphavantageQueue.add(new IntraDayRequest(listener, function, symbol, interval));
    }

    public static void init(Application context) {
        alphavantageQueue = Volley.newRequestQueue(context);
    }
}
