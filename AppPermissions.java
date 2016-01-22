package com.attribe.yapjobs.app.Models;

/**
 * Created by Muhammad Shahab on 1/20/16.
 */
public class AppPermissions {
    private String appPermission;
    private String appPermissionName;

    public AppPermissions(String appPermission, String appPermissionName) {
        this.appPermission = appPermission;
        this.appPermissionName = appPermissionName;
    }

    public String getAppPermission() {
        return appPermission;
    }

    public void setAppPermission(String appPermission) {
        this.appPermission = appPermission;
    }

    public String getAppPermissionName() {
        return appPermissionName;
    }

    public void setAppPermissionName(String appPermissionName) {
        this.appPermissionName = appPermissionName;
    }
}
