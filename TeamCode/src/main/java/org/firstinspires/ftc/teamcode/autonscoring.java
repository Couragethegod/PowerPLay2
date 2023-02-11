package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class autonscoring extends LinearOpMode {
    DcMotor Lwheels;
    DcMotor Rwheels;
    DcMotor Barm;
    DcMotor Uarm;
    DcMotor Claw;
    CRServo Rclaw;
    CRServo Lclaw;

    @Override
    public void runOpMode() {
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

        Uarm.setTargetPosition(40);
        Barm.setTargetPosition(40);
        Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Uarm.setPower(0.75);
        Barm.setPower(0.75);
        while (Barm.isBusy() && Uarm.isBusy()) {
            sleep(15);
        }
        Claw.setPower(-.5);
        sleep(500);
        Lclaw.setPower(-1);
        Rclaw.setPower(1);
        sleep(200);
        Lclaw.setPower(0);
        Rclaw.setPower(0);
        sleep(500);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 200);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 200);
        Lwheels.setPower(1);
        Rwheels.setPower(1);
        Rwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Lwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 510);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 510);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 1100);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 1100);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 510);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 510);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 1460);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 1460);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(500);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 670);
        while (Lwheels.isBusy()) {
            sleep(15);
        }
        sleep(500);
        Uarm.setTargetPosition(1440);
        Barm.setTargetPosition(1440);
        while (Barm.isBusy() && Uarm.isBusy()) {
            sleep(15);
        }
        Lwheels.setPower(0.5);
        Rwheels.setPower(0.5);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 120);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 120);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Claw.setPower(.4);
        sleep(250);
        Claw.setPower(0);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 100);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 100);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Uarm.setTargetPosition(40);
        Barm.setTargetPosition(40);
        while (Barm.isBusy() && Uarm.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Lwheels.setPower(1);
        Rwheels.setPower(1);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 300);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 300);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 590);
        while (Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 860);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 860);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 1120);
        while (Rwheels.isBusy()) {
            sleep(15);
        }
        /*sleep(1000);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 2000);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 2000);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);*/
        sleep(250);
        Uarm.setTargetPosition(240);
        Barm.setTargetPosition(240);
        while (Barm.isBusy() && Uarm.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 2320);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 2320);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Claw.setPower(-.5);
        sleep(50);
        Lclaw.setPower(-1);
        Rclaw.setPower(1);
        sleep(250);

        Uarm.setTargetPosition(600);
        Barm.setTargetPosition(600);
        while (Barm.isBusy() && Uarm.isBusy()) {
            sleep(15);
        }
        Lclaw.setPower(0);
        Rclaw.setPower(0);
        sleep(250);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 2300);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 2300);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 530);
        while (Rwheels.isBusy()) {
            sleep(15);
        }
        Uarm.setTargetPosition(Uarm.getCurrentPosition() + 420 );
        Barm.setTargetPosition(Barm.getCurrentPosition() + 420 );
    }
}