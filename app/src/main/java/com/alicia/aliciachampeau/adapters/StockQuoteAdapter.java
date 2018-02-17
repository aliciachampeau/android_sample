package com.alicia.aliciachampeau.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alicia.aliciachampeau.R;
import com.alicia.aliciachampeau.objects.StockQuote;

import java.util.List;

public class StockQuoteAdapter extends ArrayAdapter<StockQuote> {

    public StockQuoteAdapter(Context context, List<StockQuote> stockQuotes) {
        super(context, R.layout.stock_quote, stockQuotes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        StockQuote quote = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stock_quote, parent, false);
        }
        assert quote != null;
        ((TextView) convertView).setText(String.format("Time: %s\nHigh: %s\nLow: %s", quote.time.toString(), quote.high, quote.low));
        return convertView;
    }
}
