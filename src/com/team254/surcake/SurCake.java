package com.team254.surcake;

import edu.wpi.first.wpilibj.Timer;

/**
 * Controls the Citrus Circuits' Cheesecake
 */
public class SurCake {

    public enum GrabberDirection {
        EXTEND(1.0),
        NEUTRAL(0.0),
        RETRACT(-1.0),;
        private final double mMotorPower;

        GrabberDirection(double motorPower) {
            mMotorPower = motorPower;
        }
    }

    private static final double GRABBER_EXTEND_TIME = 1.0;

    private final ReversingTalon[] mGrabberTalons;

    public SurCake() {
        mGrabberTalons = new ReversingTalon[] {
                new ReversingTalon(2, ReversingTalon.Polarity.NORMAL),
                new ReversingTalon(3, ReversingTalon.Polarity.INVERTED),
        };
    }

    /**
     * Call this in the auto periodic callback
     */
    public void updateForAuto() {
        setGrabberDirection(
                Timer.getMatchTime() < GRABBER_EXTEND_TIME
                        ? GrabberDirection.EXTEND
                        : GrabberDirection.NEUTRAL);
    }

    /**
     * Call this in the tele-op periodic callback
     * @param grabberDirection Output to the can grabber
     */
    public void updateForTeleop(GrabberDirection grabberDirection) {
        setGrabberDirection(grabberDirection);
    }

    private void setGrabberDirection(GrabberDirection grabberDirection) {
        ReversingTalon.setTalons(mGrabberTalons, grabberDirection.mMotorPower);
    }
}
