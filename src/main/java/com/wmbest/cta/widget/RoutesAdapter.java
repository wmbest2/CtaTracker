package com.wmbest.cta.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.view.*;
import android.widget.*;

import butterknife.*;

import com.vokal.database.*;

import com.wmbest.cta.R;

import com.wmbest.cta.models.Route;

public class RoutesAdapter extends CursorAdapter {

    private LayoutInflater mInflater;

    public RoutesAdapter(Context aContext, Cursor aCursor) {
        super(aContext, aCursor, false);
        mInflater = LayoutInflater.from(aContext);
    }

    @Override
    public View newView(Context aContext, Cursor aCursor, ViewGroup aParent) {
        View view = mInflater.inflate(R.layout.route_list_item, aParent, false);
        view.setTag(new ViewHolder(view));
        return view;
    }

    @Override
    public void bindView(View aView, Context aContext, Cursor aCursor) {
        ViewHolder vh = (ViewHolder) aView.getTag();
        CursorGetter getter = new CursorGetter(aCursor);

        vh.icon.setImageDrawable(FontIconDrawable.inflate(aContext.getResources(), R.xml.bus));
        vh.number.setText(getter.getString(Route.NUMBER));
        vh.name.setText(getter.getString(Route.NAME));
    }

    static class ViewHolder {
        @InjectView(R.id.icon) ImageView icon;
        @InjectView(R.id.number) TextView number;
        @InjectView(R.id.name) TextView name;
        
        public ViewHolder(View aView) {
            ButterKnife.inject(this, aView);
        }
    }
}
