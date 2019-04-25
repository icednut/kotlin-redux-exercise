package io.icednut.kotlin.exercise.redux

class ReduxStore<T>(reducer: (currentState: T?, action: Action) -> T) {
    private var isDispatching: Boolean = false
    private val listeners: MutableList<() -> Unit> = mutableListOf()
    private val currentReducer: (currentState: T?, action: Action) -> T = reducer
    private var currentState: T? = null

    init {
        dispatch(Action("INIT"))
    }

    fun dispatch(action: Action) {
        if (isDispatching) {
            throw RuntimeException("리듀서 처리 중이기 때문에 액션을 디스패칭 할 수 없습니다.")
        }

        try {
            isDispatching = true
            // 리듀서 실행해서 상태 갱신하기
            currentState = currentReducer(currentState, action)
        } finally {
            isDispatching = false
        }

        for (listener in listeners) {
            listener()
        }
    }

    fun subscribe(listener: () -> Unit) {
        listeners.add(listener)
    }

    fun getState(): Any? {
        return currentState
    }
}

fun <T> createStore(reducer: (currentState: T?, action: Action) -> T): ReduxStore<T> {
    return ReduxStore(reducer)
}