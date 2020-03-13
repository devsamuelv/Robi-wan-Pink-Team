package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.PinkCode.Systems.Controls;
import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@TeleOp(group = "Teleop", name = "Teleop Main")
public class Teleop extends Controls {
    @Override
    public void init() {
        try {
            StartHardware(hardwareMap);
        } catch (Exception e) {
            telemetry.addData("Error", e.getMessage());
            telemetry.update();
        }
    }

    @Override
    public void loop() {
        drive(false, false, gamepad1.right_stick_y, gamepad1.left_stick_x);

        // strafe right
        if (gamepad1.dpad_right) {
            drive(false, true, 1.0,1.0);
        }

        // strafe left
        if (gamepad1.dpad_left) {
            drive(true, false, 1.0,1.0);
        }

        if (gamepad1.y) {
            liftUp();
        }

        if (gamepad1.x) {
            liftDown();
        }

        if (gamepad1.a) {
            pickBlock();
        }

        if (gamepad1.b) {
            relaseBlock();
        }
    }
    // 176000 for second
}
