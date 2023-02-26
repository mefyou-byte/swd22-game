package at.compus02.swd.ss2022.game.common;

public final class DistanceCalculator {
    public static float calculateDistanceBetweenPointsWithHypot(
            float x1,
            float y1,
            float x2,
            float y2) {

        float ac = Math.abs(y2 - y1);
        float cb = Math.abs(x2 - x1);

        return (float) Math.hypot(ac, cb);
    }
}
