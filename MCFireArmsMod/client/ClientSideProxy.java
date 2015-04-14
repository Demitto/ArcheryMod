package MCFireArmsMod.client;

import net.minecraft.world.World;
import MCFireArmsMod.common.CommonSideProxy;
import MCFireArmsMod.common.EntityBullet;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientSideProxy extends CommonSideProxy{


	//public static final KeyBinding RReloadKey = new KeyBinding("Key.sample", Keyboard.KEY_R, "MCFireArms");
	//public static final KeyBinding ADSKey = new KeyBinding("Key.sample2", Keyboard.KEY_LCONTROL, "MCFireArms2");
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new EntityRenderer(new BulletModel()));
	}

}