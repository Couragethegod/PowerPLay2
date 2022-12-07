package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Zeshan_Noooo extends LinearOpMode {

    public DcMotor backR;
    public DcMotor FR;

    @Override
    public void runOpMode() {
        backR = hardwareMap.get(DcMotor.class, "backR");
        FR = hardwareMap.get(DcMotor.class, "FR");
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            float drive = gamepad1.left_stick_y;
            float turn = gamepad1.left_stick_x;

            backR.setPower((-(drive + turn) / 4) * 3);
            FR.setPower((-(drive + turn) / 4) * 3);
        }
    }
}