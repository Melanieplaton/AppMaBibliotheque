package com.example.mlanie.mabibliotheque;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mélanie on 03/08/2016.
 */
public class BookAdapter extends ArrayAdapter<Book> {

      public BookAdapter(Context context, List<Book> objects) {
        super(context, 0, objects);
      }


   @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Book book = getItem(i);

        if (view == null){

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_layout, viewGroup, false);
        }
        TextView titre = (TextView) view.findViewById(R.id.textView);
        TextView nomAuteur = (TextView) view.findViewById(R.id.textView2);
        TextView anneePublication = (TextView) view.findViewById(R.id.textView3);

        nomAuteur.setText(book.getAuthorName());
        titre.setText(book.getBookTitle());
        anneePublication.setText(String.valueOf(book.getPublishedYear()));

        return view;
    }
}
