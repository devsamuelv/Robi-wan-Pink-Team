package org.firstinspires.ftc.teamcode.PinkCode.Systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorImpl;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

public class Driving extends DcMotorImpl {

    public Driving(DcMotorController controller, int portNumber) {
        super(controller, portNumber);
    }
}
