package com.lyonic.testmod.block;

import com.lyonic.testmod.TestMod;
import com.lyonic.testmod.block.custom.BlueBerryCropBlock;
import com.lyonic.testmod.block.custom.JumpyBlock;
import com.lyonic.testmod.block.custom.ZirconLampBlock;
import com.lyonic.testmod.item.ModCreativeModeTab;
import com.lyonic.testmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);

    //Blocks
    public static final RegistryObject<Block> ZIRCON_BLOCK = registerBlock("zircon_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> ZIRCON_ORE = registerBlock("zircon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE = registerBlock("deepslate_zircon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> ENDSTONE_ZIRCON_ORE = registerBlock("endstone_zircon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> NETHERRACK_ZIRCON_ORE = registerBlock("netherrack_zircon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> JUMPY_BLOCK = registerBlock("jumpy_block", () -> new JumpyBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> ZIRCON_LAMP = registerBlock("zircon_lamp", () -> new ZirconLampBlock(BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS).strength(4f).lightLevel(state -> state.getValue(ZirconLampBlock.LIT) ? 15 : 0)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> BLUEBERRY_CROP = BLOCKS.register("blueberry_crop", () -> new BlueBerryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

}
