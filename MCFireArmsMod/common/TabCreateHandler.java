package MCFireArmsMod.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabCreateHandler extends CreativeTabs{

	public TabCreateHandler(String label) {
		super(label);
	}
	@Override
	public Item getTabIconItem() {
		return new ItemStack(ProjectileTutorialCore.compoundbulletSource, 1, 0).getItem();
	}

}
