/*
 * Copyright 2019 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.destinationsol.game.console.commands;

import org.destinationsol.game.DmgType;
import org.destinationsol.game.Hero;
import org.destinationsol.game.SolGame;
import org.destinationsol.game.console.annotations.Command;
import org.destinationsol.game.console.annotations.Game;
import org.destinationsol.game.console.annotations.RegisterCommands;
import org.destinationsol.game.console.exceptions.CommandExecutionException;

/**
 * A command used to instantly destroy the hero's ship, mostly for debugging purposes.
 */
@RegisterCommands
public class DieCommandHandler {

    @Command(shortDescription = "Kills the hero")
    public String die(@Game SolGame game) throws CommandExecutionException {
        Hero hero = game.getHero();
        if (hero.isTranscendent()) {
            throw new CommandExecutionException("Cannot kill hero when transcendent!");
        }
        if (!hero.isAlive()) {
            throw new CommandExecutionException("Hero is already dead!");
        }
        hero.getShip().receivePiercingDmg(hero.getHull().getHullConfig().getMaxLife() + 1f, game, hero.getPosition(), DmgType.CRASH);
        return "Hero killed!";
    }
}
