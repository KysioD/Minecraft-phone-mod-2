package fr.kysio.phonemod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemPhone extends Item {

    public final String NAME = "phone";

    public ItemPhone(){
        setCreativeTab(CreativeTabs.TOOLS);
        PhoneItems.setItemName(this, NAME);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote){

        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
