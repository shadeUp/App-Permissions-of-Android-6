# App-Permissions-of-Android-6
This repository provide generic classes for Android 6 and previous versions requesting permission dynamically with in the Application



# build.gradle


```
 android {
    compileSdkVersion 23
    buildToolsVersion "23.0.1"
    defaultConfig {
        applicationId "com.myapp"
        minSdkVersion 16
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
        multiDexEnabled true
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_7
        targetCompatibility JavaVersion.VERSION_1_7
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:support-v4:23.0.1'
    compile 'com.android.support:appcompat-v7:23.0.1'
    compile 'com.android.support:design:23.0.1'
}
```
# MyActivity

```
package com.myapp;
public class MyActivity extends AppCompatActivity{
// Set your permission here
public static final List<AppPermissions> APP_PERMISSIONS = new ArrayList<AppPermissions>(){{
        add(new AppPermissions(Manifest.permission.ACCESS_FINE_LOCATION,"Approximate location"));
        add(new AppPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,"Precise location"));
    }};
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(UserPermissions.checkIfTheUserPermissionNeeded(this,APP_PERMISSIONS))
        {
            // Wait for the activity result
        }
        else
        {
            //Loading Data in Views because you already have all permissions that you requested
            loadData();
        }
        
     }
     @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

                case ApplicationConstants.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                    boolean AllpermissionsGranted = true;
                   for (int i : grantResults)
                       if(grantResults[i]==PackageManager.PERMISSION_DENIED)
                           AllpermissionsGranted = false;
                    if (AllpermissionsGranted)
                    {
                        // All Permissions Granted
                        Toast.makeText(GetDirection.this, "All Permissions Granted", Toast.LENGTH_SHORT)
                                .show();
                        // Now load your data here
                        loadData();
                    }
                    else
                    {
                        // Permission Denied
                        Toast.makeText(GetDirection.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                                .show();
                    }
                    break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                
        }
    }
}
