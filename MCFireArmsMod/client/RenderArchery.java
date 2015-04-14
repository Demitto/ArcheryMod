package MCFireArmsMod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import MCFireArmsMod.common.ProjectileTutorialCore;

public class RenderArchery implements IItemRenderer {

  @Override
  public boolean handleRenderType(ItemStack item, ItemRenderType type) {
    switch (type) {
      //case ENTITY:
      case EQUIPPED:
      case EQUIPPED_FIRST_PERSON:
      //case INVENTORY:
        return true;
      default:
        return false;
    }
	  //return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
  }

  @Override
  public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

	  return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
  }


  @Override
  public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

	  switch(type)
      {
          case EQUIPPED :
          {
	  GL11.glPushMatrix();

	  //float scala = 1.2F;
	  float scala = 3.0F;

	  GL11.glScalef(scala, scala, scala);

      GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(230F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(50F, 1.0F, 0.0F, 0.0F);


      GL11.glTranslatef(0.1F, -0.35F, -0.44F);

      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ProjectileTutorialCore.MODID, "textures/archery.png"));
      //Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("/assets/archery/textures/archery.png"));
      ProjectileTutorialCore.mcgunsmodelarchery.renderAll();
	  GL11.glPopMatrix();
	  break;
          }

          //when first person view

          case EQUIPPED_FIRST_PERSON :
          {
        	  GL11.glPushMatrix();

        	  float scala = 3.0F;

        	  GL11.glScalef(scala, scala, scala);

              //GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
              //GL11.glRotatef(140F, 0.0F, 1.0F, 0.0F);
              //GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);

        	  GL11.glRotatef(10F, 0.0F, 0.0F, 1.0F);
              GL11.glRotatef(150F, 0.0F, 1.0F, 0.0F);
              GL11.glRotatef(-12F, 1.0F, 0.0F, 0.0F);

              //GL11.glTranslatef(-0.2F, 0.5F, -0.2F);
              GL11.glTranslatef(-0.2F, 0.1F, 0F);

              Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ProjectileTutorialCore.MODID, "textures/archery.png"));
              //Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("/assets/archery/textures/archery.png"));
        	  ProjectileTutorialCore.mcgunsmodelarchery.renderAll();
        	  GL11.glPopMatrix();
        	  break;
          }
	default:
		break;
      }
}
}