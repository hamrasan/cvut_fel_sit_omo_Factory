package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.VisitorInspector;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;

import java.util.logging.Logger;

public class Inspector extends Person implements VisitorInspector {
    private static final Logger LOG = Logger.getLogger(Inspector.class.getName());

    public Inspector(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }

    @Override
    public void visit(LineRobot lineRobot) {
        LOG.info("Deprecation in " + lineRobot.getClass().getSimpleName() + " is: "+ lineRobot.getDepreciation());
    }


    @Override
    public void visit(ControllingRobot controllingRobot) {
        LOG.info("Deprecation in " + controllingRobot.getClass().getSimpleName() + " is: "+ controllingRobot.getDepreciation());

    }


    @Override
    public void visit(LineMachine machine) {
        LOG.info("Deprecation in " + machine.getClass().getSimpleName() + " is: "+ machine.getDepreciation());

    }

    public void startIterate(InspectorIterator inspectorIterator){

        while (inspectorIterator.hasNext()){
            inspectorIterator.next().accept(this);
        }
    }


    @Override
    public void accept(VisitorDirector visitor) {

    }
}
