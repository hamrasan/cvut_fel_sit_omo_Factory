package cz.cvut.fel.omo.hamrazec.test.services;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.person.Worker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;
import cz.cvut.fel.omo.hamrazec.services.MachineGenerator;
import cz.cvut.fel.omo.hamrazec.services.SeriesFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BuilderCTest {

    private Factory factory = Factory.getInstance();
    private SeriesFactory seriesFactory = new SeriesFactory();
    private ProductionSeries productionSeries = seriesFactory.getSeriesC(400,3);
    private MachineGenerator generator = new MachineGenerator();
    private List<LineWorker> workers = new ArrayList<>();
    private ControllingRobot controllingRobot = generator.generateControlRobot();
    private int minProduction = 0;

    public BuilderCTest() throws IOException {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() throws IOException {

        boolean first = true;
        List<LineWorker> robotList = new ArrayList<>();
        List<LineWorker> machineList = new ArrayList<>();
        List<LineWorker> peopleList = new ArrayList<>();
        machineList.add(generator.generateMachine());
        machineList.add(generator.generateMachine());
        robotList.add(generator.generateRobot());
        robotList.add(generator.generateRobot());
        peopleList.add(new Worker("Jan", "Bolo", 200, 8));

        workers.addAll(robotList);
        workers.addAll(machineList);
        workers.addAll(peopleList);
        workers.add(controllingRobot);
        factory.setLineWorkers(workers);
        factory.putWorkersToProduction(workers);

        for (LineWorker lw: workers) {
            if (first) {
                minProduction = lw.getProductPerTact();
                first = false;
            }
            if (lw.getProductPerTact() < minProduction) minProduction= lw.getProductPerTact();
        }
    }

    @Test
    public void build_enoughWorkersForBuildC_worksCorrect() throws CannotBuildLineException {

        ProductionLine productionLine = productionSeries.build();
        assertEquals("Line doesnt build",productionSeries,productionLine.getSeries());
    }

    @Test
    public void update_regularUpdateC_worksCorrect() throws CannotBuildLineException {

        ProductionLine productionLine = productionSeries.build();
        productionLine.update();
        assertEquals("Line doesnt update product",minProduction,controllingRobot.getFinishedAmount());
    }

    @Test
    public void build_noEnoughWorkersForBuildC_worksCorrect() throws CannotBuildLineException {

        thrown.expect(CannotBuildLineException.class);
        thrown.reportMissingExceptionWithMessage("Line was created without lineworkers");
        productionSeries.build();
        productionSeries.build();
    }

}
