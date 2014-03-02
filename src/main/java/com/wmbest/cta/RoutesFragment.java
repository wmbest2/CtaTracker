package com.wmbest.cta;

import android.app.*;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import dagger.*;
import barstool.*;
import butterknife.*;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import com.vokal.volley.VolleyBallDebug;

import javax.inject.Inject;

import com.wmbest.cta.dagger.DaggerApp;
import com.wmbest.cta.service.RouteService;
import com.wmbest.cta.models.Route;
import com.wmbest.cta.widget.RoutesAdapter;

import com.vokal.database.DatabaseHelper;

public class RoutesFragment extends ListFragment {

    @Inject Bus mBus;
    @Inject RouteService mService;

    @Override
    public void onCreate(Bundle aState) {
        super.onCreate(aState);

        ObjectGraph graph = ((DaggerApp) getActivity().getApplication()).graph();
        graph.inject(this);

        getLoaderManager().initLoader(0, null, new RoutesLoader());
    }

    @Override
    public void onStart() {
        super.onStart();
        mBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mBus.unregister(this);
    }

    @Subscribe
    public void onServerChange(VolleyBallDebug.Changed aEvent) {
        ObjectGraph graph = ((DaggerApp) getActivity().getApplication()).graph();
        graph.inject(this);
        mService.fetchRoutes();
    }

    public class RoutesLoader implements LoaderCallbacks<Cursor> {
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Uri uri = DatabaseHelper.getContentUri(Route.class);
            return new CursorLoader(getActivity(), uri, null,
                null, null, null);
        }
    
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data == null || data.getCount() == 0) {
                mService.fetchRoutes();
            }

            if (getListAdapter() == null) {
                setListAdapter(new RoutesAdapter(getActivity(), data));
            } else {
                ((CursorAdapter) getListAdapter()).swapCursor(data);
            }
        }
    
        public void onLoaderReset(Loader<Cursor> loader) {
            if (getListAdapter() != null) {
                ((CursorAdapter) getListAdapter()).swapCursor(null);
            }
        }
    }
}
