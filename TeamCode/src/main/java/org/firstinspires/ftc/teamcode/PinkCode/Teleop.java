package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo.*;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.concurrent.TimeUnit;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@TeleOp(group = "Teleop", name = "Teleop")
public class Teleop extends OpMode {

    @Override
    public void init() {
        StartHardware(hardwareMap);
        grabber_right.setPosition(0.5);
        telemetry.addData("Servo Ground: ", ground_servo.getPosition());
        telemetry.addData("Servo Level 1: ", level_1_servo.getPosition());
        telemetry.addData("Servo Grabber Left: ", grabber_left.getPosition());
        telemetry.addData("Servo Grabber Right: ", grabber_right.getPosition());
        telemetry.update();
    }

    @Override
    public void loop() {
        RightFront.setPower(gamepad1.right_stick_y);
        RightBack.setPower(gamepad1.right_stick_y);
        LeftFront.setPower(gamepad1.left_stick_y);
        LeftBack.setPower(gamepad1.left_stick_y);

        while (gamepad1.y) {
            level_1_servo.setPosition(0.5);
            ground_servo.setPosition(0.8);
        }

        while (gamepad1.x) {
            for (long i = 0; i < 176; i++) {
                level_1_servo.setPosition(getLevel_1_servo() - 0.1);
                telemetry.addData("Level 1", level_1_servo.getPosition());
                telemetry.addData("Grabber Left", grabber_left.getPosition());
                telemetry.addData("Grabber Right", grabber_right.getPosition());
                telemetry.update();
            }
        }

        while (gamepad1.a) {
            grabber_left.setPosition(getGrabberLeft() + 0.1);
            grabber_right.setPosition(getGrabberRight() + 0.1);
            telemetry.addData("Servo Left", grabber_left.getPosition());
            telemetry.addData("Servo Right", grabber_right.getPosition());
            telemetry.update();
        }

        while (gamepad1.b) {
            grabber_left.setPosition(getGrabberLeft() - 0.1);
            grabber_right.setPosition(getGrabberRight() - 0.1);
            telemetry.addData("Servo Left", grabber_left.getPosition());
            telemetry.addData("Servo Right", grabber_right.getPosition());
            telemetry.update();
        }
    }
// 176000
    private double getServoGround() {
        return ground_servo.getPosition();
    }

    private double getLevel_1_servo() {
        return level_1_servo.getPosition();
    }

    private double getGrabberLeft() {
        return grabber_left.getPosition();
    }

    private double getGrabberRight() {
        return grabber_right.getPosition();
    }

    public void print(String caption, String message) {
        telemetry.addData(caption, message);
        telemetry.update();
    }

    public void timer(long time) {
        for (long i = 0; i < 176000; i++) {

        }
    }
}
