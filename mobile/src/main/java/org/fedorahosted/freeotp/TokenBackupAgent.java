package org.fedorahosted.freeotp;
import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

    public class TokenBackupAgent extends BackupAgentHelper{
        static final String BACKUP = "tokenBackup";
        static final String RESTORED = "restoreComplete";
        private SharedPreferences mBackups;
        static final String PREFS_BACKUP_KEY = "data";

        @Override
        public void onCreate() {
            super.onCreate();
            SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, BACKUP);
            addHelper(PREFS_BACKUP_KEY, helper);
            mBackups = getApplicationContext().getSharedPreferences(BACKUP, Context.MODE_PRIVATE);
        }

        @Override
        public void onRestoreFinished() {
            super.onRestoreFinished();
            mBackups.edit().putBoolean(RESTORED, true).apply();
        }
    }
