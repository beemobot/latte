package dev.ayu.latte.logging

import java.lang.reflect.Modifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

// From https://github.com/mrkirby153/giveaways/blob/a0e86cbeab4841781a98b6ac3b2dfce660a0e652/src/main/kotlin/com/mrkirby153/giveaways/utils/Logger.kt
fun <T : Any> unwrapCompanionClass(clazz: Class<T>): Class<*> {
    return clazz.enclosingClass?.let { enclosingClass ->
        try {
            enclosingClass.declaredFields.find { field ->
                field.name == clazz.simpleName && Modifier.isStatic(
                    field.modifiers
                ) && field.type == clazz
            }?.run { enclosingClass }
        } catch (e: SecurityException) {
            null
        }
    } ?: clazz
}

inline val Any.log: Logger
    get() = LoggerFactory.getLogger(unwrapCompanionClass(this::class.java))

fun getLogger(clazz: Class<*>): Logger = LoggerFactory.getLogger(clazz)
