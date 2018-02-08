package com.example.sprischepko.evotorappforwork;

        import android.app.ListActivity;
        import android.app.ProgressDialog;
        import android.content.ActivityNotFoundException;
        import android.content.Intent;
        import android.content.pm.ApplicationInfo;
        import android.content.pm.PackageManager;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ListView;
        import android.widget.Toast;
        import java.util.ArrayList;
        import java.util.List;


public class VersionAppsActivity extends ListActivity {

    private PackageManager packageManager = null;
    private List applist = null;
    private AppAdapter listadapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_apps);

        packageManager = getPackageManager();

        new LoadApplications().execute();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = (ApplicationInfo) applist.get(position);

        try{
            Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);

            if(intent != null) {
                startActivity(intent);
            }
        } catch(ActivityNotFoundException e) {
            Toast.makeText(VersionAppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Toast.makeText(VersionAppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private List checkForLaunchIntent(List list) {

        ArrayList appList = new ArrayList();

        for(Object info : list) {
            try{
                if(packageManager.getLaunchIntentForPackage(getPackageName()) != null) {
                    appList.add(info);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return appList;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

            listadapter = new AppAdapter(VersionAppsActivity.this, R.layout.list_item, applist);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(listadapter);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(VersionAppsActivity.this, null, "Loading apps info...");
            super.onPreExecute();
        }
    }
}

