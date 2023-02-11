package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class test extends LinearOpMode {
    DcMotor Lwheels;
    DcMotor Rwheels;
    DcMotor Barm;
    DcMotor Uarm;
    DcMotor Claw;
    CRServo Rclaw;
    CRServo Lclaw;

    @Override
    public void runOpMode() throws InterruptedException {
        Uarm = hardwareMap.get(DcMotor.class, "Uarm");
        Barm = hardwareMap.get(DcMotor.class, "Barm");
        Lwheels = hardwareMap.get(DcMotor.class, "Lwheels");
        Rwheels = hardwareMap.get(DcMotor.class, "Rwheels");
        Claw = hardwareMap.get(DcMotor.class, "Claw");
        Lclaw = hardwareMap.get(CRServo.class, "Lclaw");
        Rclaw = hardwareMap.get(CRServo.class, "Rclaw");

        Lwheels.setDirection(DcMotorSimple.Direction.REVERSE);
        Rwheels.setDirection(DcMotorSimple.Direction.FORWARD);
        Barm.setDirection(DcMotorSimple.Direction.REVERSE);
        Uarm.setDirection(DcMotorSimple.Direction.REVERSE);

        Uarm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Barm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Rwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        Uarm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Barm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lwheels.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Rwheels.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        /*Uarm.setTargetPosition(40);
        Barm.setTargetPosition(40);
        Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Uarm.setPower(0.75);
        Barm.setPower(0.75);
        while (Barm.isBusy() && Uarm.isBusy()) {
            sleep(15);
        }*/
        Claw.setPower(-.6);
        Uarm.setTargetPosition(40);
        Barm.setTargetPosition(40);
        Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Uarm.setPower(0.75);
        Barm.setPower(0.75);

        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 3100);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 3100);
        Lwheels.setPower(.75);
        Rwheels.setPower(.75);
        Rwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Lwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(500);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 330);
        while (Lwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Uarm.setTargetPosition(1440);
        Barm.setTargetPosition(1440);
        while (Uarm.isBusy() && Barm.isBusy()){
            sleep(15);
        }
        sleep(300);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 249);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 249);
        Lwheels.setPower(.5);
        Rwheels.setPower(.5);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(1000);

        Claw.setPower(.3);
        sleep(250);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 250);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 250);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        Uarm.setTargetPosition(40);
        Barm.setTargetPosition(40);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 690);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 690);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
    }
}