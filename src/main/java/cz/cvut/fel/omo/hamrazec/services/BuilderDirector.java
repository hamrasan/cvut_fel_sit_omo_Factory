package main.java.cz.cvut.fel.omo.hamrazec.services;

import main.java.cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public interface BuilderDirector {
    ProductLine build() throws CannotBuildLineException;
}
