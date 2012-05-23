package genetics;

import utils.EventsSolver;
import utils.exceptions.SolverException;
import utils.exceptions.SonsInicialitazionException;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public abstract class GenericSolver {
    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    

    public abstract String getInfo();

    public abstract boolean setParameters(String parameters);

    public GenericSolver() {
    }

    public abstract void run() throws SolverException, SonsInicialitazionException;

    public abstract boolean SetEvents(EventsSolver eventSolver);

    public abstract boolean SetSelection(String parms);

    public abstract boolean SetStopCrit(String parms);

    public abstract boolean SetMutation(String parms);

    public abstract boolean SetRecombination(String parms);

    public abstract boolean SetReplacement(String parms);

    public abstract boolean SetTSPProbl(String parms);

    public abstract void StopSolver();

    public abstract Population getPopulation();

    public abstract boolean setPopulation(Population p);
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************        
}
