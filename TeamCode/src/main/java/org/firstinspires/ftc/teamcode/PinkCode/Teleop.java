package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@TeleOp(group = "Teleop", name = "Teleop")
public class Teleop extends OpMode {

    @Override
    public void init() {
        StartHardware(hardwareMap);
    }

    @Override
    public void loop() {
        RightFront.setPower(gamepad1.right_stick_y);
        RightBack.setPower(gamepad1.right_stick_y);
        LeftFront.setPower(gamepad1.left_stick_y);
        LeftBack.setPower(gamepad1.left_stick_y);

        while (gamepad1.dpad_up) {
            ground_servo.setPosition(getServoGround() - 0.1);
            for (double i = Servo.MIN_POSITION; i < Servo.MAX_POSITION; i++) {
                level_1_servo.setPosition(i);
            }
        }

        while (gamepad1.dpad_down) {
            ground_servo.setPosition(getServoGround() + 0.1);
            level_1_servo.setPosition(getLevel_1_servo() + 0.1);
        }

        while (gamepad1.a) {
            grabber_left.setPosition(getGrabberLeft() + 0.1);
            grabber_right.setPosition(getGrabberRight() + 0.1);
        }

        while (gamepad1.b) {
            grabber_left.setPosition(getGrabberLeft() - 0.1);
            grabber_right.setPosition(getGrabberRight() - 0.1);
        }
    }

    private double getServoGround() {
        return ground_servo.getPosition();
    }

    private double getLevel_1_servo() {
        return level_1_servo.getPortNumber();
    }

    private double getGrabberLeft() {
        return grabber_left.getPosition();
    }

    private double getGrabberRight() {
        return grabber_right.getPosition();
    }
}
