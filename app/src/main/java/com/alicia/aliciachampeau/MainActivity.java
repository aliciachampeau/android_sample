package com.alicia.aliciachampeau;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.alicia.aliciachampeau.adapters.StockQuoteAdapter;
import com.alicia.aliciachampeau.objects.TimeSeries;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ListView quotesListView;

    private static class OnAlphaAdvantageResponse implements Response.Listener<String> {

        private final ListView listView;
        private final AlphaAdvantageApi.StockInterval interval;

        OnAlphaAdvantageResponse(ListView listView, AlphaAdvantageApi.StockInterval interval) {
            this.listView = listView;
            this.interval = interval;
        }

        @Override
        public void onResponse(String response) {
            if (!listView.isEnabled()) {
                return;
            }

            try {
                TimeSeries timeSeries = new TimeSeries(new JSONObject(response).getJSONObject(interval.jsonKey));
                listView.setAdapter(new StockQuoteAdapter(listView.getContext(), timeSeries.stockQuotes));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quotesListView = (ListView) findViewById(R.id.quotes);

        AlphaAdvantageApi.intraday(
                AlphaAdvantageApi.Function.TIME_SERIES_INTRADAY,
                "MSFT",
                AlphaAdvantageApi.StockInterval.MIN_1,
                new OnAlphaAdvantageResponse(quotesListView, AlphaAdvantageApi.StockInterval.MIN_1)
        );
    }

}
