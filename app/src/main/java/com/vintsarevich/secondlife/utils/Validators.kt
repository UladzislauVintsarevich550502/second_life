import android.text.Editable

fun isPasswordValid(text: Editable?): Boolean {
    return text != null && text.length >= 8
}

fun isNotEmpty(vararg texts: Editable?): Boolean {
    return texts.all { it != null && it.isNotEmpty() }
}