package org.firstinspires.ftc.teamcode.PinkCode;

import android.drm.DrmStore;

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
        StartHardware(hardwareMap);
    }

    @Override
    public void loop() {

    }
}
