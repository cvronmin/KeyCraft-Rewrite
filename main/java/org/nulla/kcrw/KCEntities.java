package org.nulla.kcrw;

import org.nulla.kcrw.entity.EntityBaseball;

import net.minecraftforge.fml.common.registry.EntityRegistry;

public class KCEntities {
	
    public static void InitEntities() {
    	int modID = 1;
    	EntityRegistry.registerModEntity(EntityBaseball.class, "baseball", modID++, KeyCraft_Rewrite.class, 128, 1, true);
    }

}