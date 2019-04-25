package io.icednut.kotlin.exercise

import io.icednut.kotlin.exercise.redux.Action
import io.icednut.kotlin.exercise.redux.createStore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ReduxStoreKtTest {

//    fun reducer(initial: Int): (state: Int?, action: Action) -> Int {
//        return { state: Int?, action: Action ->
//            val resultState = state ?: initial
//            when (action.type) {
//                "ADD" -> resultState + 1
//                else -> resultState
//            }
//        }
//    }

    @Test
    fun testRedux() {
        val reducer: (initial: Int) -> (state: Int?, action: Action) -> Int =
                { initial: Int ->
                    { state: Int?, action: Action ->
                        val resultState = state ?: initial
                        when (action.type) {
                            "ADD" -> resultState + 1
                            "MINUS" -> resultState - 1
                            else -> resultState
                        }
                    }
                }
        val store = createStore(reducer(0))

        store.subscribe { println(store.getState()) }

        store.dispatch(Action("ADD"))
        store.dispatch(Action("ADD"))
        store.dispatch(Action("ADD"))
        store.dispatch(Action("MINUS"))

        val state = store.getState()

        assertNotNull(store)
        assertEquals(state, 2)
    }
}