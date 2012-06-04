package genetics;

import genetics.algorithms.K100;
import genetics.algorithms.K50;
import genetics.algorithms.KnapSack;
import genetics.algorithms.OnesMax;
import genetics.algorithms.TSP;
import utils.TSPProblem;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Genetic;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.mutation.Invertion;
import operators.mutation.Mutation;
import operators.mutation.MutationGaussian;
import operators.mutation.SwapGenes;
import operators.recombinations.Crossover;
import operators.recombinations.CrossoverAX;
import operators.recombinations.OrderCrossover;
import operators.recombinations.PMX;
import operators.recombinations.Recombination;
import operators.recombinations.UniformCrossover;
import operators.replacements.Replacement;
import operators.replacements.Truncation;
import operators.selections.Roulette;
import operators.selections.SUS;
import operators.selections.Selection;
import utils.EventsSolver;
import utils.PopulationUtils;
import utils.exceptions.SolverException;
import utils.exceptions.SonsInicialitazionException;

/**
 *
 * @author diogoantonio
 */
public class Solver extends GenericSolver {

    private Population _parentsPopulation;
    private Population _sonsPopulation;
    private int _sizePopulation;
    private int _sizeGenotype;
    private int _sizeGenome;
    private int _sizeAllelo;
    private Individual _prototypeIndividual;
    private StopCriterion _stopCriterion;
    private int _numberIteractions;
    public ArrayList<Operator> _operators;
    private EventsSolver _eventSolver;
    public TSPProblem TSP = null;
    //Variável que permite parar o solver
    //True=Solver parador/False=solver a correr
    private volatile boolean Stop = false;
    private volatile boolean OperatorBlock = false;

    /**
     * Construtor do solver
     * @param operators Array de operadores geneticos
     * @param eventSolver Eventos que são chamados consuante o solver vai correndo
     */
    public Solver(ArrayList<Operator> operators, EventsSolver eventSolver) {
        this(100, 20, new OnesMax(), new StopCriterion(100, 18.0), operators, eventSolver);
    }

    /**
     * Construtor do solver que gera uma população de forma aleatoria
     * @param sizePopulation Tamanho da população
     * @param sizeAllelo Tamanho do Allelo
     * @param prototypeIndividual Tipo de individuo que a população vai conter
     * @param stopCriterion Criterio de paragem para o solver
     * @param operators Array de operadores geneticos
     * @param eventSolver Eventos que são chamados consuante o solver vai correndo
     */
    public Solver(int sizePopulation, int sizeAllelo, Individual prototypeIndividual, StopCriterion stopCriterion, ArrayList<Operator> operators, EventsSolver eventSolver) {
        this._sizePopulation = sizePopulation;
        this._sizeAllelo = sizeAllelo;
        this._prototypeIndividual = prototypeIndividual;
        this._stopCriterion = stopCriterion;
        this._eventSolver = eventSolver;
        this._operators = operators;

        this._sizeGenotype = Population.DEFAULT_SIZE_GENOTYPE;
        this._sizeGenome = Population.DEFAULT_SIZE_GENOME;
    }

    /**
     * Construtore do solver que passa uma população como parametro
     * @param population População inicial
     * @param stopCriterion Criterios de paragem para o solver
     * @param operators Array de operadores geneticos
     * @param eventSolver Eventos que são chamados consuante o solver vai correndo
     */
    public Solver(Population population, StopCriterion stopCriterion, ArrayList<Operator> operators, EventsSolver eventSolver) {
        this(population.getSizePopulation(), population.getSizeAllelo(), population.getTypePopulation(), stopCriterion, operators, eventSolver);

        // Por defeito cria logo um clone da população para que ela
        // a partir de agora seja uma população independente da original
        this._parentsPopulation = population.clone();
    }

    /**
     * Construtor sem parametros passados para que seja possivel instanciar a classe Solver.
     */
    public Solver() {
        this._operators = new ArrayList<Operator>();
    }

