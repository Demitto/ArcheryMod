package MCFireArmsMod.common;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

/*
 * ItemSwordを継承しているため、見た目はエメラルドだが攻撃力を持つ。
 * ただし右クリック動作はItemBowから持ってきており、引き絞る動作、タメ時間による発射エンティティの変化がある。
 * タメ時間によるエンティティの撃ち分けができれば何でも良かったので、剣のような仕様にした意味はあまりない。*/
public class ItemBulletSource extends ItemSword {

	/* 剣としてのダメージ。 */
	private float damage;
	public static boolean ExplosionArrow;

	public ItemBulletSource(Item.ToolMaterial par1) {
		super(par1);
		//this.setTextureName("archery:compound");
		//this.setCreativeTab(ProjectileTutorialCore.mcgunstab);
		//this.setUnlocalizedName(itemname);
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
		this.damage = 2;
	}

	/* 剣としてのダメージを設定するメソッド。今回はdamageに代入した値（ハート2.5個分）を使用。 */
	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage
				.getAttributeUnlocalizedName(), new AttributeModifier(
				field_111210_e, "Weapon modifier", (double) this.damage, 0));
		return multimap;
	}

	/* アイコンはバニラのエメラルドを使用。 */
	/*
	 * public String getTextureFile(){ return "/white.png"; }
	 */

	/*
	 * 右クリック使用をやめた時に呼ばれるメソッド。右クリックを継続して押していた時間をもとに、エンティティを発射する処理を行う。
	 */
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, int par4) {



		// par4は右クリックの押下時間。
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

		// まず、クリエイティブであるか＆このアイテムにInfinityエンチャントが付いているかを確かめる。いずれかを満たすとtrue。
		boolean flag = par3EntityPlayer.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(
						Enchantment.infinity.effectId, par1ItemStack) > 0;

		if (!flag)// アイテムにダメージを与える。
		{
			par1ItemStack.damageItem(1, par3EntityPlayer);
		}

		// 右クリック押下時間をもとに計算。20で割り（単位を秒に変換）、なにやら二次関数的な計算式に入れている。
		// ここではバニラ弓のまま使っているが、独自の計算式でも良いと思います。
		float f = (float) j / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		// タメ時間が一定以下の場合、何も起こさず処理から抜ける。
		if ((double) f < 0.5D) {
			return;
		}


		// fの上限値。
		if (f > 1.0F) {
			f = 1.0F;
		}

		if(par3EntityPlayer.inventory.hasItem(ProjectileTutorialCore.itemregisterarrow)){

		EntityBullet bullet = new EntityBullet(par2World, par3EntityPlayer, f * 3.0F);//発射処理

		// par2World.playSoundAtEntity(par3EntityPlayer, "mob.villager.idle",
		// 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F
				/ (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

		if (!par2World.isRemote) {
			par2World.spawnEntityInWorld(bullet);
		}
		ExplosionArrow = false;
		//System.out.println(ExplosionArrow);
		if(!par3EntityPlayer.capabilities.isCreativeMode){
		par3EntityPlayer.inventory.consumeInventoryItem(ProjectileTutorialCore.itemregisterarrow);
		}

		}else if(par3EntityPlayer.inventory.hasItem(ProjectileTutorialCore.itemregisterearrow)){
			EntityBullet bullet = new EntityBullet(par2World, par3EntityPlayer,
					f * 2.5F);//発射処理

			// par2World.playSoundAtEntity(par3EntityPlayer, "mob.villager.idle",
			// 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F
					/ (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (!par2World.isRemote) {
				par2World.spawnEntityInWorld(bullet);
			}

			ExplosionArrow = true;
			//System.out.println(ExplosionArrow);
			if(!par3EntityPlayer.capabilities.isCreativeMode){
			par3EntityPlayer.inventory.consumeInventoryItem(ProjectileTutorialCore.itemregisterearrow);
			}
		}
	}

	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	/*
	 * 右クリックでの使用（タメ）時間の上限。
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	/*
	 * 右クリック時の動作のタイプ。 ここではbow（引き絞るタメ動作）にしているが、ガードや飲食などに変えることも出来、呼ばれるメソッドが異なる。
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	/*
	 * 右クリックでの使用時に呼ばれるメソッド。
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack,
				this.getMaxItemUseDuration(par1ItemStack));

		return par1ItemStack;
	}

	/*
	 * 右クリックでタメている時のアイコンを変えられる。今回は特に変えていない。
	 */
	//@SideOnly(Side.CLIENT)
	/*public Icon getItemIconForUseDuration(int par1) {
		return this.itemIcon;
	}*/

	/*
	@SideOnly(Side.CLIENT)
	// アイテムアイコンのエフェクト
	public boolean hasEffect(ItemStack par1ItemStack) {
		return par1ItemStack.getItemDamage() > 0;
	}*/

}