
package com.team254.surcake;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {

    private static final int JOYSTICK_X_AXIS = 0;
    private static final int JOYSTICK_Y_AXIS = 1;
    private static final int GRABBER_IN_BUTTON = 0;
    private static final int GRABBER_OUT_BUTTON = 1;

    private final SurCake mSurCake;

    private final ReversingTalon[] mLeftDriveTalons;
    private final ReversingTalon[] mRightDriveTalons;

    private final Joystick mJoystick;

    public Robot() {
        mSurCake = new SurCake();
        mLeftDriveTalons =
                new ReversingTalon[]{new ReversingTalon(0, ReversingTalon.Polarity.NORMAL)};
        mRightDriveTalons =
                new ReversingTalon[]{new ReversingTalon(1, ReversingTalon.Polarity.NORMAL)};
        mJoystick = new Joystick(0);
    }


    @Override
    public void autonomousPeriodic() {
        mSurCake.updateForAuto();
        ReversingTalon.setTalons(mLeftDriveTalons, 0);
        ReversingTalon.setTalons(mRightDriveTalons, 0);
    }

    @Override
    public void teleopPeriodic() {
        // grabber control
        SurCake.GrabberDirection grabberDirection = SurCake.GrabberDirection.NEUTRAL;
        if (mJoystick.getRawButton(GRABBER_IN_BUTTON)) {
            grabberDirection = SurCake.GrabberDirection.RETRACT;
        } else if (mJoystick.getRawButton(GRABBER_OUT_BUTTON)) {
            grabberDirection = SurCake.GrabberDirection.EXTEND;
        }
        mSurCake.updateForTeleop(grabberDirection);

        // arcade drive calculation
        double x = mJoystick.getRawAxis(JOYSTICK_X_AXIS);
        double y = mJoystick.getRawAxis(JOYSTICK_Y_AXIS);
        ReversingTalon.setTalons(mLeftDriveTalons, y + x);
        ReversingTalon.setTalons(mRightDriveTalons, y - x);
    }
}
