package minechem.oredictionary;

import minechem.item.molecule.MoleculeEnum;
import minechem.item.molecule.Molecule;
import minechem.potion.PotionChemical;
import minechem.tileentity.decomposer.DecomposerRecipe;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

public class OreDictionaryUndergroundBiomesHandler implements OreDictionaryHandler
{

    private enum EnumOre
    {
        stoneGraniteBlack, stoneGraniteRed, stoneRhyolite, stoneAndesite, stoneGabbro, stoneBasalt, stoneKomatiite, stoneDacite, stoneGneiss, stoneEclogite, stoneMarble, stoneQuartzite, stoneBlueschist, stoneGreenschist, stoneSoapstone, stoneMigmatite, stoneLimestone, stoneChalk, stoneShale, stoneSiltstone, stoneLignite, stoneDolomite, stoneGreywacke, stoneChert
    }

    @Override
    public boolean canHandle(OreRegisterEvent event)
    {
        for (EnumOre ore : EnumOre.values())
        {
            if (ore.name().equals(event.Name))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handle(OreRegisterEvent event)
    {
        EnumOre ore = EnumOre.valueOf(event.Name);
        switch (ore)
        {
        case stoneGraniteBlack:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.siliconDioxide, 20), new Molecule(MoleculeEnum.plagioclaseAlbite, 4), new Molecule(MoleculeEnum.plagioclaseAnorthite, 4), new Molecule(MoleculeEnum.orthoclase, 3), new Molecule(MoleculeEnum.biotite, 1) }));
            break;
        case stoneGraniteRed:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.siliconDioxide, 20), new Molecule(MoleculeEnum.plagioclaseAlbite, 3), new Molecule(MoleculeEnum.plagioclaseAnorthite, 3), new Molecule(MoleculeEnum.orthoclase, 4), new Molecule(MoleculeEnum.biotite, 1),
                    new Molecule(MoleculeEnum.whitePigment, 1) }));
            break;
        case stoneRhyolite:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.siliconDioxide, 18), new Molecule(MoleculeEnum.plagioclaseAlbite, 5), new Molecule(MoleculeEnum.plagioclaseAnorthite, 5), new Molecule(MoleculeEnum.orthoclase, 2), new Molecule(MoleculeEnum.biotite, 1),
                    new Molecule(MoleculeEnum.augite, 1) }));
            break;
        case stoneAndesite:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.plagioclaseAlbite, 12), new Molecule(MoleculeEnum.plagioclaseAnorthite, 12), new Molecule(MoleculeEnum.augite, 8) }));
            break;
        case stoneGabbro:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.plagioclaseAlbite, 16), new Molecule(MoleculeEnum.plagioclaseAnorthite, 8), new Molecule(MoleculeEnum.augite, 7), new Molecule(MoleculeEnum.olivine, 1) }));
            break;
        case stoneBasalt:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.plagioclaseAlbite, 8), new Molecule(MoleculeEnum.plagioclaseAnorthite, 8), new Molecule(MoleculeEnum.augite, 12), new Molecule(MoleculeEnum.olivine, 4) }));
            break;
        case stoneKomatiite:
            // TODO: find actual components (this is c/p from basalt)
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.plagioclaseAlbite, 8), new Molecule(MoleculeEnum.plagioclaseAnorthite, 8), new Molecule(MoleculeEnum.augite, 12), new Molecule(MoleculeEnum.olivine, 4) }));
            break;
        case stoneDacite:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.plagioclaseAlbite, 6), new Molecule(MoleculeEnum.plagioclaseAnorthite, 6), new Molecule(MoleculeEnum.biotite, 8), new Molecule(MoleculeEnum.siliconDioxide, 8), new Molecule(MoleculeEnum.augite, 4) }));
            break;
        case stoneGneiss:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.orthoclase, 8), new Molecule(MoleculeEnum.plagioclaseAlbite, 4), new Molecule(MoleculeEnum.plagioclaseAnorthite, 6), new Molecule(MoleculeEnum.biotite, 4), new Molecule(MoleculeEnum.siliconDioxide, 6),
                    new Molecule(MoleculeEnum.augite, 4) }));
            break;
        case stoneEclogite:
            // TODO: find actual components (this is c/p from basalt)
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.plagioclaseAlbite, 8), new Molecule(MoleculeEnum.plagioclaseAnorthite, 8), new Molecule(MoleculeEnum.augite, 12), new Molecule(MoleculeEnum.olivine, 4) }));
            break;
        case stoneMarble:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.calcite, 32) }));
            break;
        case stoneQuartzite:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.siliconDioxide, 32) }));
            break;
        case stoneGreenschist:
        case stoneBlueschist:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.plagioclaseAlbite, 8), new Molecule(MoleculeEnum.plagioclaseAnorthite, 8), new Molecule(MoleculeEnum.biotite, 4), new Molecule(MoleculeEnum.siliconDioxide, 8), new Molecule(MoleculeEnum.augite, 4) }));
            break;
        case stoneSoapstone:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.talc, 32) }));
            break;
        default:
            DecomposerRecipe.add(new DecomposerRecipe(event.Ore, new PotionChemical[]
            { new Molecule(MoleculeEnum.siliconDioxide, 8), new Molecule(MoleculeEnum.plagioclaseAnorthite, 8), new Molecule(MoleculeEnum.plagioclaseAlbite, 8), new Molecule(MoleculeEnum.orthoclase, 8) }));
            break;
        }
    }

}
