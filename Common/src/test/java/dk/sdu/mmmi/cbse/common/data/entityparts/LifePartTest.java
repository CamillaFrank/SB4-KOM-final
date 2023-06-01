package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LifePartTest {
    GameData gameData;
    Entity entity;

    @BeforeEach
    void setup(){
        this.gameData = mock(GameData.class);
        this.entity = mock(Entity.class);
    }

    @Test
    void processNotHitNotDead() {
        LifePart lifePart = new LifePart(10);

        lifePart.process(gameData, entity);

        assertEquals(10, lifePart.getLife());

        assertFalse(lifePart.isDead());
        assertFalse(lifePart.isHit());
    }

    @Test
    void processIsHitNotDead() {
        LifePart lifePart = new LifePart(10);
        lifePart.setIsHit(true);

        assertTrue(lifePart.isHit());

        lifePart.process(gameData, entity);

        assertEquals(9, lifePart.getLife());

        assertFalse(lifePart.isDead());
        assertFalse(lifePart.isHit());
    }

    @Test
    void processIsHitDies() {
        LifePart lifePart = new LifePart(1);
        lifePart.setIsHit(true);

        assertTrue(lifePart.isHit());

        lifePart.process(gameData, entity);

        assertEquals(0, lifePart.getLife());

        assertTrue(lifePart.isDead());
        assertFalse(lifePart.isHit());
    }
}