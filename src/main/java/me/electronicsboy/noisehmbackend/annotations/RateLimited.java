package me.electronicsboy.noisehmbackend.annotations;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimited {
	int limit() default 10;
}
