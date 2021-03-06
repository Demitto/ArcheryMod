package MCFireArmsMod.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GunBulletModel extends ModelBase{

	ModelRenderer GunBullet;
    //ModelRenderer Nose;

  public GunBulletModel()
  {
    textureWidth = 64;
    textureHeight = 64;

      GunBullet = new ModelRenderer(this, 0, 0);
      //Head.addBox(-4F, -5F, -4F, 1, 1, 30);
      //GunBullet.addBox(0F, 18F, -10F, 1, 1, 1);
      GunBullet.addBox(0F, 18F, 0F, 1, 1, 1);
      GunBullet.setRotationPoint(0F, 0F, 0F);
      GunBullet.setTextureSize(64, 64);
      GunBullet.mirror = true;
      setRotation(GunBullet, 0F, 0F, 0F);
      //Nose = new ModelRenderer(this, 24, 0);
      //Nose.addBox(-1F, 2F, -6F, 2, 4, 2);
      //Nose.setRotationPoint(0F, 16F, 0F);
      //Nose.setTextureSize(64, 64);
      //Nose.mirror = true;
	  }

	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    GunBullet.render(f5);
	    //Nose.render(f5);
	  }

	  public void renderNose(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    //Nose.render(f5);
	  }

	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }

	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
            //飛ばしたエンティティが回転しながら飛んで行く。
	    //this.Head.rotateAngleZ += 0.5F / (180F / (float)Math.PI);
	    //this.Nose.rotateAngleZ += 0.5F / (180F / (float)Math.PI);
	  }

}