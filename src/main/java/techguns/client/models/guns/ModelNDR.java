package techguns.client.models.guns;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.Entity;
import techguns.client.models.ModelMultipart;
import techguns.client.render.TGRenderHelper;
import techguns.client.render.TGRenderHelper.RenderType;
import techguns.util.MathUtil;

public class ModelNDR extends ModelMultipart {
    public ModelRenderer shape14;
    public ModelRenderer shape15;
    public ModelRenderer shape14_1;
    public ModelRenderer shape14_2;
    public ModelRenderer GLOW;
    public ModelRenderer shape14_3;
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer sixside;
    public ModelRenderer shape14_4;
    public ModelRenderer shape14_5;
    public ModelRenderer shape14_6;
    public ModelRenderer shape14_7;
    public ModelRenderer shape14_8;
    public ModelRenderer shape14_9;
    public ModelRenderer shape14_10;
    public ModelRenderer shape84;
    public ModelRenderer shape84_1;
    public ModelRenderer shape84_2;
    public ModelRenderer shape84_3;
    public ModelRenderer shape84_4;
    public ModelRenderer shape84_5;
    public ModelRenderer shape84_6;
    public ModelRenderer shape84_7;
    public ModelRenderer shape99;
    public ModelRenderer shape99_1;
    public ModelRenderer shape99_2;
    public ModelRenderer shape1_;
    public ModelRenderer shape1__1;
    public ModelRenderer shape1__2;
    public ModelRenderer shape1__3;
    public ModelRenderer shape1__4;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;
    public ModelRenderer shape1_8;
    public ModelRenderer shape1_9;
    public ModelRenderer shape1_10;
    public ModelRenderer shape1_11;
    public ModelRenderer sixside_1;
    public ModelRenderer shape14_11;
    public ModelRenderer shape14_12;
    public ModelRenderer shape1__5;
    public ModelRenderer shape1__6;
    public ModelRenderer shape1__7;
    public ModelRenderer shape1__8;
    public ModelRenderer shape1__9;
    public ModelRenderer shape45_;
    public ModelRenderer shape45;
    public ModelRenderer shape45_1;
    public ModelRenderer shape48;
    public ModelRenderer shape48_1;
    public ModelRenderer shape48_2;
    public ModelRenderer shape58;
    public ModelRenderer shape58_1;
    public ModelRenderer shape58_2;
    public ModelRenderer shape59;
    public ModelRenderer shape60;
    public ModelRenderer shape59_1;
    public ModelRenderer shape60_1;
    public ModelRenderer shape82;
    public ModelRenderer shape83;
    public ModelRenderer shape59_2;
    public ModelRenderer shape60_2;
    public ModelRenderer glowA1;

