package cz.cvut.fel.omo.hamrazec.model.events;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public class StartRepair extends Event {
    private Machine repairing;


    public StartRepair(FactoryWorker sender, Machine repairing) {
        this.sender = sender;
        this.repairing = repairing;
        this.tact = sender.getTact();

    }


    public Machine getRepairing() {

        return repairing;
    }
}
