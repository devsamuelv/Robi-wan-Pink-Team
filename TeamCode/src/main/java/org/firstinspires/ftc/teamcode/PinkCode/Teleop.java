package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.PinkCode.Systems.Controls;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@TeleOp(group = "Teleop", name = "Teleop")
public class Teleop extends OpMode {

    public int press = 0;
    private Controls Controls = new Controls();

    @Override
    public void init() {
        StartHardware(hardwareMap);
        Controls.init();
    }

    @Override
    public void loop() {
        Controls.drive(false, false, gamepad1.right_stick_y, gamepad1.left_stick_y);

        // strafe right
        if (gamepad1.dpad_right) {
            Controls.drive(false, true, 1.0,1.0);
        }

        // strafe left
        if (gamepad1.dpad_left) {
            Controls.drive(true, false, 1.0,1.0);
        }

        if (gamepad1.x) {
            Controls.setGroundServoPostion(0.5);
        }

        if (gamepad1.y) {
            Controls.setGroundServoPostion(0.7);
        }

        if (gamepad1.a) {
            Controls.pickBlock();
        }

        if (gamepad1.b) {
            Controls.relaseBlock();
        }
    }
    // 176000 for second
}
