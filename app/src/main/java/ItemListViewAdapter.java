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

        ListItemViewHolder l_ListItemViewHolder = (ListItemViewHolder) convertView.getTag();
        if(l_ListItemViewHolder == null){
            l_ListItemViewHolder = new ListItemViewHolder();
            l_ListItemViewHolder.ms_Pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            l_ListItemViewHolder.m_Avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(l_ListItemViewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        ItemListView l_ItemListView = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        l_ListItemViewHolder.ms_Pseudo.setText(l_ItemListView.getPseudo());
        l_ListItemViewHolder.m_Avatar.setImageDrawable(new ColorDrawable(l_ItemListView.getColor()));

        return convertView;
    }

    private class ListItemViewHolder{
        public TextView ms_Pseudo;
        public ImageView m_Avatar;
    }
}

