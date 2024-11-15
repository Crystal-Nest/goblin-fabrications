package it.crystalnest.goblin_fabrications.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.entity.custom.GoblinEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GoblinRenderer extends GeoEntityRenderer<GoblinEntity> {
  public GoblinRenderer(EntityRendererProvider.Context renderManager) {
    super(renderManager, new GoblinModel());
    this.shadowRadius = 0.5f;
    //addRenderLayer(new HumanoidArmorLayer(this));
  }

  @Override
  public @NotNull ResourceLocation getTextureLocation(@NotNull GoblinEntity animatable) {
    return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/goblin.png");
  }

  @Override
  public void render(@NotNull GoblinEntity entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
    super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    //addLayer(new HumanoidArmorLayer(this, new SkeletonModel(context.bakeLayer(modelLayerLocation2)), new SkeletonModel(context.bakeLayer(modelLayerLocation3)), context.getModelManager()));
  }
}
