package com.duakoma.reportapps.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.duakoma.reportapps.Adapter.CustomListAdapter;
import com.duakoma.reportapps.Adapter.NewsItem;
import com.duakoma.reportapps.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 1/20/2019.
 */

public class HistoryActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.custom_list);
        lv1.setAdapter(new CustomListAdapter(this, image_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                NewsItem newsData = (NewsItem) o;
                Toast.makeText(HistoryActivity.this, "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList getListData() {
        ArrayList<NewsItem> results = new ArrayList<NewsItem>();
        NewsItem newsData = new NewsItem();
        newsData.setHeadline("Laporan 1");
        newsData.setReporterName("Isi laporan");
        newsData.setDate("26 Mei, 2013, 13:35");
        results.add(newsData);

        newsData.setHeadline("Laporan 2");
        newsData.setReporterName("Isi laporan 2");
        newsData.setDate("26 Mei, 2013");
        results.add(newsData);

        newsData.setHeadline("Laporan 1");
        newsData.setReporterName("Isi laporan");
        newsData.setDate("26 Mei, 2013, 13:35");
        results.add(newsData);
        // Add some more dummy data for testing
        return results;
    }
}
