package cz.cvut.fel.omo.hamrazec.model.machine.state;

import cz.cvut.fel.omo.hamrazec.model.events.Alert;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;

import java.util.Random;

public class Working extends State {

    public Working(Machine context) {
        super(context);
    }

    @Override
    public boolean canWork() {
        if (new Random().nextInt(15) == 3) {
            context.setState(new Broken(context));
            ProductionLine line = context.getProductionLine();
            int priority = line.getPriority();
            Alert a = new Alert(priority, context);
            context.getEventList().receive(a);
            return false;
        } else {
            return true;
        }
    }
}
