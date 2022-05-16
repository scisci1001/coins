package simplesplit;

public class MinimumFive extends MinimumOne{

    @Override
    public int getMaxValue(){
        return super.getMaxValue()/5;
    }

    @Override
    public CoinSystem format(CoinSystem coinSystem){
         coinSystem.multiplyBy(5);
         return coinSystem;
    }

    @Override
    public String getName() {
        return " - Minimum 5";
    }

}
