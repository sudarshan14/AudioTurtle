package sid.test.parsedemo.app;

/**
 * Created by 10608780 on 8/24/2015.
 */
import android.app.Application;

import sid.test.parsedemo.helper.ParseUtils;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // register with parse
        ParseUtils.registerParse(this);
    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
