package org.firstinspires.ftc.teamcode.PinkCode.Systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

public class Driving {

    public static void ServoDrive(float RightServoX, float LeftServoX) {
        // no code here for now.
    }

    public static void MotorDrive(double RightPower, double LeftPower) {
        LeftBack.setPower(LeftPower);
        RightBack.setPower(RightPower);
        RightFront.setPower(RightPower);
        LeftFront.setPower(LeftPower);
        telemetry.addData("Message", "Motor Moved" + RightFront.getCurrentPosition());
        telemetry.update();
    }

    public static void ButtonControls(double Power ,boolean is_x_pressed, boolean is_y_pressed, boolean is_a_pressed, boolean is_b_pressed){

    }
}
