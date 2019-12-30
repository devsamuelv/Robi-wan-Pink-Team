package org.firstinspires.ftc.teamcode.PinkCode;

import android.drm.DrmStore;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@Autonomous(name = "Auto", group = "Auto")
public class Auto extends OpMode {

    @Override
    public void init() {
        StartHardware(hardwareMap);
    }

    @Override
    public void loop() {
        LeftBack.setPower(-80);
        LeftFront.setPower(-80);
        RightBack.setPower(-80);
        RightFront.setPower(-80);
        try {
            wait(5000);
        } catch (InterruptedException e) {
            telemetry.addData("Error: ",e.getMessage());
            telemetry.update();
        }
        LeftBack.setPower(-80);
        LeftFront.setPower(-80);
        RightBack.setPower(80);
        RightFront.setPower(80);
        try {
            wait(5000);
        } catch (InterruptedException e) {
            telemetry.addData("Error: ",e.getMessage());
            telemetry.update();
        }
    }
}
