package com.nami.mlib.animation.easing;

public interface EasingFunction {

    //Linear
    EasingFunction
            EASE_LINEAR = t -> t;

    //Sine
    EasingFunction
            EASE_IN_SINE = t -> 1 - Math.cos((t * Math.PI) / 2),
            EASE_OUT_SINE = t -> Math.sin((t * Math.PI) / 2),
            EASE_IN_OUT_SINE = t -> -(Math.cos(Math.PI * t) - 1) / 2;

    //Quad
    EasingFunction
            EASE_IN_QUAD = t -> t * t,
            EASE_OUT_QUAD = t -> 1 - (1 - t) * (1 - t),
            EASE_IN_OUT_QUAD = t -> t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;

    //Cubic
    EasingFunction
            EASE_IN_CUBIC = t -> t * t * t,
            EASE_OUT_CUBIC = t -> 1 - Math.pow(1 - t, 3),
            EASE_IN_OUT_CUBIC = t ->  t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2;

    double getTValue(double t);

}
