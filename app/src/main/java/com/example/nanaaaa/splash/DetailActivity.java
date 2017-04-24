package com.example.nanaaaa.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String judul = getIntent().getStringExtra("JUDUL");
        String deskripsi = getIntent().getStringExtra("bDESKRIPSI");
        String foto = getIntent().getStringExtra("FOTO");
        String content = getIntent().getStringExtra("CONTENT");
        String author = getIntent().getStringExtra("AUTHOR");

        TextView tvDeskripsi = (TextView) findViewById(R.id.place_detail);
        TextView tvAuthor = (TextView) findViewById(R.id.place_author);
        ImageView ivFoto = (ImageView) findViewById(R.id.imageFoto);

        System.out.println(judul);
        System.out.println(deskripsi);
        System.out.println(foto);
        System.out.println(content);
        System.out.println(author);

        getSupportActionBar().setTitle(judul);
        tvAuthor.setText(author);
        tvDeskripsi.setText(Html.fromHtml(content));
        Glide.with(this).load(foto).into(ivFoto);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
