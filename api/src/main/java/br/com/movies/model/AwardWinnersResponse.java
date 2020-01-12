package br.com.movies.model;

public class AwardWinnersResponse {

    private AwardWinner min;
    private AwardWinner max;

    public AwardWinner getMin() {
        return min;
    }

    public void setMin(AwardWinner min) {
        this.min = min;
    }

    public AwardWinner getMax() {
        return max;
    }

    public void setMax(AwardWinner max) {
        this.max = max;
    }
}
