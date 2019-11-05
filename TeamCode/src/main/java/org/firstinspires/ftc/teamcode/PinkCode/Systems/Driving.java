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
    }

    public static void ButtonControls(double Power ,boolean is_x_pressed, boolean is_y_pressed, boolean is_a_pressed, boolean is_b_pressed){
        if (is_x_pressed) {
            LeftBack.setPower(Power);
            LeftFront.setPower(Power);
        } else if (is_y_pressed) {
            RightFront.setPower(Power);
            RightBack.setPower(Power);
        } else if (is_b_pressed) {
            RightFront.setPower(Power);
            LeftBack.setPower(Power);
        } else if (is_a_pressed) {
            LeftFront.setPower(Power);
            RightBack.setPower(Power);
        } else {
            telemetry.addData("Message", "Button's Are Not Working?");
            telemetry.update();
        }
    }
}
