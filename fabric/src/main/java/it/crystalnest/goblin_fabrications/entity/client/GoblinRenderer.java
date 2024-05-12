package it.crystalnest.goblin_fabrications.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.entity.custom.GoblinEntity;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GoblinRenderer extends GeoEntityRenderer<GoblinEntity> {
    public GoblinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GoblinModel());
        addRenderLayer(new HumanoidArmorLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GoblinEntity animatable) {
        return new ResourceLocation(Constants.MOD_ID, "textures/entity/goblin.png");
    }

    @Override
    public void render(GoblinEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        addLayer(new HumanoidArmorLayer(this, new SkeletonModel(context.bakeLayer(modelLayerLocation2)), new SkeletonModel(context.bakeLayer(modelLayerLocation3)), context.getModelManager()));

    }
}
