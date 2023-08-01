package us.timinc.mc.cobblemon.wildhiddenabilities

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import net.fabricmc.api.ModInitializer
import kotlin.random.Random

object WildHiddenAbilities : ModInitializer {
    override fun onInitialize() {}

    fun addHiddenAbilityChance(
        entity: PokemonEntity
    ) {
        val pokemon = entity.pokemon

        val hiddenAbilitySides = 10
        val hiddenAbilityChance = 1
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