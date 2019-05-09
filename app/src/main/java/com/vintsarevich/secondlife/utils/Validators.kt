import android.text.Editable

fun isPasswordValid(text: Editable?): Boolean {
    return text != null && text.length >= 8
}

fun isUsernameValid(text: Editable?): Boolean {
    return text != null && text.isNotEmpty()
}