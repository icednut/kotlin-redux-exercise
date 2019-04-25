package io.icednut.kotlin.exercise.redux

class Action(val type: String, val content: Any) {

    constructor(actionType: String) : this(actionType, Any())
}