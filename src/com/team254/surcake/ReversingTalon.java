package com.team254.surcake;

import edu.wpi.first.wpilibj.Talon;

/**
 * Wrapper around talon enabling it to be reversed
 */
public class ReversingTalon {
    public enum Polarity {
        NORMAL(1.0),
        INVERTED(-1.0),;
        private final double mDirection;

        private Polarity(double direction) {
            mDirection = direction;
        }
    }

    private final Polarity mPolarity;
    private final Talon mTalon;

    public ReversingTalon(int channel, Polarity polarity) {
        mPolarity = polarity;
        mTalon = new Talon(channel);
    }

    public void set(double power) {
        mTalon.set(power * mPolarity.mDirection);
    }

    public static void setTalons(ReversingTalon[] talons, double power) {
        for (ReversingTalon talon : talons) {
            talon.set(power);
        }
    }

}
