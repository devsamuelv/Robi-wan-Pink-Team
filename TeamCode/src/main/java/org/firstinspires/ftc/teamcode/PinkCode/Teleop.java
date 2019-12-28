package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@TeleOp(group = "Teleop", name = "Teleop")
public class Teleop extends OpMode {

    private double MotorPower = 100;
    private int PowerLevel = 5;

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
            ground_servo.setPosition(getServoGround() + 20);
            level_1_servo.setPosition(getLevel_1_servo() + 30);
        }

        while (gamepad1.dpad_down) {
            ground_servo.setPosition(getServoGround() - 20);
            level_1_servo.setPosition(getLevel_1_servo() - 30);
        }

        while (gamepad1.a) {
            grabber_left.setPosition(getGrabberLeft() + 5);
            grabber_right.setPosition(getGrabberRight() + 5);
        }

        while (gamepad1.b) {
            grabber_left.setPosition(getGrabberLeft() - 5);
            grabber_right.setPosition(getGrabberRight() - 5);
        }

        // code for servo arm
        // put it here

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
