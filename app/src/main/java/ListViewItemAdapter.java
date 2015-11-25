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

public class ListViewItemAdapter extends ArrayAdapter<ListViewItem> {

    public ListViewItemAdapter(Context context, List<ListViewItem> l_ListViewItem) {
        super(context, 0, l_ListViewItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewlayout,parent, false);
        }

        ListViewItemHolder l_ListViewItemHolder = (ListViewItemHolder) convertView.getTag();
        if(l_ListViewItemHolder == null){
            l_ListViewItemHolder = new ListViewItemHolder();
            l_ListViewItemHolder.ms_Pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            l_ListViewItemHolder.m_Avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(l_ListViewItemHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        ListViewItem l_ListViewItem = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        l_ListViewItemHolder.ms_Pseudo.setText(l_ListViewItem.getPseudo());
        l_ListViewItemHolder.m_Avatar.setImageDrawable(new ColorDrawable(l_ListViewItem.getColor()));

        return convertView;
    }

    private class ListViewItemHolder{
        public TextView ms_Pseudo;
        public ImageView m_Avatar;
    }
}

