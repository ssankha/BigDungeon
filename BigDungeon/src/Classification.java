
/**
 *
 *  This class is used to classify each Entity. Rather than using a boolean variable for each class that tells what entity it is, I found that it would be much easier to use anenumeration of each Entity.
 *
 *  @author  Shiv Sankhavaram
 *  @version May 25, 2020
 *  @author  Period: 5
 *  @author  Assignment: BigDungeon
 *
 *  @author  Sources: None
 */
public enum Classification
{
    Player(),
    Monster(),
    HardMonster(),
    Bullet(),
    Wall(),
    HealthPotion(),
    Coin(),
    Arrow(),
    Bomb();

}