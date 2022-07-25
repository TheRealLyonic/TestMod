package com.lyonic.testmod.villager;

import com.google.common.collect.ImmutableSet;
import com.lyonic.testmod.TestMod;
import com.lyonic.testmod.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, TestMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, TestMod.MOD_ID);

    /*
    POIs = Points of Interest (Where the villager will go to get their
    profession).
    */
    public static final RegistryObject<PoiType> JUMPY_BLOCK_POI = POI_TYPES.register("jumpy_block_poi", () -> new PoiType(ImmutableSet.copyOf(ModBlocks.JUMPY_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));

    /*
    Villager Professions *SHOULD HAVE AN ASSOCIATED POI*
    You can also set a secondary POI, hence there's 2 parameters that
    take POIs, but you can just pass the same POI twice if you want the
    villager to only have one.
    */
    public static final RegistryObject<VillagerProfession> JUMPY_MASTER = VILLAGER_PROFESSIONS.register("jumpy_master", () -> new VillagerProfession("jumpy_master", x -> x.get() == JUMPY_BLOCK_POI.get(), x -> x.get() == JUMPY_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));


    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

    public static void registerPOIs(){
        try{
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, JUMPY_BLOCK_POI.get());
        }catch(InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
            System.out.println("In the registerPOIs method of the ModVillagers class.");
        }
    }

}
