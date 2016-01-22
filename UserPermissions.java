package com.attribe.yapjobs.app.Utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import com.attribe.yapjobs.app.Models.AppPermissions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Shahab on 1/20/16.
 */
public class UserPermissions {
    public static boolean checkIfTheUserPermissionNeeded(final Activity mActivity, final List<AppPermissions> permissionsList) {
        // for Access Fine Location
        final List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissions = new ArrayList<String>();
        for (AppPermissions permission:permissionsList)
        {
            if (!addPermission(permission.getAppPermission(),mActivity)) {
                permissionsNeeded.add(permission.getAppPermissionName());
                permissions.add(permission.getAppPermission());
            }
        }


        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(mActivity,permissions.toArray(new String[permissions.size()]),
                                    /* Use your request Code here */    ApplicationConstants.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        },mActivity);
                return true;
            }

            return false;

        }
        else
            return false;

    }
    private static boolean addPermission(String permission, Activity mActivity) {
        if (ContextCompat.checkSelfPermission(mActivity,permission) != PackageManager.PERMISSION_GRANTED) {
            // Check for Rationale Option
            // Edited:let the Android decide whether it is required or not
            // if (!ActivityCompat.shouldShowRequestPermissionRationale(mActivity,permission))
                return false;
        }
        return true;
    }
    private static void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener,Activity mActivity) {
        new AlertDialog.Builder(mActivity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
