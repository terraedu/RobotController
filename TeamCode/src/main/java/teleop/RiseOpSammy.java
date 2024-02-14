package teleop;

import static global.General.gph1;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp( name = "RiseOpSammy", group = "TeleOp")

public class RiseOpSammy extends Tele{


    @Override

    public void initTele() {


    }
    @Override
    public void loopTele() {
drive.move(gph1.ry,gph1.rx,gph1.lx){


        }
}
