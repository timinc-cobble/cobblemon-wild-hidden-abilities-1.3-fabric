package us.timinc.mc.cobblemon.wildhiddenabilities

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer
import net.fabricmc.api.ModInitializer
import kotlin.random.Random

object WildHiddenAbilities : ModInitializer {
    const val MOD_ID = "wild_hidden_abilities"
    private lateinit var config: WildHiddenAbilitiesConfig

    override fun onInitialize() {
        AutoConfig.register(
            WildHiddenAbilitiesConfig::class.java
        ) { definition: Config?, configClass: Class<WildHiddenAbilitiesConfig?>? ->
            JanksonConfigSerializer(
                definition,
                configClass
            )
        }
        config = AutoConfig.getConfigHolder(WildHiddenAbilitiesConfig::class.java)
            .config
    }

    fun addHiddenAbilityChance(
        entity: PokemonEntity
    ) {
        val cachedConfig = config

        val pokemon = entity.pokemon

        val hiddenAbilitySides = cachedConfig.hiddenAbilitySides
        val hiddenAbilityChance = cachedConfig.hiddenAbilityChance
        val hiddenAbilityRoll = Random.Default.nextInt(0, hiddenAbilitySides)
        if (hiddenAbilityRoll >= hiddenAbilityChance) {
            return
        }

        val tForm = pokemon.form
        val potentialAbilityMapping = tForm.abilities.mapping[Priority.LOW] ?: return

        val potentialAbility = potentialAbilityMapping[0]
        val newAbilityBuilder = potentialAbility.template.builder
        val newAbility = newAbilityBuilder.invoke(potentialAbility.template, false)
        newAbility.index = 0
        newAbility.priority = Priority.LOW
        pokemon.ability = newAbility
    }
}