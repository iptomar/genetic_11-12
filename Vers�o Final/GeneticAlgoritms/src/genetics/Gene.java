package genetics;

import java.io.Serializable;

/**
 * Classe que representa um gene. Tem um objecto T do tipo allele, que é o valor
 * guardado dentro do gene.
 * @author goncalo
 * @param <T> - Valor genérico a ser guardado dentro do allelo do individuo 
 */
public class Gene<T> implements Serializable{

    /**
     * Variavél allele que guardará qualquer valor para o allelo do invididuo
     */
    private T _allele;
    
    /**
     * Construtor da classe onde é passado o valor a ser guardado no allelo do individuo
     * @param valueAllelo - Valor a ser guardado no allelo do individuo (valor genérico)
     */
    public Gene(T valueAllelo) {
        this._allele = valueAllelo;
    }
    
    /**
     * Criação de um novo gene.
     * É criado um array de booleans com o tamanho do allelo recebido, sendo efectuado um cópia do mesmo
     * para este allelo.
     * @param newGene - Allelo a ser copiado para esta instância da classe
     */
    public Gene(Gene<T> newGene){
        //Copia para dentro deste _allele o valor do allelo recebido no parametro
        if(newGene.getAllele() instanceof Boolean[]){
            int __sizeAllelo = ((Boolean[])newGene.getAllele()).length;
            Boolean[] __newAllelo = new Boolean[__sizeAllelo];           
            for(int __indexAlleloValue = 0; __indexAlleloValue < __sizeAllelo; __indexAlleloValue++) {
                __newAllelo[__indexAlleloValue] = ((Boolean[])newGene.getAllele())[__indexAlleloValue];
            }            
            this._allele = (T)__newAllelo;
        }else{
            this._allele = newGene.getAllele();
        }
    }

    /**
     * Método que devolve o valor definido no allelo da classe
     * @return (<T>) - Valor definido na variavel _allele
     */
    public T getAllele() {
        return _allele;
    }

    /**
     * Método que permite fazer a definição do valor da variavel _allele da classe.
     * @param allele (<T>) - Valor a ser definido na variavel _allele (valor genérico)
     */
    public void setAllele(T allele) {
        this._allele = allele;
    }
    
    /**
     * Método que devolve uma string ao utilizador com as informações referentes aos valores que estão
     * guardados na variavel _allele.
     * @return (String) - String que contem informação sobre o(s) valor(es) guardado(s) na variavel _allele.
     */
    @Override
    public String toString() {
        StringBuilder __output = new StringBuilder();
        // se for uma instancia/allelo do tipo Boolean[] entao mostramos o valor da seguinte forma
        if(this._allele instanceof Boolean[]){
            Boolean[] __allelo = (Boolean[])this._allele;
            for (int __indexAlleloValue = 0; __indexAlleloValue < __allelo.length; __indexAlleloValue++) {
                __output.append(__allelo[__indexAlleloValue] ? " 1 " : " 0 ");
            }
        } else if(this._allele instanceof Double){
            Double __allelo = (Double)this._allele;
            __output.append(__allelo);
        } else {
            /**
             * ********************************************************************************
             * *** Só retorna informações do _allele caso o mesmo seja um array de booleans ***
             * ********************************************************************************
             */
            __output.append("Não esta definido ainda!!!");
        }
        return __output.toString();
    }
}
