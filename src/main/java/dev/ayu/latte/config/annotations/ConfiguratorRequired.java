package dev.ayu.latte.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Tells {@link dev.ayu.latte.config.Configurator} to have this field
 * required and call {@link System#exit(int)} if the field does not have a value.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfiguratorRequired {
}
