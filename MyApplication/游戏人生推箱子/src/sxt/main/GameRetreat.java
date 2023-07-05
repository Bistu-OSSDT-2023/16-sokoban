package sxt.main;

/**
 * @author
 * @create
 */
public class GameRetreat {
    GameBox gameBox;
    GamePeople gamePeople;

    public GameRetreat() {
        gameBox = new GameBox();
        gamePeople = new GamePeople();
    }

    /**
     * 小人回退
     */
    public int stepBack(){
        //如果集合为空  结束方法
        if (gamePeople.behavior.size() == 0) return 0;

        int ber = (int) gamePeople.behavior.get(gamePeople.behavior.size() - 1);
        System.out.println("stepBack");

        switch (ber){
            case 1:
                gamePeople.y+=50;
                gamePeople.behavior.remove(gamePeople.behavior.size()-1);
                break;
            case 2:
                gamePeople.y-=50;
                gamePeople.behavior.remove(gamePeople.behavior.size()-1);
                break;
            case 3:
                gamePeople.x+=50;
                gamePeople.behavior.remove(gamePeople.behavior.size()-1);
                break;
            case 4:
                gamePeople.x-=50;
                gamePeople.behavior.remove(gamePeople.behavior.size()-1);
                break;
        }


        return 0;
    }

}
