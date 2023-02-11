package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Teleop_main extends LinearOpMode {
    private DcMotor Lwheels;
    private DcMotor Rwheels;
    private DcMotor Barm;
    private DcMotor Uarm;
    private DcMotor Claw;
    private CRServo Rclaw;
    private CRServo Lclaw;

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

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        Uarm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Barm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        /*Uarm.setTargetPosition(40);
        Barm.setTargetPosition(40);
        Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Uarm.setPower(0.5);
        Barm.setPower(0.5);*/

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            move();
            arm();
            claw();
            telemetry.addData("Uarm", Uarm.getCurrentPosition());
            telemetry.addData("Barm", Barm.getCurrentPosition());
            telemetry.addData("Claw", Claw.getCurrentPosition());
            telemetry.update();
        }
    }
    public void move(){
        float drive = -gamepad1.left_stick_y;
        float turn = gamepad1.left_stick_x;
        if (Uarm.getCurrentPosition() > 400 && Barm.getCurrentPosition() > 1015){
            Lwheels.setPower(((drive + turn) / 4) * 2);
            Rwheels.setPower(((drive - turn) / 4) * 2);
        } else {
            Lwheels.setPower(((drive + turn) / 4) * 4);
            Rwheels.setPower(((drive - turn) / 4) * 4);
        }
    }

    public void arm() {
        if (gamepad1.y) {
            // high
            Uarm.setTargetPosition(1440);
            Barm.setTargetPosition(1440);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(.5);
            Barm.setPower(.5);
        } else if (gamepad1.b) {
            // medium
            Uarm.setTargetPosition(1015);
            Barm.setTargetPosition(1015);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(.5);
            Barm.setPower(.5);
        } else if (gamepad1.a) {
            // ground
            Uarm.setTargetPosition(40);
            Barm.setTargetPosition(40);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(0.5);
            Barm.setPower(0.5);
        } else if (gamepad1.x) {
            // low
            Uarm.setTargetPosition(620);
            Barm.setTargetPosition(620);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(.5);
            Barm.setPower(.5);
            //sleep(500);
        }
        if (gamepad2.dpad_down && Barm.getCurrentPosition() > 30 ) {
            // if at junction higher than ground
            Uarm.setTargetPosition(Uarm.getCurrentPosition() - 40);
            Barm.setTargetPosition(Barm.getCurrentPosition() - 40);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(0.5);
            Barm.setPower(0.5);
        } else if (gamepad2.dpad_up) {
            Uarm.setTargetPosition(Uarm.getCurrentPosition() + 40);
            Barm.setTargetPosition(Barm.getCurrentPosition() + 40);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(0.5);
            Barm.setPower(0.5);
        } else if (gamepad2.dpad_down && Barm.getCurrentPosition() < 50) {
            // if at ground height
        }
    }

    public void claw(){
        if (gamepad2.x) {
            // open
            Claw.setPower(0.3);
        } else if (gamepad2.b) {
            // close
            Claw.setPower(-0.6);
        } else if (gamepad2.y) {
            // cut power
            Claw.setPower(0);
        }
        if (gamepad2.left_bumper){
            Lclaw.setPower(0);
            Rclaw.setPower(0);
        }
        else if (gamepad2.right_bumper){
            Lclaw.setPower(-1);
            Rclaw.setPower(1);
        }
    }
}