package techguns.plugins.jei;

import techguns.tileentities.operation.CamoBenchRecipes;
import techguns.tileentities.operation.CamoBenchRecipes.CamoBenchRecipe;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import techguns.tileentities.operation.IMachineRecipe;

public class CamoBenchJeiRecipe extends BasicRecipeWrapper {

	CamoBenchRecipe rec;
	
	public CamoBenchJeiRecipe(CamoBenchRecipe recipe) {
		super(recipe);
		this.rec=recipe;
	}

	@Override
	protected int getRFperTick() {
		return 0;
	}

	public static List<CamoBenchJeiRecipe> getRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
	
		List<CamoBenchJeiRecipe> recipes = new ArrayList<>();
		
		ArrayList<CamoBenchRecipe> m_recipes = CamoBenchRecipes.getRecipes();
		m_recipes.forEach(r -> recipes.add(new CamoBenchJeiRecipe(r)));
		
		return recipes;
	}
}
