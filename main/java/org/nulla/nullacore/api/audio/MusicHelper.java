package  org.nulla.nullacore.api.audio;

import java.lang.reflect.Field;

import org.nulla.kcrw.KCUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class MusicHelper {
	
	private static ISound currentSound = null; 
	
	public static boolean isBgmPlaying() {
		return currentSound != null;
	}

	/** 停止当前BGM、MC原版BGM并播放新的BGM */
	public static void playBgm(ResourceLocation location) {
		SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
		if (isBgmPlaying()) {
			soundHandler.stopSound(currentSound);
		}
		stopMcBgm();
		currentSound = PositionedSoundRecord.create(location);
		soundHandler.playSound(currentSound);
	}
	
	/** 当前BGM播放完后恢复原版BGM */
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if (event.phase == TickEvent.Phase.END
			&& !mc.isGamePaused()
			&& isBgmPlaying()
			&& !mc.getSoundHandler().isSoundPlaying(currentSound))
		{
			currentSound = null;
			restartMcBgm();
		}
	}
	
	
	private static boolean mcBgmPlaying = true;
	
	private static boolean isMcBgmPlaying() {
		return mcBgmPlaying;
	}
	
	private static void stopMcBgm() {
		if (!mcBgmPlaying)
			return;
		
		Minecraft mc = Minecraft.getMinecraft();
		try {
			Field musicTickerField = ReflectionHelper.findField(Minecraft.class, "mcMusicTicker", "field_147126_aw");
			musicTickerField.setAccessible(true);
			MusicTicker mcMusicTicker = (MusicTicker)musicTickerField.get(mc);

			Field bgmPlayingField = ReflectionHelper.findField(MusicTicker.class, "currentMusic", "field_147678_c");
			Field bgmCdField = ReflectionHelper.findField(MusicTicker.class, "timeUntilNextMusic", "field_147676_d");
			bgmPlayingField.setAccessible(true);
			bgmCdField.setAccessible(true);
			
			bgmCdField.setInt(mcMusicTicker, Integer.MAX_VALUE);
			ISound bgmPlaying = (ISound)bgmPlayingField.get(mcMusicTicker);
			if (bgmPlaying != null)
				mc.getSoundHandler().stopSound(bgmPlaying);
			bgmPlayingField.set(mcMusicTicker, null);
			
			mcBgmPlaying = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void restartMcBgm() {
		if (mcBgmPlaying)
			return;
		Minecraft mc = Minecraft.getMinecraft();
		if (isBgmPlaying())
		{
			mc.getSoundHandler().stopSound(currentSound);
			currentSound = null;
		}
		
		try {
			Field musicTickerField = ReflectionHelper.findField(Minecraft.class, "mcMusicTicker", "field_147126_aw");
			musicTickerField.setAccessible(true);
			MusicTicker mcMusicTicker = (MusicTicker)musicTickerField.get(mc);

			Field bgmCdField = ReflectionHelper.findField(MusicTicker.class, "timeUntilNextMusic", "field_147676_d");
			bgmCdField.setAccessible(true);
			
			bgmCdField.setInt(mcMusicTicker, 0);
			
			mcBgmPlaying = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
