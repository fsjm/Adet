package com.fabricesalmon.adet;

import com.fabricesalmon.adet.R;

import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import android.content.Context;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.graphics.drawable.ColorDrawable;

public class ListViewItemAdapter extends ArrayAdapter<ListViewItem> {

    public ListViewItemAdapter(Context context, List<ListViewItem> l_ListViewItem) {
        super(context, 0, l_ListViewItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewlayout, parent, false);
        }

        ListViewItemHolder l_ListViewItemHolder = (ListViewItemHolder) convertView.getTag();
        if (null == l_ListViewItemHolder) {
            l_ListViewItemHolder = new ListViewItemHolder();
            l_ListViewItemHolder.setPseudo((TextView) convertView.findViewById(R.id.pseudo));
            l_ListViewItemHolder.setAvatar((ImageView) convertView.findViewById(R.id.avatar));
            convertView.setTag(l_ListViewItemHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<ListViewItem> l_ListViewItem
        ListViewItem l_ListViewItem = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        l_ListViewItemHolder.getPseudo().setText(l_ListViewItem.getPseudo());
        l_ListViewItemHolder.getAvatar().setImageDrawable(new ColorDrawable(l_ListViewItem.getColor()));

        return convertView;
    }

    private class ListViewItemHolder {
        private TextView m_Pseudo;
        private ImageView m_Avatar;

        public TextView getPseudo() {
            return m_Pseudo;
        }

        public void setPseudo(TextView l_Pseudo) {
            m_Pseudo = l_Pseudo;
        }

        public ImageView getAvatar() {
            return m_Avatar;
        }

        public void setAvatar(ImageView l_Avatar) {
            m_Avatar = l_Avatar;
        }
    }
}

