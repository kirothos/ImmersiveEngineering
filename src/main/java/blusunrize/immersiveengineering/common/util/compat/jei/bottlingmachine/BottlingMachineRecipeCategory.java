package blusunrize.immersiveengineering.common.util.compat.jei.bottlingmachine;

import blusunrize.immersiveengineering.api.crafting.BottlingMachineRecipe;
import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.metal.BlockTypes_MetalMultiblock;
import blusunrize.immersiveengineering.common.util.compat.jei.IERecipeCategory;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class BottlingMachineRecipeCategory extends IERecipeCategory<BottlingMachineRecipe, BottlingMachineRecipeWrapper>
{
	public static ResourceLocation background = new ResourceLocation("immersiveengineering:textures/gui/fermenter.png");
	private final IDrawable tankOverlay;
	static ItemStack bottlignMachineStack;

	public BottlingMachineRecipeCategory(IGuiHelper helper)
	{
		super("bottlingMachine","tile.immersiveengineering.metalMultiblock.bottling_machine.name", helper.createBlankDrawable(140,50), BottlingMachineRecipe.class, new ItemStack(IEContent.blockMetalMultiblock,1,BlockTypes_MetalMultiblock.BOTTLING_MACHINE.getMeta()));
		tankOverlay = helper.createDrawable(background, 177,31, 20,51, -2,2,-2,2);
		bottlignMachineStack = new ItemStack(IEContent.blockMetalMultiblock,1,BlockTypes_MetalMultiblock.BOTTLING_MACHINE.getMeta());
	}

	@Override
	@Deprecated
	public void setRecipe(IRecipeLayout recipeLayout, BottlingMachineRecipeWrapper recipeWrapper)
	{
		//Deprecated
	}
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, BottlingMachineRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 0, 12);
		guiItemStacks.init(1, false, 100, 12);
		guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
		guiItemStacks.set(1, ingredients.getOutputs(ItemStack.class));

		recipeLayout.getFluidStacks().init(0, true, 75,0, 16,47, 4000, false, tankOverlay);
		recipeLayout.getFluidStacks().set(0, ingredients.getInputs(FluidStack.class).get(0));
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(BottlingMachineRecipe recipe)
	{
		return new BottlingMachineRecipeWrapper(recipe);
	}
}