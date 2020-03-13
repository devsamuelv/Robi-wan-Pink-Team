package org.firstinspires.ftc.teamcode.PinkCode.Systems;

import com.qualcomm.hardware.microsoft.MicrosoftGamepadXbox360;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.PinkCode.Teleop;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

public abstract class Controls extends OpMode {
    public MicrosoftGamepadXbox360 xgamepad = new MicrosoftGamepadXbox360();

    @Deprecated
    public void setGroundServoPostion(double position) {
        Range.clip(position, 0.5, 0.7);
        ground_servo.setPosition(position);
    }

    public void liftUp() {
        ground_servo.setPosition(0.7);
    }
    public void liftDown() {
        ground_servo.setPosition(0.5);
    }
    public void drive(boolean strafeL, boolean strafeR, double right, double left) {
        try {
            // if not strafing drive
            if (!strafeL && !strafeR) {
                if (right > 0.5) {
                    double power = 0;
                    if (gamepad1.right_bumper) {
                        power = 0.5;
                    }

                    if (xgamepad.right_bumper) {
                        power = 0.5;
                    }

                    RightFront.setPower(-power);
                    RightBack.setPower(-power);
                    LeftBack.setPower(-power);
                    LeftFront.setPower(-power);
                    telemetry.addData("Direction: ", "Back");
                    telemetry.update();
                }

                if (right > -0.5) {
                    double power = 1.0;

                    if (gamepad1.right_bumper) {
                        power = 0.5;
                    }

                    if (xgamepad.right_bumper) {
                        power = 0.5;
                    }

                    RightFront.setPower(power);
                    RightBack.setPower(power);
                    LeftBack.setPower(power);
                    LeftFront.setPower(power);
                    telemetry.addData("Direction: ", "Front");
                    telemetry.update();
                }

                if (left > 0.5) {
                    LeftFront.setPower(-left);
                    LeftBack.setPower(-left);
                    RightBack.setPower(left);
                    RightFront.setPower(left);
                    telemetry.addData("Direction: ", "Right");
                    telemetry.update();

                }

                if (left > 0.5) {
                    LeftFront.setPower(left);
                    LeftBack.setPower(left);
                    RightFront.setPower(-left);
                    RightBack.setPower(-left);
                    telemetry.addData("Direction: ", "Left");
                    telemetry.update();

                }
            }

            // strafe Left
            if (strafeL) {
                double power = 1.0;
                RightFront.setPower(-power);
                RightBack.setPower(power);
                LeftFront.setPower(power);
                LeftBack.setPower(-power);
                telemetry.addData("Strafe Power", "Power: " + power);
                telemetry.update();
            }

            // strafe right
            if (strafeR) {
                double power = 1.0;
                RightFront.setPower(power);
                RightBack.setPower(-power);
                LeftFront.setPower(-power);
                LeftBack.setPower(power);
                telemetry.addData("Strafe Power", "Power: " + power);
                telemetry.update();
            }
        } catch (NullPointerException e) {
            telemetry.addData("Error: ", e.getMessage());
            telemetry.addData("Cause: ", e.getCause());
            telemetry.update();
        }
    }
    private double getGroundSevo() {
        return ground_servo.getPosition();
    }
    public void pickBlock() {
        grabber.setPosition(0.7);
        telemetry.addData("Grabber", getGrabber());
        telemetry.update();
    }
    public void relaseBlock() {
        grabber.setPosition(0.5);
        telemetry.addData("Grabber", getGrabber());
        telemetry.update();
    }
    private double getGrabber() {
        return grabber.getPosition();
    }
}