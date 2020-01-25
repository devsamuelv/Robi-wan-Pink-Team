package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.StartHardware;

public class Teleop2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        StartHardware(hardwareMap);
    }
}