    protected long timePassSeconds(long startTime) {
        // Converte a diferença para segundos
        return (java.lang.management.ManagementFactory.getThreadMXBean().getCurrentThreadUserTime() - startTime) / 1000000000;
    }
    
    /**
     * Metodo que faz correr o Solver, ou seja, aplica os operadores geneticos
     * sobre a população ate encontrar o individuo com o fitness desejado ou atingir
     * o numero maximo de gerações pre-determinado pelo utilizador
     * @throws SolverException Caso exista um erro no correr do solver dispara esta excepção
     */
    @Override
    public void run() throws SolverException, SonsInicialitazionException {

        long __startTime;
        ArrayList<Operator> __operators = null;
        
        // Tempo que a thread esteve a correr
        __startTime = java.lang.management.ManagementFactory.getThreadMXBean().getCurrentThreadUserTime();
        
        // Capturar erros de codigo não programados
        try {

            // Evento inicial quando o solver inicia
            if (this._eventSolver != null) {
                this._eventSolver.EventStartSolver();
            }

            this._numberIteractions = 0;

            // So cria uma população nova e aleatoria quando não é passado uma
            // população no construtor. Situação onde neste ponto de codigo ja 
            // existe uma população é quando estamos a correr a mesma população
            // com diferentes configurações de operadores
            if (this._parentsPopulation == null) {
                this._parentsPopulation = new Population(this._sizePopulation, this._sizeGenome, this._sizeGenotype, this._sizeAllelo, this._prototypeIndividual);
            }

            //Verifica se é o TSP que está a ser resolvido ou não - Caso seja, o fitness terá que ser minimizado
            if (this._prototypeIndividual instanceof genetics.algorithms.TSP) {
                // Ciclo que corre o solver e que só termina quando atingir o numero
                // maximo de gerações/iterações definadas para o solver ou um individuo
                // atingir o fitness desejado
                //OperatorBlock=true;
                while ((this._numberIteractions < this._stopCriterion.getNumberIteractions() ||  this._stopCriterion.getNumberIteractions() == StopCriterion.NO_ITERACTIONS_LIMIT)
                        && (PopulationUtils.getBestFitness(this._parentsPopulation) > this._stopCriterion.getGoodFiteness())
                        && (timePassSeconds(__startTime) < this._stopCriterion.getSecondsToRun())) {
                    
                    if(OperatorBlock==false){
                        System.out.println("************************************\nNovos parametros....\n*************************");
                        __operators = (ArrayList<Operator>)_operators.clone();
                        OperatorBlock=true;
                    }
                    
                    /**
                     * Faz a normalização de todos os individuos das duas populações
                     */
                    for (int i = 0; i < this._parentsPopulation.getPopulation().size(); i++) {
                        ((TSP) this._parentsPopulation.getPopulation().get(i)).individualNormalization();
                    }
                    if (_sonsPopulation != null) {
                        for (int i = 0; i < this._sonsPopulation.getPopulation().size(); i++) {
                            ((TSP) this._sonsPopulation.getPopulation().get(i)).individualNormalization();
                        }
                    }

                    // Corre todos os operadores que foram passados para este solver
                    for (int __indexOperators = 0; __indexOperators < __operators.size(); __indexOperators++) {

                        // Se o operador for do tipo Selection
                        if (__operators.get(__indexOperators) instanceof Selection) {
                            // aplica o operador a população de pais e devolve uma nova população de filhos
                            this._sonsPopulation = ((Genetic) __operators.get(__indexOperators)).execute(this._parentsPopulation);
                        }

                        // Se o operador por do tipo Recombinação ou Mutação
                        if (__operators.get(__indexOperators) instanceof Recombination || __operators.get(__indexOperators) instanceof Mutation) {

                            // Dispara um erro se a população de filhos não tiver sido inicializada
                            if (this._sonsPopulation == null) {
                                throw new SonsInicialitazionException();
                            }

                            // aplica o operador a população de filhos e devolve uma nova população de filhos
                            _sonsPopulation = ((Genetic) __operators.get(__indexOperators)).execute(_sonsPopulation);
                        }

                        // Se o operador por do tipo Replacements
                        if (__operators.get(__indexOperators) instanceof Replacement) {
                            // aplica o operador a população de filhos e pais e devolve 
                            // os melhores para a proxima geração. Este processo faz deles
                            // os proximos pais
                            _parentsPopulation = ((Replacement) __operators.get(__indexOperators)).execute(this._parentsPopulation, this._sonsPopulation);
                        }

                    }

                    // no final de cada iteração dispara um evento que passa
                    // o numero da iteração e a população gerada
                    if (this._eventSolver != null) {
                        this._eventSolver.EventIteraction(this._numberIteractions, this._parentsPopulation);
                    }

                    // incrementa mais uma geração/iteração à variavel
                    this._numberIteractions++;
                    //System.out.println("Iteration: " + _numberIteractions);
                    //System.out.println("Best Fitness Parents: " + PopulationUtils.getBestFitness(_parentsPopulation) + PopulationUtils.getHallOfFame(_parentsPopulation, 1).toString());
                    //System.out.println("Best Fitness Sons: " + PopulationUtils.getBestFitness(_sonsPopulation) + PopulationUtils.getHallOfFame(_sonsPopulation, 1).toString());
                    //System.out.println("------------------------------------------------------------------------------");

                    if (Stop == true) {
                        System.out.println("Solver Terminado por pedido!");
                        break;
                    }

                }
            } else {
                // Ciclo que corre o solver e que só termina quando atingir o numero
                // maximo de gerações/iterações definadas para o solver ou um individuo
                // atingir o fitness desejado
                System.out.println("**********************************OperadorBlock:"+OperatorBlock);
                while ((this._numberIteractions < this._stopCriterion.getNumberIteractions() ||  this._stopCriterion.getNumberIteractions() == StopCriterion.NO_ITERACTIONS_LIMIT)
                        && (
                            (this._stopCriterion.getTypeProblem() == StopCriterion.TYPE_PROBLEM_MAXIMIZATION && PopulationUtils.getBestFitness(this._parentsPopulation) < this._stopCriterion.getGoodFiteness()) 
                            ||
                            (this._stopCriterion.getTypeProblem() == StopCriterion.TYPE_PROBLEM_MINIMIZATION && PopulationUtils.getBestFitness(this._parentsPopulation) > this._stopCriterion.getGoodFiteness()) 
                        )
                        && (timePassSeconds(__startTime) < this._stopCriterion.getSecondsToRun())) {

                    if(OperatorBlock==false){
                        System.out.println("************************************\nNovos parametros....\n*************************");
                        __operators = (ArrayList<Operator>)_operators.clone();
                        OperatorBlock=true;
                    }
                    
                    // Corre todos os operadores que foram passados para este solver
                    for (int __indexOperators = 0; __indexOperators < __operators.size(); __indexOperators++) {

                        // Se o operador for do tipo Selection
                        if (__operators.get(__indexOperators) instanceof Selection) {
                            ((Selection)__operators.get(__indexOperators)).setTypeSelection(this._stopCriterion.getTypeProblem());
                            // aplica o operador a população de pais e devolve uma nova população de filhos
                            this._sonsPopulation = ((Genetic) __operators.get(__indexOperators)).execute(this._parentsPopulation);
                        }

                        // Se o operador por do tipo Recombinação ou Mutação
                        if (__operators.get(__indexOperators) instanceof Recombination || __operators.get(__indexOperators) instanceof Mutation) {

                            // Dispara um erro se a população de filhos não tiver sido inicializada
                            if (this._sonsPopulation == null) {
                                throw new SonsInicialitazionException();
                            }

                            // aplica o operador a população de filhos e devolve uma nova população de filhos
                            _sonsPopulation = ((Genetic) __operators.get(__indexOperators)).execute(_sonsPopulation);
                        }

                        // Se o operador por do tipo Replacements
                        if (__operators.get(__indexOperators) instanceof Replacement) {
                            // aplica o operador a população de filhos e pais e devolve 
                            // os melhores para a proxima geração. Este processo faz deles
                            // os proximos pais
                            ((Replacement)__operators.get(__indexOperators)).setTypeReplacement(this._stopCriterion.getTypeProblem());
                            _parentsPopulation = ((Replacement) __operators.get(__indexOperators)).execute(this._parentsPopulation, this._sonsPopulation);
                        }

                    }

                    // no final de cada iteração dispara um evento que passa
                    // o numero da iteração e a população gerada
                    if (this._eventSolver != null) {
                        this._eventSolver.EventIteraction(this._numberIteractions, this._parentsPopulation);
                    }

                    // incrementa mais uma geração/iteração à variavel
                    this._numberIteractions++;
                    //System.out.println("Iteration: " + _numberIteractions);
                    //System.out.println("Best Fitness Parents: " + PopulationUtils.getBestFitness(_parentsPopulation));
                    //System.out.println("Best Fitness Sons: " + PopulationUtils.getBestFitness(_sonsPopulation));
                    //System.out.println("------------------------------------------------------------------------------");

                    if (Stop == true) {
                        System.out.println("Solver Terminado por pedido!");
                        break;
                    }

                }
            }


            // Evento final quando o solver esta terminado
            if (this._eventSolver != null) {
                this._eventSolver.EventFinishSolver(this._numberIteractions, this._parentsPopulation);
                
                System.out.println("");
                System.out.println("Solver Terminou");
                System.out.println("Total Iterações: " + this._numberIteractions);
                System.out.println("Tempo utilizado: " + timePassSeconds(__startTime) + "s");
            }

        } catch (SonsInicialitazionException ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SonsInicialitazionException();
        } catch (Exception ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SolverException();
        }
    }

