package com.example.phemmelliot.ilab;

import android.app.Activity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by phemmelliot on 5/18/17.
 */



    public class NewsAdapter extends ArrayAdapter<News>
    {
        private static final String LOG_TAG = NewsAdapter.class.getSimpleName();
        private int colorId;
        private Activity mContext;

        public NewsAdapter(Activity context, ArrayList<News> data)
        {
            super(context,0,data);
            mContext = context;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            News News = getItem(position);
            View itemView = convertView;
            if(itemView == null)
                itemView = LayoutInflater.from(getContext()).inflate(R.layout.drawer_item, parent, false);
            TextView title = (TextView) itemView.findViewById(R.id.title);
            title.setText(News.getTitle());

            TextView text = (TextView) itemView.findViewById(R.id.text);
            text.setText(News.getText());

            final ImageView overFlow = (ImageView) itemView.findViewById(R.id.overflow);
            ImageView thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

            if(News.hasImage())
            {
                Glide.with(mContext).load(News.getImg()).into(thumbnail);
            }
            else
                Glide.with(mContext).load(R.drawable.tech1).into(thumbnail);



            overFlow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(overFlow);
                }
            });
            return itemView;
        }

        /**
         * Showing popup menu when tapping on 3 dots
         */
        private void showPopupMenu(View view) {
            // inflate menu
            PopupMenu popup = new PopupMenu(mContext, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_album, popup.getMenu());
            popup.setOnMenuItemClickListener(new NewsAdapter.MyMenuItemClickListener());
            popup.show();
        }

        /**
         * Click listener for popup menu items
         */
        class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

            public MyMenuItemClickListener() {
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add_favourite:
                        Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_unread:
                        Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                }
                return false;
            }
        }




        }



