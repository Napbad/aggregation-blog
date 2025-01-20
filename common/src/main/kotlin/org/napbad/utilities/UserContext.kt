package org.napbad.utilities

object UserContext {
    private val THREAD_LOCAL_USER_ID: ThreadLocal<Int?> = ThreadLocal.withInitial { null }

    var userId: Int?
        get() = THREAD_LOCAL_USER_ID.get()
        set(userId) {
            THREAD_LOCAL_USER_ID.set(userId)
        }

    fun remove() {
        THREAD_LOCAL_USER_ID.remove()
    }
}
