package org.nulla.kcrw.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.nulla.kcrw.KCClientProxy;
import org.nulla.kcrw.KCResources;
import org.nulla.nullacore.api.skill.Skill;
import org.nulla.nullacore.api.skill.SkillPassive;
import org.nulla.nullacore.api.skill.SkillUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KCClientUtils {

	public static Minecraft getMC() {
		return Minecraft.getMinecraft();
	}

	/**
	 * 只能在Client端用，以获取Client端的玩家信息（例如位置），不会改变Server端信息。
	 */
	public static EntityPlayer getPlayerCl() {
		return getMC().thePlayer;
	}

	public static FontRenderer getfontRenderer() {
		return getMC().fontRendererObj;
	}

	/**
	 * 判断Shift键是否按下。
	 */
	public static boolean isShiftKeyDown() {
		return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
	}

	/**
	 * 判断Ctrl键是否按下。
	 */
	public static boolean isCtrlKeyDown() {
		return Minecraft.isRunningOnMac ? Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220)
				: Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
	}

	public static void drawRect(int x1, int y1, int width, int height, int color) {
		int x2 = x1 + width;
		int y2 = y1 + height;

		int j1;

		if (x1 < x2) {
			j1 = x1;
			x1 = x2;
			x2 = j1;
		}

		if (y1 < y2) {
			j1 = y1;
			y1 = y2;
			y2 = j1;
		}

		float f3 = (float) (color >> 24 & 255) / 255.0F;
		float f = (float) (color >> 16 & 255) / 255.0F;
		float f1 = (float) (color >> 8 & 255) / 255.0F;
		float f2 = (float) (color & 255) / 255.0F;
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.color(f, f1, f2, f3);
		worldRenderer.begin(7, DefaultVertexFormats.POSITION);
		worldRenderer.pos((double) x1, (double) y2, 0.0D).endVertex();
		worldRenderer.pos((double) x2, (double) y2, 0.0D).endVertex();
		worldRenderer.pos((double) x2, (double) y1, 0.0D).endVertex();
		worldRenderer.pos((double) x1, (double) y1, 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

	/**
	 * 绘制可缩放的纹理。<BR/>
	 * x，y代表绘制在屏幕上的位置。<BR/>
	 * u，v代表要绘制的部分在源素材图上的位置。<BR/>
	 * uW，vH代表要绘制的部分在源素材图上的大小。<BR/>
	 * w，h代表要绘制的部分的大小。<BR/>
	 * tW，tH代表源素材图的大小（缩放用）。
	 */
	public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width,
			int height, float tileWidth, float tileHeight) {
		float f4 = 1.0F / tileWidth;
		float f5 = 1.0F / tileHeight;
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldRenderer.pos((double) x, (double) (y + height), 0.0D).tex(u * f4, (v + vHeight) * f5).endVertex();
		worldRenderer.pos((double) (x + width), (double) (y + height), 0.0D).tex((u + uWidth) * f4, (v + vHeight) * f5)
				.endVertex();
		worldRenderer.pos((double) (x + width), (double) y, 0.0D).tex((u + uWidth) * f4, v * f5).endVertex();
		worldRenderer.pos((double) x, (double) y, 0.0D).tex(u * f4, v * f5).endVertex();
		tessellator.draw();
	}

	/**
	 * 绘制HUD上的欧若拉条。<BR/>
	 * width，height代表屏幕的宽高。
	 */
	/*public static void drawAuroraStrip(int width, int height) {

		initDrawerState();

		width *= 0.95;
		height *= 0.05;

		int currentAuroraPoint = SkillUtils.getAuroraPoint(getPlayerCl());
		int maximumAuroraPoint = SkillUtils.MAX_AURORA_POINT;

		int length = Math.min(105 * currentAuroraPoint / maximumAuroraPoint, 105);

		GlStateManager.enableBlend();
		getMC().getTextureManager().bindTexture(KCResources.aurora_strip_inside);
		drawScaledCustomSizeModalRect(width - length - 15, height + 1, 630 - 6 * length, 0, 6 * length + 90, 120,
				length + 15, 18, 720, 120);

		getMC().getTextureManager().bindTexture(KCResources.aurora_strip_outside);
		drawScaledCustomSizeModalRect(width - 120, height, 0, 0, 720, 120, 120, 20, 720, 120);
		GlStateManager.disableBlend();

		String info = "Aurora: " + SkillUtils.getAuroraPoint(getPlayerCl()) + " / " + SkillUtils.MAX_AURORA_POINT;
		FontRenderer fontRenderer = getfontRenderer();
		int color = 0x7FFFBF;
		// if (currentAuroraPoint <= MaximumAuroraPoint * 0.25) {color =
		// 0xFF0000;}
		// else if (currentAuroraPoint <= MaximumAuroraPoint * 0.5) {color =
		// 0xFFFF00;}
		// else if (currentAuroraPoint > MaximumAuroraPoint) {color = 0x00FF00;}
		fontRenderer.drawStringWithShadow(info, width - 105, height - 3, color);
		initDrawerState();
		getMC().renderEngine.bindTexture(Gui.icons);
	}*/

	/**
	 * 初始化（绘制器的）撞钛鸡。
	 */
	public static void initDrawerState() {
		FontRenderer fontRenderer = getfontRenderer();
		fontRenderer.drawString("", 0, 0, 0xFFFFFF);
	}

	/**
	 * 绘制HUD上的技能槽。<BR/>
	 * width，height代表屏幕的宽高。
	 */
	/*public static void drawSkillSlot(int width, int height) {

		initDrawerState();

		int widthToDraw = (int) (0.9 * width);
		int heightToDraw[] = new int[4];
		heightToDraw[0] = (int) (height * 0.2);
		heightToDraw[1] = (int) (height * 0.35);
		heightToDraw[2] = (int) (height * 0.5);
		heightToDraw[3] = (int) (height * 0.65);

		String[] SkillButton = new String[4];
		SkillButton[0] = GameSettings.getKeyDisplayString(KCClientProxy.kbSkill1.getKeyCode());
		SkillButton[1] = GameSettings.getKeyDisplayString(KCClientProxy.kbSkill2.getKeyCode());
		SkillButton[2] = "Shift+" + GameSettings.getKeyDisplayString(KCClientProxy.kbSkill1.getKeyCode());
		SkillButton[3] = "Shift+" + GameSettings.getKeyDisplayString(KCClientProxy.kbSkill2.getKeyCode());

		GL11.glEnable(GL11.GL_BLEND);
		Skill skillinslot[] = new Skill[SkillUtils.SKILL_SLOT_SIZE];

		EntityPlayer player = getPlayerCl();
		for (int i = 0; i < skillinslot.length; i++) {
			skillinslot[i] = SkillUtils.getSkillInSlot(player, i);
			if (skillinslot[i] != null) {
				// 绘制技能图标
				getMC().getTextureManager().bindTexture(skillinslot[i].mIcon);
				drawScaledCustomSizeModalRect(widthToDraw, heightToDraw[i], 0, 0, 64, 64, 32, 32, 64, 64);
				// 绘制熟练度条
				int exp = 32 * skillinslot[i].getExperience(player) / skillinslot[i].MAX_EXPERIENCE;
				drawRect(widthToDraw + 32, heightToDraw[i] + 32 - exp, 8, exp, 0xFF7FFF7F);
				// 绘制Aurora消耗
				getfontRenderer().drawStringWithShadow(skillinslot[i].mAuroraCost + "", widthToDraw + 2,
						heightToDraw[i] + 24, 0x000000);
				initDrawerState();
				// 绘制CD状态
				if (!skillinslot[i].checkCD(player)) {
					int time = (int) (player.worldObj.getTotalWorldTime() - skillinslot[i].getLastUseTime(player));
					int length = 32 - 32 * time / skillinslot[i].mCD;
					drawRect(widthToDraw, heightToDraw[i], 32, length, 0x80000000);
					initDrawerState();
				}
			}

			if (skillinslot[i] != null && skillinslot[i] instanceof SkillPassive) {
				SkillPassive toDraw = (SkillPassive) skillinslot[i];
				if (toDraw.getIsOn(player))
					getMC().getTextureManager().bindTexture(KCResources.skill_passive_on);
				else
					getMC().getTextureManager().bindTexture(KCResources.skill_passive_off);
				drawScaledCustomSizeModalRect(widthToDraw, heightToDraw[i], 0, 0, 64, 64, 32, 32, 64, 64);
			} else {
				getMC().getTextureManager().bindTexture(KCResources.skill_empty_slot);
				drawScaledCustomSizeModalRect(widthToDraw, heightToDraw[i], 0, 0, 64, 64, 32, 32, 64, 64);
				getfontRenderer().drawStringWithShadow(SkillButton[i], widthToDraw + 2, heightToDraw[i], 0xFF0000);
				initDrawerState();
			}

			getMC().getTextureManager().bindTexture(KCResources.skill_empty_exp);
			drawScaledCustomSizeModalRect(widthToDraw + 32, heightToDraw[i], 0, 0, 16, 64, 8, 32, 16, 64);

		}

		initDrawerState();
		GL11.glDisable(GL11.GL_BLEND);
		getMC().renderEngine.bindTexture(Gui.icons);
	}*/

	/**
	 * register an item's renderer
	 * 
	 * @param item
	 *            the item that should register a renderer
	 * @deprecated Unique Identifier is deprecated. Please specify a resource
	 *             location
	 **/
	@Deprecated
	public static void registerItemRenderer(Item item) {
		registerItemRenderer(item, GameRegistry.findUniqueIdentifierFor(item).toString());
	}

	/**
	 * register an item's renderer
	 * 
	 * @param item
	 *            the item that should register a renderer
	 * @param resrcloc
	 *            the resource location
	 **/
	public static void registerItemRenderer(Item item, String resrcloc) {
		registerItemRenderer(item, 0, resrcloc);
	}

	/**
	 * register an item's renderer
	 * 
	 * @param item
	 *            the item that should register a renderer
	 * @param meta
	 *            metadata
	 * @param resrcloc
	 *            the resource location
	 **/
	public static void registerItemRenderer(Item item, int meta, String resourcesLocation) {
		getMC().getRenderItem().getItemModelMesher().register(item, meta,
				new ModelResourceLocation(resourcesLocation, "inventory"));
	}

	/**
	 * register an entity's renderer
	 * 
	 * @param entity
	 *            the class of the entity
	 * @param render
	 *            the class of the entity render
	 **/
	@SuppressWarnings("rawtypes")
	public static void registerEntityRenderer(Class<? extends Entity> entity, final Class<? extends Render> render) {
		registerEntityRenderer(entity, render, new Object() {});
	}

	/**
	 * register an entity's renderer
	 * 
	 * @param entity
	 *            the class of the entity
	 * @param render
	 *            the class of the entity render
	 * @param ctorArg
	 *            the arguments of the render's constructor.
	 *            {@link RenderManager} should be ignored. If there is only
	 *            {@link RenderManager} argument, please fill
	 *            " new Object[] {} " in this argument
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void registerEntityRenderer(Class<? extends Entity> entity, final Class<? extends Render> render,
			final Object... ctorArg) {
		RenderingRegistry.registerEntityRenderingHandler(entity, new KCUniteRenderFactory(render, ctorArg));
	}
}
