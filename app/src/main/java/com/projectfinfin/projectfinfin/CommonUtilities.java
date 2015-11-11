package com.projectfinfin.projectfinfin;

/**
 * Created by Ha on 11/10/2015.
 */

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {

    // give your server registration url here
    static final String SERVER_URL = "http://snappyshop.me/android/gcm/register.php";

    // Google project id
    public static final String SENDER_ID = "71087572818";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "Snappyshop GCM";

    static final String DISPLAY_MESSAGE_ACTION =
            "com.projectfinfin.projectfinfin.Notification.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "Nothing";

    /**
     * Notifies UI to display a message.
     * <p/>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
