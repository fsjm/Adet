package com.fabricesalmon.adet;

import com.fabricesalmon.adet.R;
import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.graphics.drawable.ColorDrawable;

public class ItemListViewAdapter extends ArrayAdapter<ItemListView> {

    public ItemListViewAdapter(Context context, List<ItemListView> l_ItemListView) {
        super(context, 0, l_ItemListView);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewlayout,parent, false);
        }

        ItemListViewHolder viewHolder = (ItemListViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ItemListViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        ItemListView l_ItemListView = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.pseudo.setText(l_ItemListView.getPseudo());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(l_ItemListView.getColor()));

        return convertView;
    }

    private class ItemListViewHolder{
        public TextView pseudo;
        public ImageView avatar;
    }
}

