/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 06/03/2020 / 16:08
 * Class: BakedBlockModel
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.block;

import com.google.common.collect.ImmutableList;
import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.tileentity.BakedBlockTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BakedBlockModel implements IDynamicBakedModel {

    private TextureAtlasSprite getTexture() {
        ResourceLocation name = new ResourceLocation(TestMod.MODID, "block/baked_block");
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(name);
    }

    /**
     * @param builder builds the quads corresponding to each face of our cubic model
     * @param normal represents the vector normal to the face of the cubic model (which face?) [used for lighting??]
     * @param x coordinate on the X Axis of the starting point of the vector
     * @param y coordinate on the Y Axis of the starting point of the vector
     * @param z coordinate on the Z Axis of the starting point of the vector
     * @param u coordinate on the X Axis of the texture
     * @param v coordinate on the Y Axis of the texture
     * @param sprite our texture position on the big Atlas Texture
     * @param r red color tint
     * @param g green color tint
     * @param b blue color tint
     */
    private void addVertex(BakedQuadBuilder builder, Vec3d normal,
                           double x, double y, double z, float u, float v,
                           TextureAtlasSprite sprite, float r, float g, float b)
    {
        ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements();

        for (int e = 0; e < elements.size(); e++) {
            switch (elements.get(e).getUsage()) {
                case POSITION:
                    builder.put(e, (float) x, (float) y, (float) z, 1.0F);
                    break;
                case COLOR:
                    builder.put(e, r, g, b, 1.0F);
                    break;
                case UV:
                    switch (elements.get(e).getIndex()) {
                        case 0:
                            float iu = sprite.getInterpolatedU(u);
                            float iv = sprite.getInterpolatedV(v);
                            builder.put(e, iu, iv);
                            break;
                        case 2:
                            builder.put(e, 0f, 1f);
                            break;
                        default:
                            builder.put(e);
                            break;
                    }
                    break;
                case NORMAL:
                    builder.put(e, (float) normal.x, (float) normal.y, (float) normal.z, 0F);
                    break;
                default:
                    builder.put(e);
                    break;
            }
        }
    }

    /**
     * @param v1,v2,v3,v4 the four vector representing the four corners of the quad
     * @param sprite the texture sprite
     * @return a baked quad
     */
    private BakedQuad createQuad(Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4, TextureAtlasSprite sprite) {
        Vec3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();

        BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
        builder.setTexture(sprite);
        builder.setQuadOrientation(Direction.getFacingFromVector(normal.x, normal.y, normal.z));
        addVertex(builder, normal, v1.x, v1.y, v1.z, 0, 0, sprite, 1F, 1F, 1F);
        addVertex(builder, normal, v2.x, v2.y, v2.z, 0, 16, sprite, 1F, 1F, 1F);
        addVertex(builder, normal, v3.x, v3.y, v3.z, 16, 16, sprite, 1F, 1F, 1F);
        addVertex(builder, normal, v4.x, v4.y, v4.z, 16, 0, sprite, 1F, 1F, 1F);
        return builder.build();
    }

    private static Vec3d vector(double x, double y, double z) {
        return new Vec3d(x, y, z);
    }

    //Called 7 times! - one time for each side, and one time in general
    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {

        BlockState mimic = extraData.getData(BakedBlockTileEntity.MIMIC);
        if (mimic != null) {
            ModelResourceLocation location = BlockModelShapes.getModelLocation(mimic);
            if (location != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    return model.getQuads(mimic, side, rand, extraData);
                }
            }
        }

        //When it's called in general we return an empty list
        //We don't need to do "visibility calling because our model is smaller than a full block"
        if (side != null) {
            return Collections.emptyList();
        }

        TextureAtlasSprite texture = getTexture();
        List<BakedQuad> quads = new ArrayList<>();
        double l = 0.2;
        double r = 1 - 0.2;
        quads.add(createQuad(vector(l, r, l), vector(l, r, r), vector(r, r, r), vector(r, r, l), texture));
        quads.add(createQuad(vector(l, l, l), vector(r, l, l), vector(r, l, r), vector(l, l, r), texture));
        quads.add(createQuad(vector(r, r, r), vector(r, l, r), vector(r, l, l), vector(r, r, l), texture));
        quads.add(createQuad(vector(l, r, l), vector(l, l, l), vector(l, l, r), vector(l, r, r), texture));
        quads.add(createQuad(vector(r, r, l), vector(r, l, l), vector(l, l, l), vector(l, r, l), texture));
        quads.add(createQuad(vector(l, r, r), vector(l, l, r), vector(r, l, r), vector(r, r, r), texture));

        return quads;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean func_230044_c_() {
        return true;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Nonnull
    @Override
    public TextureAtlasSprite getParticleTexture() {
        return getTexture();
    }

    @Nonnull
    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }

    @Nonnull
    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }
}
