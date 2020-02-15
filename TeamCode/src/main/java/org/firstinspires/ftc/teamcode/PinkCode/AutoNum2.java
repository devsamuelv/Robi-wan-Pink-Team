package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.PinkCode.Systems.Controls;
import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@Autonomous(group = "auto", name = "Auto Simple")
public class AutoNum2 extends Controls {
//    Controls Controls = new Controls();

    @Override
    public void init() {
        StartHardware(hardwareMap);
        init();
    }

    @Override
    public void loop() {
        drive(false, false,1.0,1.0);
    }
}
