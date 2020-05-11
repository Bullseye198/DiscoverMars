package com.cm.baseAndroid

open class SingleLiveEvent<out T>(private val content: T) {

    var hasBeenHandled = false
    private set //Allow all external read but do not write.

    /**
     * Return content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

