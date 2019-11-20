package com.app.advancecurd.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.app.advancecurd.R;
import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Item> items;

    public UserAdapter(Context context, ArrayList<Item> objects) {
        super(context, R.layout.custom_list, objects);
        this.context = context;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = layoutInflater.inflate(R.layout.custom_list, parent, false);

        Item item = (Item) getItem(position);

        String dataItem =
                " FullName  " + item.getFullname() + "\n" +
                        " Username  " + item.getUsername() + "\n" +
                        " Gender  " + item.getGender() + "\n" +
                        " Branch  " + item.getBranch() + "\n" +
                        " City  " + item.getCity() + "\n" +
                        " Status  " + item.getStatus();

        TextView showView = rowView.findViewById(R.id.custom_text);
        showView.setText(dataItem);
        return rowView;
    }
}
