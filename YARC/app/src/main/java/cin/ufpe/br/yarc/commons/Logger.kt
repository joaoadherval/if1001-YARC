package cin.ufpe.br.yarc.commons

import android.util.Log

object Logger {
    private const val TAG = "YARC"

    fun dt(value: String) {
        Log.d(TAG, "Thread Name: ${Thread.currentThread().name} - $value")
    }
}