    public ModelNDR() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape14_1 = new ModelRenderer(this, 54, 15);
        this.shape14_1.setRotationPoint(3.0F, -3.0F, -2.0F);
        this.shape14_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.shape1_8 = new ModelRenderer(this, 52, 10);
        this.shape1_8.setRotationPoint(4.0F, -2.6F, -1.5F);
        this.shape1_8.addBox(0.0F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
        this.setRotation(shape1_8, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape45_1 = new ModelRenderer(this, 0, 55);
        this.shape45_1.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.shape45_1.addBox(0.0F, 0.0F, 0.0F, 8, 3, 6, 0.0F);
        this.setRotation(shape45_1, -1.0471975511965976F, 0.0F, 0.0F);
        this.shape1__9 = new ModelRenderer(this, 0, 0);
        this.shape1__9.setRotationPoint(0.0F, 8.66F, 0.0F);
        this.shape1__9.addBox(0.0F, -3.0F, 0.0F, 3, 3, 5, 0.0F);
        this.GLOW = new ModelRenderer(this, 42, 45);
        this.GLOW.setRotationPoint(-4.0F, -2.0F, -2.0F);
        this.GLOW.addBox(0.0F, 0.0F, 0.0F, 7, 4, 4, 0.0F);
        this.shape14_9 = new ModelRenderer(this, 42, 35);
        this.shape14_9.setRotationPoint(-4.0F, -3.0F, -2.0F);
        this.shape14_9.addBox(0.0F, 0.0F, 0.0F, 7, 6, 4, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 50, 0);
        this.shape1_2.setRotationPoint(7.0F, 3.46F, -2.0F);
        this.shape1_2.addBox(0.0F, -1.0F, -4.0F, 3, 1, 4, 0.0F);
        this.setRotation(shape1_2, -1.0471975511965976F, 0.0F, 0.0F);
        this.sixside = new ModelRenderer(this, 0, 0);
        this.sixside.setRotationPoint(-8.0F, -4.33F, -2.5F);
        this.sixside.addBox(0.0F, 0.0F, 0.0F, 3, 3, 5, 0.0F);
        this.shape14_3 = new ModelRenderer(this, 46, 15);
        this.shape14_3.setRotationPoint(3.0F, -2.0F, -3.0F);
        this.shape14_3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 6, 0.0F);
        this.shape99 = new ModelRenderer(this, 23, 41);
        this.shape99.setRotationPoint(-5.5F, 5.0F, -2.5F);
        this.shape99.addBox(-1.0F, -1.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotation(shape99, 0.0F, 0.0F, -0.40980330836826856F);
        this.shape14_6 = new ModelRenderer(this, 20, 12);
        this.shape14_6.setRotationPoint(-15.0F, -7.0F, -1.5F);
        this.shape14_6.addBox(0.0F, 0.0F, 0.0F, 6, 1, 3, 0.0F);
        this.shape58 = new ModelRenderer(this, 24, 4);
        this.shape58.setRotationPoint(-6.5F, 0.0F, 0.0F);
        this.shape58.addBox(0.0F, -5.33F, -1.0F, 2, 2, 2, 0.0F);
        this.shape14_5 = new ModelRenderer(this, 28, 4);
        this.shape14_5.setRotationPoint(-13.8F, -8.6F, -2.0F);
        this.shape14_5.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.setRotation(shape14_5, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape1_7 = new ModelRenderer(this, 52, 10);
        this.shape1_7.setRotationPoint(4.0F, -2.6F, 1.5F);
        this.shape1_7.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3, 0.0F);
        this.setRotation(shape1_7, -1.0471975511965976F, 0.0F, 0.0F);
        this.shape1__4 = new ModelRenderer(this, 0, 0);
        this.shape1__4.setRotationPoint(0.0F, 8.66F, 0.0F);
        this.shape1__4.addBox(0.0F, -3.0F, 0.0F, 3, 3, 5, 0.0F);
        this.shape14_12 = new ModelRenderer(this, 38, 5);
        this.shape14_12.setRotationPoint(-18.0F, -4.0F, -2.0F);
        this.shape14_12.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.shape1__3 = new ModelRenderer(this, 0, 0);
        this.shape1__3.setRotationPoint(0.0F, 8.66F, 5.0F);
        this.shape1__3.addBox(0.0F, -3.0F, 0.0F, 3, 3, 5, 0.0F);
        this.setRotation(shape1__3, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape99_2 = new ModelRenderer(this, 15, 38);
        this.shape99_2.setRotationPoint(-6.0F, 5.0F, -3.0F);
        this.shape99_2.addBox(-2.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
        this.shape45_ = new ModelRenderer(this, 0, 49);
        this.shape45_.setRotationPoint(-17.0F, -3.5F, -1.5F);
        this.shape45_.addBox(0.0F, 0.0F, 0.0F, 8, 3, 3, 0.0F);
        this.shape1__5 = new ModelRenderer(this, 0, 0);
        this.shape1__5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1__5.addBox(0.0F, 0.0F, -5.0F, 3, 3, 5, 0.0F);
        this.setRotation(shape1__5, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape1__6 = new ModelRenderer(this, 0, 0);
        this.shape1__6.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.shape1__6.addBox(0.0F, 0.0F, 0.0F, 3, 3, 5, 0.0F);
        this.setRotation(shape1__6, -1.0471975511965976F, 0.0F, 0.0F);
        this.shape59_1 = new ModelRenderer(this, 20, 0);
        this.shape59_1.setRotationPoint(1.5F, -4.83F, -0.5F);
        this.shape59_1.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.shape1__1 = new ModelRenderer(this, 0, 0);
        this.shape1__1.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.shape1__1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 5, 0.0F);
        this.setRotation(shape1__1, -1.0471975511965976F, 0.0F, 0.0F);
        this.shape59_2 = new ModelRenderer(this, 20, 0);
        this.shape59_2.setRotationPoint(1.5F, -4.83F, -0.5F);
        this.shape59_2.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.shape14_10 = new ModelRenderer(this, 46, 15);
        this.shape14_10.setRotationPoint(-5.0F, -2.0F, -3.0F);
        this.shape14_10.addBox(0.0F, 0.0F, 0.0F, 1, 4, 6, 0.0F);
        this.shape59 = new ModelRenderer(this, 20, 0);
        this.shape59.setRotationPoint(1.5F, -4.83F, -0.5F);
        this.shape59.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.glowA1 = new ModelRenderer(this, 48, 58);
        this.glowA1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.glowA1.addBox(-4.0F, -6.0F, -0.5F, 7, 5, 1, 0.0F);
        this.setRotation(glowA1, 0.4363323129985824F, 0.0F, 0.0F);
        this.shape58_1 = new ModelRenderer(this, 18, 8);
        this.shape58_1.setRotationPoint(-6.5F, 0.0F, 0.0F);
        this.shape58_1.addBox(-1.0F, -5.33F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotation(shape58_1, 2.0943951023931953F, 0.0F, 0.0F);
        this.shape84_6 = new ModelRenderer(this, 12, 25);
        this.shape84_6.setRotationPoint(-32.0F, 0.0F, -0.5F);
        this.shape84_6.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.shape84_7 = new ModelRenderer(this, 16, 25);
        this.shape84_7.setRotationPoint(-32.0F, -2.0F, -0.5F);
        this.shape84_7.addBox(0.0F, 0.0F, 0.0F, 6, 2, 1, 0.0F);
        this.shape60_1 = new ModelRenderer(this, 30, 2);
        this.shape60_1.setRotationPoint(10.5F, -4.83F, -0.5F);
        this.shape60_1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotation(shape60_1, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape1_11 = new ModelRenderer(this, 52, 10);
        this.shape1_11.setRotationPoint(4.0F, 2.6F, -1.5F);
        this.shape1_11.addBox(0.0F, -2.0F, 0.0F, 3, 2, 3, 0.0F);
        this.shape14_8 = new ModelRenderer(this, 54, 15);
        this.shape14_8.setRotationPoint(-5.0F, -3.0F, -2.0F);
        this.shape14_8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.shape14_4 = new ModelRenderer(this, 16, 16);
        this.shape14_4.setRotationPoint(-20.0F, -5.0F, -2.0F);
        this.shape14_4.addBox(0.0F, 0.0F, 0.0F, 14, 1, 4, 0.0F);
        this.shape84_2 = new ModelRenderer(this, 34, 25);
        this.shape84_2.setRotationPoint(-25.0F, -1.0F, 0.5F);
        this.shape84_2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.shape60_2 = new ModelRenderer(this, 30, 2);
        this.shape60_2.setRotationPoint(10.5F, -4.83F, -0.5F);
        this.shape60_2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotation(shape60_2, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape1_1 = new ModelRenderer(this, 50, 0);
        this.shape1_1.setRotationPoint(7.0F, -3.46F, -2.0F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.shape1_ = new ModelRenderer(this, 0, 0);
        this.shape1_.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1_.addBox(0.0F, 0.0F, -5.0F, 3, 3, 5, 0.0F);
        this.setRotation(shape1_, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape99_1 = new ModelRenderer(this, 24, 36);
        this.shape99_1.setRotationPoint(-8.5F, 3.0F, -3.0F);
        this.shape99_1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
        this.shape15 = new ModelRenderer(this, 38, 25);
        this.shape15.setRotationPoint(-4.0F, -2.0F, -3.0F);
        this.shape15.addBox(0.0F, 0.0F, 0.0F, 7, 4, 6, 0.0F);
        this.sixside_1 = new ModelRenderer(this, 0, 0);
        this.sixside_1.setRotationPoint(-21.0F, -4.33F, -2.5F);
        this.sixside_1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 5, 0.0F);
        this.shape84_5 = new ModelRenderer(this, 40, 1);
        this.shape84_5.setRotationPoint(-31.0F, 0.0F, 0.0F);
        this.shape84_5.addBox(0.0F, 0.0F, 0.0F, 5, 4, 0, 0.0F);
        this.shape14_11 = new ModelRenderer(this, 38, 5);
        this.shape14_11.setRotationPoint(-9.0F, -4.0F, -2.0F);
        this.shape14_11.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.shape1__7 = new ModelRenderer(this, 0, 0);
        this.shape1__7.setRotationPoint(0.0F, 8.66F, 0.0F);
        this.shape1__7.addBox(0.0F, -3.0F, -5.0F, 3, 3, 5, 0.0F);
        this.setRotation(shape1__7, -1.0471975511965976F, 0.0F, 0.0F);
        this.shape60 = new ModelRenderer(this, 30, 2);
        this.shape60.setRotationPoint(10.5F, -4.83F, -0.5F);
        this.shape60.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotation(shape60, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape84_3 = new ModelRenderer(this, 38, 28);
        this.shape84_3.setRotationPoint(-31.0F, 0.0F, -0.5F);
        this.shape84_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape1 = new ModelRenderer(this, 48, 5);
        this.shape1.setRotationPoint(7.0F, -3.46F, 2.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
        this.setRotation(shape1, -1.0471975511965976F, 0.0F, 0.0F);
        this.shape1_9 = new ModelRenderer(this, 52, 10);
        this.shape1_9.setRotationPoint(4.0F, -2.6F, -1.5F);
        this.shape1_9.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3, 0.0F);
        this.shape45 = new ModelRenderer(this, 0, 55);
        this.shape45.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape45.addBox(0.0F, 0.0F, -6.0F, 8, 3, 6, 0.0F);
        this.setRotation(shape45, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape14_2 = new ModelRenderer(this, 54, 15);
        this.shape14_2.setRotationPoint(3.0F, 2.0F, -2.0F);
        this.shape14_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.shape84 = new ModelRenderer(this, 34, 25);
        this.shape84.setRotationPoint(-25.0F, -1.0F, -1.5F);
        this.shape84.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 48, 5);
        this.shape1_4.setRotationPoint(7.0F, -3.46F, -2.0F);
        this.shape1_4.addBox(0.0F, 0.0F, -4.0F, 4, 1, 4, 0.0F);
        this.setRotation(shape1_4, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape1_10 = new ModelRenderer(this, 52, 10);
        this.shape1_10.setRotationPoint(4.0F, 2.6F, 1.5F);
        this.shape1_10.addBox(0.0F, -2.0F, 0.0F, 3, 2, 3, 0.0F);
        this.setRotation(shape1_10, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape48_1 = new ModelRenderer(this, 0, 49);
        this.shape48_1.setRotationPoint(0.0F, 7.79F, 4.5F);
        this.shape48_1.addBox(0.0F, -3.0F, 0.0F, 8, 3, 3, 0.0F);
        this.setRotation(shape48_1, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape84_1 = new ModelRenderer(this, 26, 25);
        this.shape84_1.setRotationPoint(-23.0F, -3.0F, -2.0F);
        this.shape84_1.addBox(0.0F, 0.0F, 0.0F, 2, 6, 4, 0.0F);
        this.shape84_4 = new ModelRenderer(this, 34, 35);
        this.shape84_4.setRotationPoint(-26.0F, -2.0F, -0.5F);
        this.shape84_4.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1, 0.0F);
        this.shape14 = new ModelRenderer(this, 54, 15);
        this.shape14.setRotationPoint(-5.0F, 2.0F, -2.0F);
        this.shape14.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.shape1__2 = new ModelRenderer(this, 0, 0);
        this.shape1__2.setRotationPoint(0.0F, 8.66F, 0.0F);
        this.shape1__2.addBox(0.0F, -3.0F, -5.0F, 3, 3, 5, 0.0F);
        this.setRotation(shape1__2, -1.0471975511965976F, 0.0F, 0.0F);
        this.shape1_6 = new ModelRenderer(this, 50, 0);
        this.shape1_6.setRotationPoint(7.0F, 3.46F, 2.0F);
        this.shape1_6.addBox(0.0F, -1.0F, 0.0F, 3, 1, 4, 0.0F);
        this.setRotation(shape1_6, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape83 = new ModelRenderer(this, 17, 29);
        this.shape83.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.shape83.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotation(shape83, 0.0F, 0.7853981633974483F, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 48, 5);
        this.shape1_3.setRotationPoint(7.0F, 3.46F, -2.0F);
        this.shape1_3.addBox(0.0F, -1.0F, 0.0F, 4, 1, 4, 0.0F);
        this.shape1__8 = new ModelRenderer(this, 0, 0);
        this.shape1__8.setRotationPoint(0.0F, 8.66F, 5.0F);
        this.shape1__8.addBox(0.0F, -3.0F, 0.0F, 3, 3, 5, 0.0F);
        this.setRotation(shape1__8, 1.0471975511965976F, 0.0F, 0.0F);
        this.shape58_2 = new ModelRenderer(this, 18, 8);
        this.shape58_2.setRotationPoint(-6.5F, 0.0F, 0.0F);
        this.shape58_2.addBox(-1.0F, -5.33F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotation(shape58_2, -2.0943951023931953F, 0.0F, 0.0F);
        this.shape82 = new ModelRenderer(this, 18, 12);
        this.shape82.setRotationPoint(-0.5F, -6.0F, -0.5F);
        this.shape82.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape14_7 = new ModelRenderer(this, 20, 21);
        this.shape14_7.setRotationPoint(-16.0F, -6.0F, -1.5F);
        this.shape14_7.addBox(0.0F, 0.0F, 0.0F, 10, 1, 3, 0.0F);
        this.shape1_5 = new ModelRenderer(this, 52, 10);
        this.shape1_5.setRotationPoint(4.0F, 2.6F, -1.5F);
        this.shape1_5.addBox(0.0F, -2.0F, -3.0F, 3, 2, 3, 0.0F);
        this.setRotation(shape1_5, -1.0471975511965976F, 0.0F, 0.0F);
        this.shape48 = new ModelRenderer(this, 0, 49);
        this.shape48.setRotationPoint(0.0F, 7.79F, -1.5F);
        this.shape48.addBox(0.0F, -3.0F, -3.0F, 8, 3, 3, 0.0F);
        this.setRotation(shape48, -1.0471975511965976F, 0.0F, 0.0F);
        this.shape48_2 = new ModelRenderer(this, 0, 55);
        this.shape48_2.setRotationPoint(0.0F, 7.79F, -1.5F);
        this.shape48_2.addBox(0.0F, -3.0F, 0.0F, 8, 3, 6, 0.0F);
        this.shape45_.addChild(this.shape45_1);
        this.sixside_1.addChild(this.shape1__9);
        this.sixside.addChild(this.shape1__4);
        this.sixside.addChild(this.shape1__3);
        this.sixside_1.addChild(this.shape1__5);
        this.sixside_1.addChild(this.shape1__6);
        this.shape58_1.addChild(this.shape59_1);
        this.sixside.addChild(this.shape1__1);
        this.shape58_2.addChild(this.shape59_2);
        this.shape58.addChild(this.shape59);
        this.shape58_1.addChild(this.shape60_1);
        this.shape58_2.addChild(this.shape60_2);
        this.sixside.addChild(this.shape1_);
        this.sixside_1.addChild(this.shape1__7);
        this.shape58.addChild(this.shape60);
        this.shape45_.addChild(this.shape45);
        this.shape45_.addChild(this.shape48_1);
        this.sixside.addChild(this.shape1__2);
        this.shape58_2.addChild(this.shape83);
        this.sixside_1.addChild(this.shape1__8);
        this.shape58_2.addChild(this.shape82);
        this.shape45_.addChild(this.shape48);
        this.shape45_.addChild(this.shape48_2);
    }

    

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, int ammoLeft,
			float reloadProgress, TransformType transformType, int part, float fireProgress) {
		
		    this.shape14_1.render(scale);
	        this.shape1_8.render(scale);
	        this.shape14_9.render(scale);
	        this.shape1_2.render(scale);
	        this.sixside.render(scale);
	        this.shape14_3.render(scale);
	        this.shape99.render(scale);
	        this.shape14_6.render(scale);
	        this.shape58.render(scale);
	        this.shape14_5.render(scale);
	        this.shape1_7.render(scale);
	        this.shape14_12.render(scale);
	        this.shape99_2.render(scale);
	        this.shape45_.render(scale);
	        this.shape14_10.render(scale);
	        this.shape58_1.render(scale);
	        this.shape84_6.render(scale);
	        this.shape84_7.render(scale);
	        this.shape1_11.render(scale);
	        this.shape14_8.render(scale);
	        this.shape14_4.render(scale);
	        this.shape84_2.render(scale);
	        this.shape1_1.render(scale);
	        this.shape99_1.render(scale);
	        this.shape15.render(scale);
	        this.sixside_1.render(scale);
	        this.shape84_5.render(scale);
	        this.shape14_11.render(scale);
	        this.shape84_3.render(scale);
	        this.shape1.render(scale);
	        this.shape1_9.render(scale);
	        this.shape14_2.render(scale);
	        this.shape84.render(scale);
	        this.shape1_4.render(scale);
	        this.shape1_10.render(scale);
	        this.shape84_1.render(scale);
	        this.shape84_4.render(scale);
	        this.shape14.render(scale);
	        this.shape1_6.render(scale);
	        this.shape1_3.render(scale);
	        this.shape58_2.render(scale);
	        this.shape14_7.render(scale);
	        this.shape1_5.render(scale);
	        
	        //
	        //System.out.println("FireProgress= "+fireProgress);
	        
	        TGRenderHelper.enableBlendMode(RenderType.ALPHA);
	        this.GLOW.render(scale);
	        if (fireProgress > 0) {
	        	
	        	//GL11.glDisable(GL11.GL_CULL_FACE);
	        	GlStateManager.disableCull();
	        	//System.out.println("FireProgress= "+fireProgress);
		  //      TGRenderHelper.enableAlphaBlend();
		        //this.glowA1.offsetY = (float) (-6.0f + Math.sin(fireProgress*2.0*Math.PI));
	
		        GL11.glPushMatrix();
		        double s = 0.90 + Math.sin(fireProgress*2.0*Math.PI)*0.1;
		        GL11.glScaled(s, s, s);
		        
		        this.glowA1.render(scale);
		        this.glowA1.rotateAngleX = 63.0f*((float)MathUtil.D2R);
		        this.glowA1.render(scale);
		        this.glowA1.rotateAngleX = 117.0f*((float)MathUtil.D2R);
		        this.glowA1.render(scale);
		        this.glowA1.rotateAngleX = 155.0f*((float)MathUtil.D2R);
		        this.glowA1.render(scale);
		        this.glowA1.rotateAngleX = -155.0f*((float)MathUtil.D2R);
		        this.glowA1.render(scale);
		        this.glowA1.rotateAngleX = -117.0f*((float)MathUtil.D2R);
		        this.glowA1.render(scale);
		        this.glowA1.rotateAngleX = -63.0f*((float)MathUtil.D2R);
		        this.glowA1.render(scale);
		        this.glowA1.rotateAngleX = -25.0f*((float)MathUtil.D2R);
		        this.glowA1.render(scale);
		        this.glowA1.rotateAngleX = 25.0f*((float)MathUtil.D2R);
		        
		        GL11.glPopMatrix();
		        
		 //       TGRenderHelper.disableAlphaBlend();
		        
		        //GL11.glEnable(GL11.GL_CULL_FACE);
		        GlStateManager.enableCull();
	        }
	        
	        TGRenderHelper.disableBlendMode(RenderType.ALPHA);
	        //
	}
}
