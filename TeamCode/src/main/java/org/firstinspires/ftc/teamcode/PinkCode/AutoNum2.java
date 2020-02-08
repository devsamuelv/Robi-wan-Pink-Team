package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.PinkCode.Systems.Controls;

import java.lang.invoke.ConstantCallSite;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

public class AutoNum2 extends LinearOpMode {
    Controls Controls = new Controls();
    @Override
    public void runOpMode() throws InterruptedException{
        Controls.drive(false, false,1.0,1.0);
        sleep(1000);
        Controls.drive(false, false, 0,0);
    }

    @Deprecated
    private void drive(double rightFront, double rightBack, double leftFront, double leftBack) {
        Range.clip(rightFront, -1.0, 1.0);
        Range.clip(rightBack, -1.0, 1.0);
        Range.clip(leftFront, -1.0, 1.0);
        Range.clip(leftBack, -1.0, 1.0);
        RightFront.setPower(rightFront);
        RightBack.setPower(rightBack);
        LeftFront.setPower(leftFront);
        LeftBack.setPower(leftBack);
    }
}