    @Override
    public String getInfo() {
        String s =
                "<p>É utilizado, em todos os métodos (excepto no setEvents) uma String com</p>"
                + "<p>os parametros pretendidos. Cada parametro será sempre separado do próximo</p>"
                + "<p>por um espaço.</p>"
                + "<p></p>"
                + "<h3>setParameters</h3>"
                + "<p>É passada uma string onde o primeiro parametro desta será a dimensão da</p>"
                + "<p>população, o segundo o tamanho do genoma de cada individuo, o terceiro o</p>"
                + "<p>tamanho do genotype do mesmo, o quarto o tamanho do allelo e o quinto o tipo</p>"
                + "<p>de individuo.</p>"
                + "<p></p>"
                + "<p>Ex: setParameters(1000 1 1 10 OnesMax) - É criado um solver que terá uma </p>"
                + "<p>população inicial de 1000 individuos, cada um com um cromossoma, cada cromossoma</p>"
                + "<p>com um gene e cada gene contem um array de tamanho 10. Os individuos são do tipo</p>"
                + "<p>OnesMax.</p>"
                + "<p>Em caso do individuo ser do tipo KnapSack genérico, será preciso passar mais</p>"
                + "<p>três parametros que serão o peso da mochila, o modo de funcionamento do problema</p>"
                + "<p>e os dados com os pesos e valores da mochila.</p>"
                + "<p></p>"
                + "<p>Ex: setParameters(1000 1 1 3 KnapSack ModeFunction.RANDOM 2$$3 2 3 10 3 2) - Neste</p>"
                + "<p>caso, a população será igual à de cima, excepto o allelo do individuo que tem um </p>"
                + "<p>array de tamanho 3. O ModeFunction refere-se à forma como o problema será abordado </p>"
                + "<p>em termos de penalidade e o 2 refere-se a uma penalidade de orndem 2. A partir dos $$,</p>"
                + "<p>será a string de dados que contem o valor1, valor2, valor3 e peso1, peso2 e peso3</p>"
                + "<p>respectivamente aos items da mochila do problema.</p>"
                + "<p></p>"
                + "<h3>setSelection</h3>"
                + "<p>É passada uma string onde o primeiro parametro é o nome do operador a ser utilizado e</p>"
                + "<p>o segundo poderá, ou não, ser os parametros desse operador. É opcional os parametros</p>"
                + "<p>para os operadores, sendo que se não forem passados, o operador será construido com</p>"
                + "<p>os valores por defeito.</p>"
                + "<p>Ex: setSelection(SUS 100) - É inicializado um operador de selecção do tipo SUS em</p>"
                + "<p>que a dimensão da nova população é de 100</p>"
                + "<p>Ex: setSelection(SUS) - É inicializado um operador de selecção do tipo SUS em que a</p> "
                + "<p>dimensão da nova população é a por defeito do operador</p>"
                + "<p></p>"
                + "<h3>setRecombination</h3>"
                + "<p>É passada uma string onde o primeiro parametro é o nome do operador a ser utilizado e</p>"
                + "<p>o segundo poderá, ou não, ser os parametros desse operador. É opcional os parametros</p>"
                + "<p>para os operadores, sendo que se não forem passados, o operador será construido com os</p>"
                + "<p>valores por defeito.</p>"
                + "<p>Ex: setRecombination(OrderCrossover 0.75) - É inicializado um operador de recombinação</p>"
                + "<p>do tipo OrderCrossover em que a probabilidade de recombinação é de 75%.</p>"
                + "<p>Ex: setRecombination(OrderCrossover) - É inicializado um operador de recombinação do</p>"
                + "<p>tipo OrderCrossover em que a probabilidade de recombinação é a de defeito do operador.</p>"
                + "<p></p>"
                + "<h3>setMutation</h3>"
                + "<p>É passada uma string onde o primeiro parametro é o nome do operador a ser utilizado e o</p>"
                + "<p>segundo poderá, ou não, ser os parametros desse operador. É opcional os parametros para</p>"
                + "<p>os operadores, sendo que se não forem passados, o operador será construido com os</p>"
                + "<p>valores por defeito.</p>"
                + "<p>Ex: setMutation(SwapGenes 0.75) - É inicializado um operador de mutação do tipo</p>"
                + "<p>SwapGenes em que a probabilidade de mutação é de 75%.</p>"
                + "<p>Ex: setMutation(SwapGenes) - É inicializado um operador de mutação do tipo SwapGenes</p>"
                + "<p>em que a probabilidade de mutação é a de defeito do operador.</p>"
                + "<p></p>"
                + "<h3>setReplacement</h3>"
                + "<p>É passada uma string onde o primeiro parametro é o nome do operador a ser utilizado e o</p>"
                + "<p>segundo poderá, ou não, ser os parametros desse operador. É opcional os parametros para os</p>"
                + "<p>operadores, sendo que se não forem passados, o operador será construido com os valores por</p>"
                + "<p>defeito.</p>"
                + "<p>Ex: setReplacement(Truncation 75) - É inicializado um operador de substituição do tipo</p>"
                + "<p>Truncation que devolverá 75 individuos.</p>"
                + "<p>Ex: setReplacement(Truncation) - É inicializado um operador de substituição do tipo </p>"
                + "<p>Truncation que devolverá tantos individuos quantos os que estão definidos por defeito</p>"
                + "<p>no operador.</p>"
                + "<p></p>"
                + "<h3>setStopCrit</h3>"
                + "<p>É passada uma string onde o primeiro paramêtro é o valor máximo de fitness para o problema parar,</p>"
                + "<p>o segundo paramêtro será o número máximo de iterações do problema, o terceiro parametro será o número</p>"
                + "<p>de segundos que o problema estará a correr e o quarto parametro será o tipo de problema (0 para maximização, 1 para minimização)</p>"
                + "<p>Ex: setStopCrit(423.99 1 3600 1) - O critério de paragem é definido com o fitness de paragem em 423.99,</p>"
                + "<p>com o número de bests do problema a serem 1, com o tempo máximo a correr a ser de 3600 segundos e</p>"
                + "<p>e com o problema a ser definido como de minimização.</p>";
        return s;
    }

