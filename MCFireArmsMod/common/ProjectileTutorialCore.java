package MCFireArmsMod.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import MCFireArmsMod.client.RenderArchery;
import MCFireArmsMod.client.RenderCompoundBow;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/*
 * Modの基本部分の登録。
 * ここでは説明を割愛する。*/
@Mod(modid = ProjectileTutorialCore.MODID, name = "MC FireArms Mod", version = "1.0.1")
//@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class ProjectileTutorialCore {
	//public static Item itemSample;

	@Mod.Instance("ModelLoder")
	public static ProjectileTutorialCore instance;
	//public static Item itemregisterarchery;
	//public final static Item itemregisterarchery = new ItemRegister(5002, "Archery");
	//public final static Item itemregistermp5a5 = new ItemRegister(5003, "MP5A5");

	//public static final int archeryguiID = 1;

	public static final String MODID = "mcfirearms";

	//public static EntityPlayer ReloadingEntityPlayer;

	//public static int keyInfomationR = 19;
	//public static int keyInfomationADS = 29;

	//boolean gamestartup = true;

	private final static RenderArchery renderarchery = new RenderArchery();
	private final static RenderCompoundBow rendercompound = new RenderCompoundBow();

	public static IModelCustom mcgunsmodelarchery;
	public static IModelCustom mcgunsmodelcompound;

	public static final CreativeTabs tabarchery = new TabCreateHandler("ArcheryMod");
	//public static final CreativeTabs mcgunstab = new ArcheryMod.client.AddTab("ArcheryMod");

	//public static Item itemregisterammo;
	public static Item itemregisterarrow;
	public static Item itemregisterearrow;

	@SidedProxy(clientSide = "MCFireArmsMod.client.ClientSideProxy", serverSide = "MCFireArmsMod.common.CommonSideProxy")
	public static CommonSideProxy proxy;

	/*
	 * @Instance("archery") public static ProjectileTutorialCore instance;
	 */

	public static Item bulletSource;
	//public static Item mp5a5bulletSource;
	public static Item compoundbulletSource;
	//public int newItemID = 7070;
	public static int entityIdHead = 170;
	public static int entityIdBullet = 171;
	//public static int entityIdBullet2 = 172;
	//public static int entityIdNose = 171;
	//public static int ArcheryItemID = 5002;
	//public static int C96ItemID = 5003;
	//public static int C96AmmoItemID = 5004;
	//public static int ArcheryArrowItemID = 5005;
	//public static int ArcheryExplosionItemID = 5006;
	//public static int CompoundItemID = 5007;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {


		// コンフィグファイルの作成。アイテム、エンティティのIDを変更可能にする。
		/*
		 * Configuration cfg = new
		 * Configuration(event.getSuggestedConfigurationFile()); try {
		 * cfg.load(); //Property itemID = cfg.getItem("BulletSourceItem",
		 * newItemID); Property entityHead = cfg.get("entity", "VillagerHead",
		 * entityIdHead); Property entityNose = cfg.get("entity",
		 * "VillagerNose", entityIdNose);
		 *
		 * //newItemID = itemID.getInt(); entityIdHead = entityHead.getInt();
		 * entityIdNose = entityNose.getInt(); } catch (Exception e) {
		 * FMLLog.log(Level.SEVERE, e, "Error Message");
		 *
		 * } finally { cfg.save(); }
		 */

		// アイテムの登録。
		bulletSource = new ItemBulletSource(Item.ToolMaterial.EMERALD)
		.setUnlocalizedName("Archery")
		.setCreativeTab(this.tabarchery)
		.setMaxStackSize(1)
		.setTextureName("mcfirearms:bow_icon");
		itemregisterarrow = new Item()
		.setUnlocalizedName("Archery Arrow")
		.setCreativeTab(this.tabarchery)
		.setMaxStackSize(16)
		.setTextureName("mcfirearms:Arrow");
		itemregisterearrow = new Item()
		.setUnlocalizedName("Archery Explosion Arrow")
		.setCreativeTab(this.tabarchery)
		.setMaxStackSize(16)
		.setTextureName("mcfirearms:eArrow");
		compoundbulletSource = new ItemBulletSource(Item.ToolMaterial.EMERALD)
		.setUnlocalizedName("Compound Bow")
		.setCreativeTab(this.tabarchery)
		.setMaxStackSize(1)
		.setTextureName("mcfirearms:compound");
		//c96bulletSource = new ItemGunBulletSource(C96ItemID,"Mauser C96");
		//itemregisterammo = new ItemAmmoRegister(C96AmmoItemID,"Mauser C96 Ammo");
		//itemregisterarrow = new ItemArrowRegister(ArcheryArrowItemID,"Archery Arrow");
		//itemregisterearrow = new ItemEArrowRegister(ArcheryExplosionItemID,"Archery Explosion Arrow");
		//compoundbulletSource = new ItemCompoundSource(CompoundItemID, EnumToolMaterial.EMERALD,"Compound Bow");

		GameRegistry.registerItem(bulletSource, "archery");
		//GameRegistry.registerItem(itemregisterammo, "mauserc96ammo");
		GameRegistry.registerItem(itemregisterarrow, "archeryarrow");
		GameRegistry.registerItem(itemregisterearrow, "archeryexplosionarrow");
		GameRegistry.registerItem(compoundbulletSource, "compoundbow");

	}

	/*
	 * @EventHandler public void init(FMLInitializationEvent event) {
	 * //エンティティの登録。 EntityRegistry.registerModEntity(EntityBullet.class,
	 * "Arrow", entityIdHead, this, 128, 5, true);
	 *
	 * GameRegistry.addRecipe(new ItemStack(bulletSource, 1), new Object[]{
	 * "XXY","ZZX","ZZX", 'X',Block.blockIron, 'Y',Block.blockDiamond,
	 * 'Z',Block.coalBlock});
	 *
	 * //プロキシを通して、クライアントサイドのみでエンティティのモデル・レンダーの登録を行う。 proxy.registerRenderers();
	 *
	 * //アイテムとエンティティの名前の登録。 //LanguageRegistry.addName(new
	 * ItemStack(this.bulletSource, 1, 0), "Villager Core");
	 * //LanguageRegistry.instance().addNameForObject(new
	 * ItemStack(this.bulletSource, 1, 0), "ja_JP", "村人コア");
	 *
	 * //LanguageRegistry.instance().addStringLocalization(
	 * "entity.villagerHead.name", "en_US", "FlingVillagerHead"); //
	 * LanguageRegistry
	 * .instance().addStringLocalization("entity.villagerHead.name", "ja_JP",
	 * "村人波動砲");
	 *
	 * }
	 */
	//@SideOnly(Side.CLIENT)


	@EventHandler
	public void load(FMLInitializationEvent event) {

		//ItemGunBulletSource.reloadflag = 1;
		//MinecraftForge.EVENT_BUS.register(new SoundHandler());
		//FMLCommonHandler.instance().bus().register(this);//KeyHandlingEvent用
		EntityRegistry.registerModEntity(EntityBullet.class, "Arrow",
				entityIdHead, this, 128, 5, true);

		/*EntityRegistry.registerModEntity(EntityGunBullet.class, "Bullet2",
				entityIdBullet2, this, 128, 5, true);*/
		proxy.registerRenderers();
		//addItemsToRegistries();
		// addBlocksToRegistries();
		//mcgunsmodelmg42 = AdvancedModelLoader.loadModel("/assets/archery/models/archery.obj");
		// mcgunsmodelak5c =
		// AdvancedModelLoader.loadModel("/assets/mcguns/models/Ak5C.obj");
		// mcgunsmodelsemipump =
		// AdvancedModelLoader.loadModel("/assets/mcguns/models/semi-pump.obj");
		// mcgunsmodelbayonet =
		// AdvancedModelLoader.loadModel("/assets/mcguns/models/bayonet.obj");
		// mcgunsmodelmp5a5 =
		// AdvancedModelLoader.loadModel("/assets/mcguns/models/MP5A5.obj");
		//itemSample = (new AddTabID(7000))/* .setCreativeTab(mcgunstab) */;

		//LanguageRegistry.addName(itemSample, "Archery");

		GameRegistry.addRecipe(new ItemStack(bulletSource, 1), new Object[] {
				"XXY", "ZZX", "AZX", 'X', Blocks.iron_ore, 'Y',
				Blocks.diamond_ore, 'Z', Blocks.coal_block, 'A' , Items.bow });
		GameRegistry.addRecipe(new ItemStack(itemregisterarrow, 16), new Object[] {
			"  X", "YX ", "XY ", 'X', Blocks.iron_ore, 'Y',Items.feather });
		GameRegistry.addRecipe(new ItemStack(itemregisterearrow, 1), new Object[] {
			"Y", "X", 'X', itemregisterarrow, 'Y',Blocks.tnt });
		GameRegistry.addRecipe(new ItemStack(compoundbulletSource, 1), new Object[] {
			" Y ", "Z X", " Y ", 'X', bulletSource, 'Y',Items.ender_pearl, 'Z',Items.bow });
	}
	/*@SubscribeEvent
    public void KeyHandlingEvent(KeyInputEvent event) {
        if (ClientSideProxy.ReloadKey.isPressed()) {
        	ItemGunBulletSource.Rreload = 1;
            PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(1));//1をサーバーに送る。
        }
        if(ClientSideProxy.ADSKey.isPressed()){
        	//System.out.println("keycalled");
        	ItemGunBulletSource.toggleads = !ItemGunBulletSource.toggleads;
    }
}*/
	@SideOnly(Side.CLIENT)
	@EventHandler
	public void loadinit(FMLInitializationEvent event) {


		mcgunsmodelarchery = AdvancedModelLoader
				.loadModel(new ResourceLocation(ProjectileTutorialCore.MODID, "models/archery.obj"));
		//mcgunsmodelmp5a5 = AdvancedModelLoader.loadModel("/assets/archery/models/MP5A5.obj");
		mcgunsmodelcompound = AdvancedModelLoader
				.loadModel(new ResourceLocation(ProjectileTutorialCore.MODID, "models/Compound_bow.obj"));

		addItemsToRegistries();
	}

	/*@SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(KeyBindings.ReloadKey.isPressed())
        {
        	//byte bytevalue = 1;
        	//PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(bytevalue));//1をサーバーに送る。
        	ItemGunBulletSource.Rreload = 1;
        }
        if(KeyBindings.ADSKey.isPressed())
        	ItemGunBulletSource.toggleads = !ItemGunBulletSource.toggleads;
    }*/

	@SideOnly(Side.CLIENT)
	private void addItemsToRegistries() {

		LanguageRegistry.addName(itemregisterarrow, "Archery Arrow");
		LanguageRegistry.addName(itemregisterearrow, "Archery Explosion Arrow");

		LanguageRegistry.addName(bulletSource, "Archery");
		MinecraftForgeClient.registerItemRenderer(bulletSource,renderarchery);

		LanguageRegistry.addName(compoundbulletSource, "Compound Bow");
		MinecraftForgeClient.registerItemRenderer(compoundbulletSource,rendercompound);

	}


}