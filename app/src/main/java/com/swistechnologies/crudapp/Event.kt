package com.swistechnologies.crudapp

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
    private set //allows external read but not write

    //returns the content and prevents its use again
    fun getContentIFNotHandled(): T?{
        return if(hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }

    //returns the content even if its already  been handled
    fun peekContent(): T = content

}