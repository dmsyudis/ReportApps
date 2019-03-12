package com.duakoma.reportapps.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class TugasActivity extends AppCompatActivity {

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
                Toast.makeText(TugasActivity.this, "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList getListData() {
        ArrayList<NewsItem> results = new ArrayList<NewsItem>();
        NewsItem newsData = new NewsItem();
        newsData.setHeadline("Tugas 1");
        newsData.setReporterName("Isi Tugas");
        newsData.setDate("26 Mei, 2013, 13:35");
        results.add(newsData);

        newsData.setHeadline("Tugas 2");
        newsData.setReporterName("Isi Tugas");
        newsData.setDate("26 Mei, 2013, 13:35");
        results.add(newsData);

        newsData.setHeadline("Tugas 3");
        newsData.setReporterName("Isi Tugas");
        newsData.setDate("26 Mei, 2013, 13:35");
        results.add(newsData);
        // Add some more dummy data for testing
        return results;
    }
}
