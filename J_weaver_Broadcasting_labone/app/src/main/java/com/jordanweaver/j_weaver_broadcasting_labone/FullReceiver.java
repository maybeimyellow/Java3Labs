package com.jordanweaver.j_weaver_broadcasting_labone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/31/15.
 */
public class FullReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ActionsExtras.ACTION_SAVE)){

            if(intent.hasExtra(ActionsExtras.EXTRA_FIRST)){
                Log.e("Worked!", intent.getStringExtra(ActionsExtras.EXTRA_FIRST));

                FileHelper helper = new FileHelper(context);

                ArrayList<SerializeObject> loadedArray = helper.loadArray();

                SerializeObject newProfile = new SerializeObject(
                        intent.getStringExtra(ActionsExtras.EXTRA_FIRST),
                                intent.getStringExtra(ActionsExtras.EXTRA_LAST),
                                        intent.getIntExtra(ActionsExtras.EXTRA_AGE, -1));

                if(loadedArray.size() == 0){
                    loadedArray.add(0, newProfile);
                } else {
                    loadedArray.add(loadedArray.size(), newProfile);
                }

                helper.onSave(loadedArray);

                Intent intent1 = new Intent(ActionsExtras.ACTION_UPDATE);
                context.sendBroadcast(intent1);

            }




        } else if (intent.getAction().equals(ActionsExtras.ACTION_DELETE)){

        }

    }
}
