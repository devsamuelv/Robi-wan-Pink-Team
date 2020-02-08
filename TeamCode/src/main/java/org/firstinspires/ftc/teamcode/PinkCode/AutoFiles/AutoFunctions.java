package org.firstinspires.ftc.teamcode.PinkCode.AutoFiles;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.PinkCode.Auto;
import org.firstinspires.ftc.teamcode.PinkCode.Systems.Controls;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@Autonomous(group = "Auto", name = "Auto")
@Disabled
public class AutoFunctions extends LinearOpMode {
    Controls Controls = new Controls();
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        wait(5000);

    }

}