    @Override
    public boolean SetEvents(EventsSolver eventSolver) {
        //Caso não seja null, mas a definição do event solver e devolve true
        if (eventSolver != null) {
            this._eventSolver = eventSolver;
            return true;
        }
        //Caso seja null, devolve false
        return false;
    }

    @Override
    public boolean SetSelection(String parms) {
        if(_operators == null) _operators = new ArrayList<Operator>(4);
        try {
            String tipoSelector = parms.split(" ")[0];
            int dimNewPop = 0;
            try {
                //verifica se foram passados parametros para o operador de selecção
                dimNewPop = Integer.parseInt(parms.split(" ")[1]);
            } catch (Exception ex) {
                //Não existem mais parametros
            }
            //Caso de ser o operador SUS
            if (tipoSelector.contains("SUS")) {
                System.out.println("------------------------");
                System.out.println("SELECTION: SUS");
                //Verifica se existem parametros para o operador ou não
                if (dimNewPop == 0) {
                    this._operators.set(0,new SUS());
                } else {
                    this._operators.set(0,new SUS(dimNewPop));
                    System.out.println("Dim new Pop: " + dimNewPop);
                    System.out.println("------------------------");
                }
            }//Caso de ser o operador Roulette
            else if (tipoSelector.contains("Roulette")) {
                System.out.println("SELECTION: ROULETTE");
                //Verifica se existem parametros para o operador ou não
                if (dimNewPop == 0) {
                    this._operators.set(0,new Roulette());
                } else {
                    this._operators.set(0,new Roulette(dimNewPop));
                }
            }//Caso de ser o operador Tournament 
            else if (tipoSelector.contains("Tournament")) {
                System.out.println("SELECTION: TOURNAMENT");
                //Verifica se existem parametros para o operador ou não
                if (dimNewPop == 0) {
                    this._operators.set(0,new operators.selections.Tournament());
                } else {
                    int sizeTourn = Integer.parseInt(parms.split(" ")[2]);
                    this._operators.set(0,new operators.selections.Tournament(dimNewPop, sizeTourn));
                }
            }
            OperatorBlock=false;
            return true;
        } catch (Exception ex) {
            //Devolve false em caso de erro
            return false;
        }
    }

