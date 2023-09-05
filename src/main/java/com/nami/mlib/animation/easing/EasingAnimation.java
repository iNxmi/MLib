package com.nami.mlib.animation.easing;

public class EasingAnimation {

    private static final double FRAME_TIME = 1.0d / 60.0d;

    public static void animate(double seconds, EasingFunction function, EasingRunnable runnable) {
        double startTime = time();
        double lastTime = startTime;

        boolean running = true;
        while (running) {
            if (!((time() - lastTime) / FRAME_TIME >= 1))
                continue;

            double t = (time() - startTime) / seconds;
            t = t < 0 ? 0 : t > 1 ? 1 : t;

            runnable.animate(t, function.getTValue(t));

            if (t >= 1)
                running = false;

            lastTime = time();
        }
    }

    private static double time() {
        return System.currentTimeMillis() / 1000.0d;
    }

}
