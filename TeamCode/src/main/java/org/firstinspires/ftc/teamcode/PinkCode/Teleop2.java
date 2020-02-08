package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.PinkCode.Systems.Controls;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.StartHardware;

@TeleOp(group = "Teleop", name = "Teleop2 Simple")
public class Teleop2 extends LinearOpMode {
    Controls Controls = new Controls();
    @Override
    public void runOpMode() throws InterruptedException {
        StartHardware(hardwareMap);
        Controls.drive(false,false,1.0,1.0);
    }
}
