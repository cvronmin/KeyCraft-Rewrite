package org.nulla.kcrw.client.renderer;

import org.lwjgl.opengl.GL11;
import org.nulla.kcrw.entity.EntityVibrationWave;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RendererVibrationWave extends Render {

	protected RendererVibrationWave(RenderManager renderManager) {
		super(renderManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityVibrationWave)entity, x, y, z, p_76986_8_, p_76986_9_);
    }
	
	public void doRender(EntityVibrationWave entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {		
		GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthMask(false);
        
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        double r = entity.getCurrentRadius();
        double p = r / entity.getMaxRadius();
        GL11.glColor4f(0.5F, 0.5F, 0.5F, 0.5F);
                
        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, 1.0F);
            worldRenderer.begin(7, DefaultVertexFormats.POSITION);
            worldRenderer.pos( r, 1D - p, r).endVertex(); // 右下
            worldRenderer.pos( r, 1D + p, r).endVertex(); // 右上
            worldRenderer.pos(-r, 1D + p, r).endVertex(); // 左上
            worldRenderer.pos(-r, 1D - p, r).endVertex(); // 左下
            tessellator.draw();
        }

        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}
}