package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@Autonomous(name = "Show Off", group = "Auto")
public class Auto extends OpMode {

    private double Motor_Power;
    private long sleep_time = 5000;
    private boolean STOP = false;

    @Override
    public void init() {
    }

    @Override
    public void loop() {
        LeftBack.setPower(Motor_Power);
        LeftFront.setPower(Motor_Power);
        RightBack.setPower(Motor_Power);
        RightFront.setPower(Motor_Power);
        if (STOP == true) {
            sleep_time = 0;
        }
        if (gamepad1.x) {
            STOP = true;
        }
        try {
            Thread.sleep(sleep_time);
        } catch (Exception e) {
            telemetry.addData("Error", e.getMessage() + " Error Caused By " + e.getCause());
            telemetry.update();
        }
    }
}
