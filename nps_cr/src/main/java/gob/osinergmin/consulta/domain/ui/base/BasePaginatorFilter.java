/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.ui.base;

/**
 *
 * @author l_garcia_r
 */
public class BasePaginatorFilter {
    private Integer startIndex; // inicio de filas mostradas
    private Integer resultsNumber; // cantidad de filas mostradas

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getResultsNumber() {
        return resultsNumber;
    }

    public void setResultsNumber(Integer resultsNumber) {
        this.resultsNumber = resultsNumber;
    }
    
}
