package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.PinkCode.Systems.Driving.*;
import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@TeleOp(group = "Teleop", name = "Teleop")
public class Teleop extends OpMode {

    @Override
    public void init() {
        try {
            StartHardware();
            telemetry.addData("Robot Info", "Robot Initialized");
            telemetry.update();
        } catch (Exception e) {
            telemetry.addData("Error", "System Error " + e.getMessage() + " Robot Not Initialized");
            telemetry.update();
        }
    }

    @Override
    public void loop() {
        MotorDrive(gamepad1.right_stick_y, gamepad1.left_stick_y);
        ButtonControls(30, gamepad1.x, gamepad1.y, gamepad1.a, gamepad1.b);
    }
}
