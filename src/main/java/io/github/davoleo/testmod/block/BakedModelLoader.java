/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 25/05/2020 / 00:34
 * Class: BakedModelLoader
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.block;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

import javax.annotation.Nonnull;

public class BakedModelLoader implements IModelLoader<BakedModelGeometry> {

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {

    }

    @Nonnull
    @Override
    public BakedModelGeometry read(@Nonnull JsonDeserializationContext deserializationContext, @Nonnull JsonObject modelContents) {
        return new BakedModelGeometry();
    }
}
