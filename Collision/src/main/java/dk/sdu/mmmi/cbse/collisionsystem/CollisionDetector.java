package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                LifePart entityLife = entity1.getPart(LifePart.class);

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // find distance between the two entities
                PositionPart entMov1 = entity1.getPart(PositionPart.class);
                PositionPart entMov2 = entity2.getPart(PositionPart.class);
                float dx = (float) entMov1.getX() - (float) entMov2.getX();
                float dy = (float) entMov1.getY() - (float) entMov2.getY();
                float distance = (float) Math.sqrt(dx * dx + dy * dy);

                if (distance < (entity1.getRadius() + entity2.getRadius())) {
                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setIsHit(true);

                        // remove entity if it is out of life
                        if (entityLife.getLife() <= 0) {
                            world.removeEntity(entity1);
                        }
                    }
                }
            }
        }
    }

}
