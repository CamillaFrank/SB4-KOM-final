package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LifePartTest {

    @Test
    void processNotHitNotDead() {
        LifePart lifePart = new LifePart(10);

        lifePart.process(mock(GameData.class), mock(Entity.class));

        assertEquals(10, lifePart.getLife());

        assertFalse(lifePart.isDead());
        assertFalse(lifePart.isHit());
    }

    @Test
    void processIsHitNotDead() {
        LifePart lifePart = new LifePart(10);
        lifePart.setIsHit(true);

        assertTrue(lifePart.isHit());

        lifePart.process(mock(GameData.class), mock(Entity.class));

        assertEquals(9, lifePart.getLife());

        assertFalse(lifePart.isDead());
        assertFalse(lifePart.isHit());
    }

    @Test
    void processIsHitDies() {
        LifePart lifePart = new LifePart(1);
        lifePart.setIsHit(true);

        assertTrue(lifePart.isHit());

        lifePart.process(mock(GameData.class), mock(Entity.class));

        assertEquals(0, lifePart.getLife());

        assertTrue(lifePart.isDead());
        assertFalse(lifePart.isHit());
    }
}