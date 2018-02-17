package com.alicia.aliciachampeau;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Response;

public class MainActivity extends AppCompatActivity implements Response.Listener<String> {

    private TextView quoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteTextView = (TextView) findViewById(R.id.quote);

        AlphaAdvantageApi.intraday(AlphaAdvantageApi.Function.TIME_SERIES_INTRADAY, "MSFT", null, this);
    }

    @Override
    public void onResponse(String response) {
        quoteTextView.setText(response);
    }
}