    @Override
    public boolean SetMutation(String parms) {
        if(_operators == null) _operators = new ArrayList<Operator>(4);
        try {
            String tipoMutacao = parms.split(" ")[0];
            double probl = 0.0;
            //Verifica se existe parametros para o operador
            try {
                probl = Double.parseDouble(parms.split(" ")[1]);
            } catch (Exception ex) {
            };
            //Verifica se é o operador SwapGenes
            if (tipoMutacao.contains("SwapGenes")) {
                System.out.println("MUTATION: SWAPGENES");
                //Verifica se existe probabilidade definida para o construtor do operador ou não
                if (probl == 0.0) {
                    this._operators.set(2,new SwapGenes());
                } else {
                    this._operators.set(2,new SwapGenes(probl));
                }
            }//Verifica se é o operador Invertion 
            else if (tipoMutacao.contains("Invertion")) {
                System.out.println("MUTATION: INVERTION");
                //Verifica se existe probabilidade definida para o construtor do operador ou não
                if (probl == 0.0) {
                    this._operators.set(2,new Invertion());
                } else {
                    this._operators.set(2,new Invertion(probl));
                }
            } //Verifica se é o operador Flipbit 
            else if (tipoMutacao.contains("Flipbit")) {
                System.out.println("------------------------");
                System.out.println("MUTATION: FLIPBIT");
                //Verifica se existe probabilidade definida para o construtor do operador ou não
                if (probl == 0.0) {
                    this._operators.set(2,new Flipbit());
                } else {
                    this._operators.set(2,new Flipbit(probl));
                    System.out.println("Probabilidade: " + probl);
                    System.out.println("------------------------");
                }
            }
            OperatorBlock=false;
            //devolve true - Tudo correu bem
            return true;
        } catch (Exception ex) {
            //Devolve false já que algo correu mal
            return false;
        }
    }

