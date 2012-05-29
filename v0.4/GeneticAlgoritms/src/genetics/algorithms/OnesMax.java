package genetics.algorithms;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;

public class OnesMax extends Individual {

    public OnesMax() {
        super();
    }

    public OnesMax(OnesMax newOnesMax) {
        super(newOnesMax);
    }

    public OnesMax(Individual newIndividual) {
        super(newIndividual);
    }

    @Override
    public double fitness() {
        int __numberOfOnes = 0;
        for (Chromosome __chromosome : this) {
            for (Gene<Boolean[]> __gene : __chromosome) {
                for (int __indexAlleloValue = 0; __indexAlleloValue < __gene.getAllele().length; __indexAlleloValue++) {
                    if (__gene.getAllele()[__indexAlleloValue]) {
                        __numberOfOnes++;
                    }
                }
            }
        }
        return __numberOfOnes;
    }

    @Override
    public Boolean[] inicializationAllelo(int indexGene) {
        Boolean[] __allelo = new Boolean[super.getSizeAllelo()];

        // gerar de forma aleatoria os valores em binario para o allelo
        for (int __indexAllelo = 0; __indexAllelo < super.getSizeAllelo(); __indexAllelo++) {
            __allelo[__indexAllelo] = Population.RANDOM_GENERATOR.nextBoolean();
        }

        return __allelo;
    }

    @Override
    public Individual clone() {
        return new OnesMax(this);
    }
    
    @Override
    public String getInfo() {
        return "<p>Solver para o OnesMax: setParameters(«num Individuos» «num Cromossomas» «num Genes Cromossoma» «tamanho array allelo» OnesMax)</p>"
                +"<p>Ex: setParameters(1000 1 1 10 OnesMax) - É criado um solver que terá uma </p><p>população inicial de 1000 individuos, cada um com um cromossoma, cada cromossoma</p><p>com um gene e cada gene contem um array de tamanho 10. Os individuos são do tipo</p><p>OnesMax.</p>";
    }

    @Override
    public boolean setParameters(String parameters) {
        return false;
    }
}
