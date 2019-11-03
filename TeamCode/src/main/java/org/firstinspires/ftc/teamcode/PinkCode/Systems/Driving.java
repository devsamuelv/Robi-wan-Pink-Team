package org.firstinspires.ftc.teamcode.PinkCode.Systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

public class Driving {

    public static void ServoDrive(float RightServoX, float LeftServoX) {
        LeftFrontServo.setPosition(LeftServoX);
        RightFrontServo.setPosition(RightServoX);
    }

    public static void MotorDrive(double RightMotorX, double LeftMotorX) {
        LeftBack.setPower(LeftMotorX);
        RightBack.setPower(RightMotorX);
    }

    public static void ButtonControls(double SuperPower ,double Power ,boolean is_x_pressed, boolean is_y_pressed, boolean is_a_pressed, boolean is_b_pressed) throws Exception {
        if (is_x_pressed) {
            RightBack.setPower(Power);
        } else {
            telemetry.addData("Error", "Unknown Button");
            telemetry.update();
        }
    }
}