    @Override
    public boolean SetRecombination(String parms) {
        if(_operators == null) _operators = new ArrayList<Operator>(4);
        try {
            String tipoRecomb = parms.split(" ")[0];
            double probl = 0.0;
            //Verifica se existem parametros para o operador
            try {
                probl = Double.parseDouble(parms.split(" ")[1]);
            } catch (Exception ex) {
            };
            //Verifica se é um operador do tipo Crossover
            if (tipoRecomb.contains(".Crossover")) {
                System.out.println("RECOMBINATION: CROSSOVER");
                //Verifica se existem parametros para o operador
                if (probl == 0.0) {
                    this._operators.set(1,new Crossover());
                } else {
                    int numCuts = Integer.parseInt(parms.split(" ")[2]);
                    this._operators.set(1,new Crossover(probl, numCuts));
                }
            }//Verifica se é um operador do tipo OrderCrossover
            else if (tipoRecomb.contains("OrderCrossover")) {
                System.out.println("RECOMBINATION: ORDERCROSSOVER");
                //Verifica se existem parametros para o operador
                if (probl == 0.0) {
                    this._operators.set(1,new OrderCrossover());
                } else {
                    this._operators.set(1,new OrderCrossover(probl));
                }
            } //Verifica se é um operador do tipo PMX
            else if (tipoRecomb.contains("PMX")) {
                System.out.println("RECOMBINATION: PMX");
                //Verifica se existem parametros para o operador
                if (probl == 0.0) {
                    this._operators.set(1,new PMX());
                } else {
                    this._operators.set(1,new PMX(probl));
                }
            } //Verifica se é um operador do tipo UniformCrossover
            else if (tipoRecomb.contains(".UniformCrossover")) {
                System.out.println("------------------------");
                System.out.println("RECOMBINATION: UNIFORMCROSSOVER");
                //Verifica se existem parametros para o operador
                if (probl == 0.0) {
                    this._operators.set(1,new UniformCrossover());
                } else {
                    this._operators.set(1,new UniformCrossover(probl));
                    System.out.println("Probabil Recombination: " + probl);
                    System.out.println("------------------------");
                }
            }
            OperatorBlock=false;
            //Devolve true - tudo correu bem na definição do operador
            return true;
        } catch (Exception ex) {
            //Algo correu mal com a definição do operador - devolve false
            return false;
        }
    }

