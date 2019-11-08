package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.PinkCode.Systems.Driving.*;
import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@TeleOp(group = "Teleop", name = "Teleop")
public class Teleop extends OpMode {

    private double MotorPower = 2000;

    @Override
    public void init() {
        StartHardware(hardwareMap);
    }

    @Override
    public void loop() {
        LeftBack.setPower(gamepad1.left_stick_y);
        LeftFront.setPower(gamepad1.left_stick_y);

        RightFront.setPower(gamepad1.right_stick_y);
        RightBack.setPower(gamepad1.right_stick_y);
    }
}
