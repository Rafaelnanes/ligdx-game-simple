package test.simple;

import com.mygdx.game.player.OldStateMachine;
import com.mygdx.game.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerStateTest {

  @Test
  public void playerDead() {
    final Player player = new Player();
    final OldStateMachine stateMachine = player.getStateMachine();
    stateMachine.changeToGetHit();
    stateMachine.changeToShoot();
    stateMachine.changeToGetHit();
    stateMachine.changeToShoot();
    stateMachine.changeToRun();
    stateMachine.changeToShoot();
    stateMachine.changeToShoot();
    stateMachine.changeToGetHit();
    stateMachine.changeToShoot();
    stateMachine.changeToGetHit();
    stateMachine.changeToShoot();
    stateMachine.changeToShoot();

    Assertions.assertEquals(0, player.getHealth());
    Assertions.assertEquals(8, player.getBullets());
  }

}
