/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 */
package international.astro.mixins.mixin.net.minecraft.client.renderer;

import international.astro.Astro;
import international.astro.hack.hacks.render.Animations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = {ItemRenderer.class})
public abstract class MixinItemRenderer {
    @Shadow
    public ItemStack itemStackOffHand;
    protected Minecraft mc = Minecraft.getMinecraft();

    @Shadow
    protected abstract void renderMapFirstPerson(float var1, float var2, float var3);

    @Shadow
    protected abstract void renderMapFirstPersonSide(float var1, EnumHandSide var2, float var3, ItemStack var4);

    @Shadow
    protected abstract void renderArmFirstPerson(float var1, float var2, EnumHandSide var3);

    @Shadow
    protected abstract void transformSideFirstPerson(EnumHandSide var1, float var2);

    @Shadow
    protected abstract void transformEatFirstPerson(float var1, EnumHandSide var2, ItemStack var3);

    @Shadow
    public abstract void renderItemSide(EntityLivingBase var1, ItemStack var2, ItemCameraTransforms.TransformType var3, boolean var4);

    @Overwrite
    public void renderItemInFirstPerson(AbstractClientPlayer p_renderItemInFirstPerson_1_, float p_renderItemInFirstPerson_2_, float p_renderItemInFirstPerson_3_, EnumHand p_renderItemInFirstPerson_4_, float p_renderItemInFirstPerson_5_, ItemStack p_renderItemInFirstPerson_6_, float p_renderItemInFirstPerson_7_) {
        boolean flag = p_renderItemInFirstPerson_4_ == EnumHand.MAIN_HAND;
        EnumHandSide enumhandside = flag ? p_renderItemInFirstPerson_1_.getPrimaryHand() : p_renderItemInFirstPerson_1_.getPrimaryHand().opposite();
        GlStateManager.pushMatrix();
        if (p_renderItemInFirstPerson_6_.isEmpty()) {
            if (flag && !p_renderItemInFirstPerson_1_.isInvisible()) {
                this.renderArmFirstPerson(p_renderItemInFirstPerson_7_, p_renderItemInFirstPerson_5_, enumhandside);
            }
        } else if (p_renderItemInFirstPerson_6_.getItem() instanceof ItemMap) {
            if (flag && this.itemStackOffHand.isEmpty()) {
                this.renderMapFirstPerson(p_renderItemInFirstPerson_3_, p_renderItemInFirstPerson_7_, p_renderItemInFirstPerson_5_);
            } else {
                this.renderMapFirstPersonSide(p_renderItemInFirstPerson_7_, enumhandside, p_renderItemInFirstPerson_5_, p_renderItemInFirstPerson_6_);
            }
        } else {
            boolean flag1;
            boolean bl = flag1 = enumhandside == EnumHandSide.RIGHT;
            if (p_renderItemInFirstPerson_1_.isHandActive() && p_renderItemInFirstPerson_1_.getItemInUseCount() > 0 && p_renderItemInFirstPerson_1_.getActiveHand() == p_renderItemInFirstPerson_4_) {
                int j = flag1 ? 1 : -1;
                switch (p_renderItemInFirstPerson_6_.getItemUseAction()) {
                    case NONE: {
                        this.transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        break;
                    }
                    case EAT:
                    case DRINK: {
                        this.transformEatFirstPerson(p_renderItemInFirstPerson_2_, enumhandside, p_renderItemInFirstPerson_6_);
                        this.transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        break;
                    }
                    case BLOCK: {
                        this.transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        break;
                    }
                    case BOW: {
                        this.transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        GlStateManager.translate((float) ((float) j * -0.2785682f), (float) 0.18344387f, (float) 0.15731531f);
                        GlStateManager.rotate((float) -13.935f, (float) 1.0f, (float) 0.0f, (float) 0.0f);
                        GlStateManager.rotate((float) ((float) j * 35.3f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
                        GlStateManager.rotate((float) ((float) j * -9.785f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
                        float f5 = (float) p_renderItemInFirstPerson_6_.getMaxItemUseDuration() - ((float) this.mc.player.getItemInUseCount() - p_renderItemInFirstPerson_2_ + 1.0f);
                        float f6 = f5 / 20.0f;
                        f6 = (f6 * f6 + f6 * 2.0f) / 3.0f;
                        if (f6 > 1.0f) {
                            f6 = 1.0f;
                        }
                        if (f6 > 0.1f) {
                            float f7 = MathHelper.sin((float) ((f5 - 0.1f) * 1.3f));
                            float f3 = f6 - 0.1f;
                            float f4 = f7 * f3;
                            GlStateManager.translate((float) (f4 * 0.0f), (float) (f4 * 0.004f), (float) (f4 * 0.0f));
                        }
                        GlStateManager.translate((float) (f6 * 0.0f), (float) (f6 * 0.0f), (float) (f6 * 0.04f));
                        GlStateManager.scale((float) 1.0f, (float) 1.0f, (float) (1.0f + f6 * 0.2f));
                        GlStateManager.rotate((float) ((float) j * 45.0f), (float) 0.0f, (float) -1.0f, (float) 0.0f);
                    }
                }
            } else {
                float f = -0.4f * MathHelper.sin((float) (MathHelper.sqrt((float) p_renderItemInFirstPerson_5_) * (float) Math.PI));
                float f5 = 0.2f * MathHelper.sin((float) (MathHelper.sqrt((float) p_renderItemInFirstPerson_5_) * ((float) Math.PI * 2)));
                float f6 = -0.2f * MathHelper.sin((float) (p_renderItemInFirstPerson_5_ * (float) Math.PI));
                int i = flag1 ? 1 : -1;
                float swingprogress = this.mc.player.getSwingProgress(this.mc.getRenderPartialTicks());
                if (Astro.hackManager.getHack("Animations").isEnabled()) {
                    if (enumhandside != EnumHandSide.LEFT) {
                        if (Animations.getInstance.mode.isMode("Dortware")) {
                            GlStateManager.translate((float) 0.56f, (float) -0.5f, (float) -0.71999997f);
                            GlStateManager.translate((float) 0.0f, (float) 0.0f, (float) 0.0f);
                            GlStateManager.rotate((float) 45.0f, (float) 0.0f, (float) 1.0f, (float) 0.0f);
                            float var11 = MathHelper.sin((float) (swingprogress * swingprogress * (float) Math.PI));
                            float var12 = MathHelper.sin((float) (MathHelper.sqrt((float) swingprogress) * (float) Math.PI));
                            GlStateManager.rotate((float) (var11 * 0.0f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
                            GlStateManager.rotate((float) (var12 * 0.0f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
                            GlStateManager.rotate((float) (var12 * -90.0f), (float) 1.0f, (float) 0.0f, (float) 0.0f);
                        }
                    } else {
                        GlStateManager.translate((float) ((float) i * f), (float) f5, (float) f6);
                        this.transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        this.transformFirstPerson(enumhandside, p_renderItemInFirstPerson_5_);
                    }
                } else {
                    GlStateManager.translate((float) ((float) i * f), (float) f5, (float) f6);
                    this.transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                    this.transformFirstPerson(enumhandside, p_renderItemInFirstPerson_5_);
                }
            }
            this.renderItemSide((EntityLivingBase) p_renderItemInFirstPerson_1_, p_renderItemInFirstPerson_6_, flag1 ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, !flag1);
        }
        GlStateManager.popMatrix();
    }

    @Overwrite
    private void transformFirstPerson(EnumHandSide hand, float p_187453_2_) {
        float angle = System.currentTimeMillis() / 3L % 360L;
        int i = hand == EnumHandSide.RIGHT ? 1 : -1;
        float f = MathHelper.sin((float) (p_187453_2_ * p_187453_2_ * (float) Math.PI));
        GlStateManager.rotate((float) ((float) i * (45.0f + f * -20.0f)), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        float f1 = MathHelper.sin((float) (MathHelper.sqrt((float) p_187453_2_) * (float) Math.PI));
        GlStateManager.rotate((float) ((float) i * f1 * -20.0f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
        GlStateManager.rotate((float) (f1 * -80.0f), (float) 1.0f, (float) 0.0f, (float) 0.0f);
        GlStateManager.rotate((float) ((float) i * -45.0f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
    }
}

