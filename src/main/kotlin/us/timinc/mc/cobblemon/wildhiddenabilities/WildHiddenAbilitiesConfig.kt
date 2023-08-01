package us.timinc.mc.cobblemon.wildhiddenabilities

import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment

@Config(name = WildHiddenAbilities.MOD_ID)
class WildHiddenAbilitiesConfig : ConfigData {
    @Comment("Imagine you're rolling a die to figure out if a Pokemon has its hidden ability or not. How many sides does that die have?")
    val hiddenAbilitySides = 10
    @Comment("Imagine you're rolling a die to figure out if a Pokemon has its hidden ability or not. What value do you have roll below?")
    val hiddenAbilityChance = 1
}