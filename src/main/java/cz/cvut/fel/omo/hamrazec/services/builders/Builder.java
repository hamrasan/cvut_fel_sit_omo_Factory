package cz.cvut.fel.omo.hamrazec.services.builders;

import cz.cvut.fel.omo.hamrazec.exceptions.NotEnoughWorkers;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public interface Builder {
    void createLine();
    void setMachines() throws NotEnoughWorkers;
    void setPeople() throws NotEnoughWorkers;
    void setRobots() throws NotEnoughWorkers;
    void setOrder();
    void setControl() throws NotEnoughWorkers;
    void cancelBuilding();
    ProductLine getResult();
}