    @Override
    public boolean SetReplacement(String parms) {
        if(_operators == null) _operators = new ArrayList<Operator>(4);
        try {
            String tipoReplac = parms.split(" ")[0];
            int dimNewPop = 0;
            //Verifica se existem parametros para o operador
            try {
                dimNewPop = Integer.parseInt(parms.split(" ")[1]);
            } catch (Exception ex) {
            };
            //Verifica se é um operador do tipo Tournament
            if (tipoReplac.contains("Tournament")) {
                System.out.println("------------------------");
                System.out.println("REPLACEMENT: TOURNAMENT");
                //Verifica se existem parametros para o operador
                if (dimNewPop == 0) {
                    this._operators.set(3,new operators.replacements.Tournament());
                } else {
                    int sizeTourn = Integer.parseInt(parms.split(" ")[1]);
                    this._operators.set(3,new operators.replacements.Tournament(sizeTourn));
                    System.out.println("Size Tournament: " + sizeTourn);
                    System.out.println("------------------------");
                }
            }//Verifica se é um operador do tipo Truncation
            else if (tipoReplac.contains("Truncation")) {
                System.out.println("------------------------");
                System.out.println("REPLACEMENT: TRUNCATION");
                //Verifica se existem parametros para o operador
                this._operators.set(3,new Truncation());
            }
            OperatorBlock=false;
            //Devolve true - tudo correu bem na definição do operador
            return true;
        } catch (Exception ex) {
            //Algo correu mal com a definição do operador - devolve false
            return false;
        }
    }

