package com.laomu.justgraduate.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.laomu.justgraduate.R;
import com.laomu.justgraduate.application.JGApplication;
import com.laomu.justgraduate.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class CommonUtils {

	private static final String TAG = "CommonUtils";

    public static void launchActivity(Context context, Class<?> activity) {
		Intent intent = new Intent(context, activity);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		context.startActivity(intent);
	}

	public static void launchActivityForResult(Activity context, Class<?> activity, int requestCode) {
		Intent intent = new Intent(context, activity);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		context.startActivityForResult(intent, requestCode);
	}

	public static void hideSoftKeybord(Activity activity) {

		if (null == activity) {
			return;
		}
		try {
			final View v = activity.getWindow().peekDecorView();
			if (v != null && v.getWindowToken() != null) {
				InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
		} catch (Exception e) {

		}
	}

	public static boolean isNull(String text) {
		if (text == null || "".equals(text.trim()) || "null".equals(text))
			return true;
		return false;
	}

	public static void startShakeAnim(Context context, View view) {
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
		view.startAnimation(shake);
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static ProgressDialog showProgress(Context context, CharSequence title, CharSequence message, boolean indeterminate, boolean cancelable) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setIndeterminate(indeterminate);
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(false);
		// dialog.setDefaultButton(false);
		dialog.show();
		return dialog;
	}

	public static String softVersion(Context context) {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return info.versionName;
	}

    public static String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    JGApplication.appContext.getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void toast(String msg) {
        Toast.makeText(JGApplication.appContext,msg,Toast.LENGTH_SHORT).show();

    }

    public static boolean hasShortcut() {
        return PreferenceManager.getShortCutIsAdded();
    }

    /**
     * 为程序创建桌面快捷方式
     */
    public static void addShortcut(Context context) {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        //快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(R.string.app_name));
        shortcut.putExtra("duplicate", false); //不允许重复创建

        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
        shortcutIntent.setClassName(context, context.getClass().getName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

        //快捷方式的图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, R.drawable.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        PreferenceManager.setShortCutAdded();

        context.sendBroadcast(shortcut);
    }

    public static  void delShortcut(Activity activity){
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

        //快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, activity.getString(R.string.app_name));
        String appClass = activity.getPackageName() + "." +activity.getLocalClassName();
        ComponentName comp = new ComponentName(activity.getPackageName(), appClass);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));

        activity.sendBroadcast(shortcut);

    }

    public static boolean copyFile(String fileToPath, String fileName, InputStream in)
            throws Exception {
        boolean copySucc = false;
        OutputStream out = null;
        try {
            File fdir = new File(fileToPath);
            if(!fdir.exists()){
                fdir.mkdirs();
            }

            File fFile = new File(fileToPath + File.separator + fileName);

            if(!fFile.exists()){
                fFile.createNewFile();
            }

            out = new FileOutputStream(fFile);
            byte[] buffer = new byte[1024];
            while (true) {
                int ins = in.read(buffer);
                if (ins == -1) {
                    break;
                }

                out.write(buffer, 0, ins);
            }
            copySucc = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.flush();
                out.close();
            }
        }

        return copySucc;
    }
}
