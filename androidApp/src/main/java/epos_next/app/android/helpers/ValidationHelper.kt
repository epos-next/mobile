package epos_next.app.android.helpers

object ValidationHelper {

    fun email(value: String?): Boolean {
        return if (value == null || value.isNullOrEmpty()) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()
        }
    }

    /// Not empty && MaximumLength = 100
    fun password(value: String?): Boolean {
        return value != null && !value.isNullOrEmpty() && value.length <= 100
    }

}