    @Override
    public boolean setParameters(String parameters) {
        if(_operators == null) _operators = new ArrayList<Operator>(4);
        try {
            int dimensaoPop = Integer.parseInt(parameters.split(" ")[0]);
            int dimensaoGenoma = Integer.parseInt(parameters.split(" ")[1]);
            int dimensaoGenotype = Integer.parseInt(parameters.split(" ")[2]);
            int tamanhoAllelo = Integer.parseInt(parameters.split(" ")[3]);
            String tipoIndividuo = parameters.split(" ")[4];

            this._sizePopulation = dimensaoPop;
            this._sizeAllelo = tamanhoAllelo;
            this._sizeGenotype = dimensaoGenotype;
            this._sizeGenome = dimensaoGenoma;

            if (tipoIndividuo.contains("OnesMax")) {
                this._prototypeIndividual = new OnesMax();
            } else if (tipoIndividuo.contains("KnapSack")) {
                String data[] = parameters.split(":");
                String dataFunc[] = data[0].split(" ");
                //Penalidade do problema
                int penalty = Integer.parseInt(dataFunc[6]);
                //Modo de funcionamento do problema
                String modeFunc = dataFunc[5];
                if (modeFunc.contains("ModeFunction.RANDOM")) {
                    this._prototypeIndividual = new KnapSack(data[1], KnapSack.ModeFunction.RANDOM, penalty);
                } else if (modeFunc.contains("ModeFunction.PENALTY")) {
                    this._prototypeIndividual = new KnapSack(data[1], KnapSack.ModeFunction.PENALTY, penalty);
                } else if (modeFunc.contains("ModeFunction.PSEUDO_RANDOM")) {
                    this._prototypeIndividual = new KnapSack(data[1], KnapSack.ModeFunction.PSEUDO_RANDOM, penalty);
                }
            } else if (tipoIndividuo.contains("K50")) {
                this._prototypeIndividual = new K50();
            } else if (tipoIndividuo.contains("K100")) {
                this._prototypeIndividual = new K100();
            } else if (tipoIndividuo.contains("TSP")) {
                //Caso seja do tipo TSP, o problema será parameterizado com o SetTSPProbl
            }
            OperatorBlock=false;
            return true;
        } catch (Exception ex) {
            //Algo correu mal - devolve false
            return false;
        }
    }

    @Override
    public boolean SetStopCrit(String parms) {
        try {
            int iterac = Integer.parseInt(parms.split(" ")[0]);
            double fitness = Double.parseDouble(parms.split(" ")[1]);
            StopCriterion stopCrit = new StopCriterion(fitness,4,3600,StopCriterion.TYPE_PROBLEM_MAXIMIZATION);
            this._stopCriterion = stopCrit;
            return true;
        } catch (Exception ex) {
            //Algo correu mal - devolve false
            return false;
        }
        else return false;

    }

    @Override
    public boolean SetTSPProbl(String param) {
        try {
            this.TSP = new TSPProblem(param);
            this._prototypeIndividual = new TSP(TSP.getCostMatrix());
            return true;
        } catch (Exception ex) {
            //Algo correu mal - devolve false
            return false;
        }
    }

    @Override
    public Population getPopulation() {
        return this._parentsPopulation;
    }

    @Override
    public boolean setPopulation(Population p) {
        if (p != null) {
            this._parentsPopulation = p;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void StopSolver() {
        this.Stop = true;
    }
    
    @Override
    public int getCurrentItera(){
        return _numberIteractions;
    }
}
